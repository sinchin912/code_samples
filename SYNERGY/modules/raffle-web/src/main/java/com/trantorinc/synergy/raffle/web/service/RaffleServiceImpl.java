package com.trantorinc.synergy.raffle.web.service;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.trantorinc.synergy.common.service.drive.DriveService;
import com.trantorinc.synergy.email.core.model.Email;
import com.trantorinc.synergy.email.core.service.EmailLocalServiceUtil;
import com.trantorinc.synergy.game.core.model.*;
import com.trantorinc.synergy.game.core.service.PrizeLocalServiceUtil;
import com.trantorinc.synergy.game.core.service.TicketLocalServiceUtil;
import com.trantorinc.synergy.lms.core.model.Employee;
import com.trantorinc.synergy.lms.core.service.EmployeeLocalServiceUtil;
import com.trantorinc.synergy.raffle.web.dto.PrizeDto;
import com.trantorinc.synergy.raffle.web.dto.TicketDto;
import com.trantorinc.synergy.raffle.web.dto.TimelineDto;

import javax.portlet.ActionRequest;
import java.io.File;
import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.trantorinc.synergy.common.service.constant.AppConstantService.*;
import static com.trantorinc.synergy.common.service.util.UtilService.*;
import static com.trantorinc.synergy.raffle.web.constants.RaffleWebPortletKeys.*;

public class RaffleServiceImpl implements RaffleService {
    @Override
    public List<PrizeDto> getPrizes() {
        List<Prize> prizes = PrizeLocalServiceUtil.findPrizesByYear(getCurrentYear());
        boolean isDrawPending = prizes.stream().anyMatch(p -> 0 == p.getTicketId());
        if(isDrawPending){
            prizes = prizes.stream().filter(p -> !p.isSurprise()).sorted(Comparator.comparing(PrizeModel::getSequence).reversed()).collect(Collectors.toList());
        }
        List<PrizeDto> prizeDtos = new ArrayList<>();
        for(Prize prize : prizes){
            PrizeDto prizeDto = new PrizeDto();
            prizeDto.setId(prize.getPrizeId());
            prizeDto.setDescription(prize.getDescription());
            prizeDto.setSurprise(prize.getSurprise());
            prizeDto.setPrizePic(prize.getFileId());
            prizeDto.setSequence(prize.getSequence());
            if(!isDrawPending && null != prize.getWinner()){
                Employee employee = EmployeeLocalServiceUtil.fetchEmployee(prize.getWinner());
                prizeDto.setWinnerName(employee.getName());
                prizeDto.setWinnerAccount(employee.getAccount());
            }
            prizeDtos.add(prizeDto);
        }
        return prizeDtos;
    }

    @Override
    public List<TicketDto> getTickets(String ecode) {
        List<Ticket> tickets = TicketLocalServiceUtil.findTicketByYearAndEcode(getCurrentYear(), ecode);
        List<Prize> prizes = PrizeLocalServiceUtil.findPrizesByYear(getCurrentYear());
        List<TicketDto> ticketDtos = new ArrayList<>();
        for (Ticket ticket : tickets){
            TicketDto ticketDto = new TicketDto();
            ticketDto.setId(ticket.getTicketId());
            ticketDto.setNumber(ticket.getTicketNumber());
            List<Prize> matchingPrize = prizes.stream().filter(p -> p.getTicketId()==ticket.getTicketId()).collect(Collectors.toList());
            if(!matchingPrize.isEmpty()){
                ticketDto.setPrizeDescription(matchingPrize.get(0).getDescription());
                ticketDto.setPrizePicId(matchingPrize.get(0).getFileId());
            }
            ticketDtos.add(ticketDto);
        }
        return  ticketDtos;
    }

    @Override
    public String getPrizePic(String photoId) {
        File photo = DriveService.getFile(photoId,photoId,JPG);
        return getBase64File(photo);
    }

    @Override
    public TimelineDto getTimeline(GameTimeline raffleTimeline) {
        TimelineDto timelineDto = new TimelineDto();
        timelineDto.setFreezeDate(convertDateToLocalDateTime(raffleTimeline.getFreezeDate()).format(FORMATTER_YYYY_MM_DD_HH_MM_SS));
        timelineDto.setActionDate(convertDateToLocalDateTime(raffleTimeline.getActionDate()).format(FORMATTER_YYYY_MM_DD_HH_MM_SS));
        return timelineDto;
    }

    @Override
    public String getTicketAvailability(int ticketId) {
        String availabilityMessage = "Ticket available";
        String formattedTickedId = "T" + String.format(FORMAT_7_NUMBER, ticketId);
        List<Ticket> tickets= TicketLocalServiceUtil.findTicketByYearAndNumber(getCurrentYear(),formattedTickedId);
        if (!tickets.isEmpty()) {
            availabilityMessage = "Ticket already taken";
        }
        return availabilityMessage;
    }

    @Override
    public void saveTickets(String loggedUserEmail, ActionRequest request, GameTimeline raffleTimeline) {
        if (getIstLocalDateTime().isBefore(convertDateToLocalDateTime(raffleTimeline.getActionDate()))) {
            int numTicket = ParamUtil.getInteger(request, "numTicket");
            Employee employee = EmployeeLocalServiceUtil.findByEmail(loggedUserEmail);
            int failedTicket = 0;
            boolean newTicketFlag = false;
            for (int count = 1; count <= numTicket; count++) {
                int ticketId = ParamUtil.getInteger(request, "ticketNum" + count);
                String formattedTickedId = "T" + String.format(FORMAT_7_NUMBER, ticketId);
                List<Ticket> tickets = TicketLocalServiceUtil.findTicketByYearAndNumber(getCurrentYear(), formattedTickedId);
                if (tickets.isEmpty()) {
                    Ticket newTicket = TicketLocalServiceUtil.createTicket(CounterLocalServiceUtil.increment());
                    newTicket.setEcode(employee.getEcode());
                    newTicket.setTicketNumber(formattedTickedId);
                    newTicket.setYear(getCurrentYear());
                    newTicket.setCreateDatetime(getIstDate());
                    newTicket.setDraw(false);
                    TicketLocalServiceUtil.addTicket(newTicket);
                    newTicketFlag = true;
                } else {
                    failedTicket++;
                }
            }
            //Send ticket id's to employees in email.
            if (newTicketFlag) {
                List<Ticket> tickets = TicketLocalServiceUtil.findTicketByYearAndEcode(getCurrentYear(), employee.getEcode());
                Set<String> ticketIds = tickets.stream().map(TicketModel::getTicketNumber).collect(Collectors.toSet());
                Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
                email.setSubject(SUBJECT_RAFFLE_TICKET);
                StringBuilder emailBody = new StringBuilder("<ol>");
                for (String ticketId : ticketIds) {
                    emailBody.append("<li>" + ticketId + "</li>");
                }
                emailBody.append("</ol>");
                email.setBody(MessageFormat.format(BODY_RAFFLE_TICKET, employee.getName(), (numTicket - failedTicket), emailBody.toString(), ticketIds.size() * 50));
                email.setToAddress(employee.getEmail());
                email.setCreatedDate(getIstDate());
                email.setScheduled(false);
                email.setModule(MODULE_GAME);
                email.setSent(false);
                EmailLocalServiceUtil.addEmail(email);
            }
        }
    }


}
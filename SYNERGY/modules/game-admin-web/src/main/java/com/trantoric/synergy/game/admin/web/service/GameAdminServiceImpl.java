package com.trantoric.synergy.game.admin.web.service;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.trantoric.synergy.game.admin.web.dto.PrizeDto;
import com.trantoric.synergy.game.admin.web.dto.TimelineDto;
import com.trantorinc.synergy.common.service.drive.DriveService;
import com.trantorinc.synergy.common.service.dto.ExcelDto;
import com.trantorinc.synergy.common.service.excel.ExcelService;
import com.trantorinc.synergy.email.core.model.Email;
import com.trantorinc.synergy.email.core.service.EmailLocalServiceUtil;
import com.trantorinc.synergy.game.core.model.*;
import com.trantorinc.synergy.game.core.service.GameTimelineLocalServiceUtil;
import com.trantorinc.synergy.game.core.service.PrizeLocalServiceUtil;
import com.trantorinc.synergy.game.core.service.SantaLocalServiceUtil;
import com.trantorinc.synergy.game.core.service.TicketLocalServiceUtil;
import com.trantorinc.synergy.lms.core.model.Employee;
import com.trantorinc.synergy.lms.core.service.DriveLocalServiceUtil;
import com.trantorinc.synergy.lms.core.service.EmployeeLocalServiceUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ResourceResponse;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.trantoric.synergy.game.admin.web.constants.GameAdminWebPortletKeys.*;
import static com.trantorinc.synergy.common.service.constant.AppConstantService.*;
import static com.trantorinc.synergy.common.service.util.UtilService.*;

public class GameAdminServiceImpl implements GameAdminService {

    @Override
    public TimelineDto getRaffleTimeline(GameTimeline raffleTime){
        TimelineDto raffleTimeline = new TimelineDto();
        raffleTimeline.setOpenDate(convertDateToLocalDateTime(raffleTime.getOpenDate()).format(FORMATTER_YYYY_MM_DD_HH_MM_SS));
        raffleTimeline.setFreezeDate(convertDateToLocalDateTime(raffleTime.getFreezeDate()).format(FORMATTER_YYYY_MM_DD_HH_MM_SS));
        raffleTimeline.setActionDate(convertDateToLocalDateTime(raffleTime.getActionDate()).format(FORMATTER_YYYY_MM_DD_HH_MM_SS));
        raffleTimeline.setEdit(getIstLocalDateTime().isBefore(convertDateToLocalDateTime(raffleTime.getActionDate())));
        return raffleTimeline;
    }
    @Override
    public TimelineDto getSantaTimelines(GameTimeline santaTime){
        TimelineDto santaTimeline = new TimelineDto();
        santaTimeline.setOpenDate(convertDateToLocalDateTime(santaTime.getOpenDate()).format(FORMATTER_YYYY_MM_DD));
        santaTimeline.setFreezeDate(convertDateToLocalDateTime(santaTime.getFreezeDate()).format(FORMATTER_YYYY_MM_DD));
        santaTimeline.setActionDate(convertDateToLocalDateTime(santaTime.getActionDate()).format(FORMATTER_YYYY_MM_DD));
        santaTimeline.setCloseDate(convertDateToLocalDateTime(santaTime.getCloseDate()).format(FORMATTER_YYYY_MM_DD));
        santaTimeline.setEdit(getIstLocalDateTime().toLocalDate().isBefore(convertDateToLocalDateTime(santaTime.getFreezeDate()).toLocalDate()));
        return santaTimeline;
    }

    @Override
    public List<PrizeDto> getPrizes(List<Prize> prizes) {
        List<PrizeDto> prizeDtos = new ArrayList<>();
        for(Prize prize : prizes){
            PrizeDto prizeDto = new PrizeDto();
            prizeDto.setId(prize.getPrizeId());
            prizeDto.setDescription(prize.getDescription());
            prizeDto.setSurprise(prize.getSurprise());
            prizeDto.setPrizePic(prize.getFileId());
            prizeDto.setSequence(prize.getSequence());
            prizeDtos.add(prizeDto);
        }
        return prizeDtos;
    }

    @Override
    public String getPrizePic(String photoId) {
        File photo = DriveService.getFile(photoId,photoId,JPG);
        return "\""+getBase64File(photo)+"\"";
    }

    @Override
    public void saveRaffleTimeline(String openDate, String freezeDate, String actionDate) {
        Date raffleOpenDate = convertLocalDateTimeToDate(LocalDateTime.parse(openDate,FORMATTER_YYYY_MM_DD_HH_MM_SS));
        Date raffleFreezeDate = convertLocalDateTimeToDate(LocalDateTime.parse(freezeDate,FORMATTER_YYYY_MM_DD_HH_MM_SS));
        Date raffleActionDate = convertLocalDateTimeToDate(LocalDateTime.parse(actionDate,FORMATTER_YYYY_MM_DD_HH_MM_SS));
        GameTimeline raffleTimeline = GameTimelineLocalServiceUtil.getCalibratedTimeline().stream().filter(g -> g.getName().equalsIgnoreCase("RAFFLE")).collect(Collectors.toList()).get(0);
        raffleTimeline.setOpenDate(raffleOpenDate);
        raffleTimeline.setFreezeDate(raffleFreezeDate);
        raffleTimeline.setActionDate(raffleActionDate);
        raffleTimeline.setCloseDate(convertLocalDateTimeToDate(getFinancialEndDate(getCurrentYear()).atStartOfDay()));
        GameTimelineLocalServiceUtil.updateGameTimeline(raffleTimeline);
    }

    @Override
    public void saveSantaTimeline(String openDate, String freezeDate, String actionDate, String closeDate) {
        Date santaOpenDate = convertLocalDateTimeToDate(LocalDate.parse(openDate,FORMATTER_YYYY_MM_DD).atStartOfDay());
        Date santaFreezeDate = convertLocalDateTimeToDate(LocalDate.parse(freezeDate,FORMATTER_YYYY_MM_DD).atStartOfDay());
        Date santaActionDate = convertLocalDateTimeToDate(LocalDate.parse(actionDate,FORMATTER_YYYY_MM_DD).atStartOfDay());
        Date santaCloseDate = convertLocalDateTimeToDate(LocalDate.parse(closeDate,FORMATTER_YYYY_MM_DD).atStartOfDay());
        GameTimeline santaTimeline = GameTimelineLocalServiceUtil.getCalibratedTimeline().stream().filter(g -> g.getName().equalsIgnoreCase("SANTA")).collect(Collectors.toList()).get(0);
        santaTimeline.setOpenDate(santaOpenDate);
        santaTimeline.setFreezeDate(santaFreezeDate);
        santaTimeline.setActionDate(santaActionDate);
        santaTimeline.setCloseDate(santaCloseDate);
        GameTimelineLocalServiceUtil.updateGameTimeline(santaTimeline);
    }

    @Override
    public String updatePrizePic(long prizeId, File uploadedPic) {
        Prize prize = PrizeLocalServiceUtil.fetchPrize(prizeId);
        if(null != prize.getFileId()) {
            DriveService.deleteFile(prize.getFileId());
        }
        String driveId = DriveLocalServiceUtil.findFolderIdByFolderName(MODULE_GAME);
        String fileId = DriveService.uploadFile(driveId,prizeId+JPG,uploadedPic);
        prize.setFileId(fileId);
        PrizeLocalServiceUtil.updatePrize(prize);
        return "\""+ getBase64File(uploadedPic)+"\"";
    }

    @Override
    public void saveRafflePrize(ActionRequest actionRequest) throws PortalException {
        int totalRows = ParamUtil.getInteger(actionRequest, "totalRows");
        List<PrizeDto> prizeDtos = new ArrayList<>();
        for(int count=1; count<=totalRows; count++){
            PrizeDto prizeDto = new PrizeDto();
            prizeDto.setSequence(count);
            prizeDto.setDescription(ParamUtil.getString(actionRequest, "prizeName"+count));
            prizeDto.setSurprise(ParamUtil.getBoolean(actionRequest, "surprise"+count));
            prizeDtos.add(prizeDto);
        }
        int currentYear = getCurrentYear();
        List<Prize> prizes = PrizeLocalServiceUtil.findPrizesByYear(currentYear);
        for(Prize prize : prizes){
            List<PrizeDto> matchingPrizeDtos = prizeDtos.stream().filter(p -> p.getSequence() == prize.getSequence()).collect(Collectors.toList());
            if(!matchingPrizeDtos.isEmpty()){
                prize.setDescription(matchingPrizeDtos.get(0).getDescription());
                prize.setSurprise(matchingPrizeDtos.get(0).isSurprise());
                PrizeLocalServiceUtil.updatePrize(prize);
            } else {
                DriveService.deleteFile(prize.getFileId());
                PrizeLocalServiceUtil.deletePrize(prize.getPrizeId());
            }
        }
        if(totalRows > prizes.size()){
            for(int newCount = prizes.size()+1; newCount <= totalRows; newCount++){
                int finalNewCount = newCount;
                List<PrizeDto> matchingPrizeDtos = prizeDtos.stream().filter(p -> p.getSequence() == finalNewCount).collect(Collectors.toList());
                if(!matchingPrizeDtos.isEmpty()){
                    Prize newPrize = PrizeLocalServiceUtil.createPrize(CounterLocalServiceUtil.increment());
                    newPrize.setYear(currentYear);
                    newPrize.setSequence(newCount);
                    newPrize.setDescription(matchingPrizeDtos.get(0).getDescription());
                    newPrize.setSurprise(matchingPrizeDtos.get(0).isSurprise());
                    PrizeLocalServiceUtil.addPrize(newPrize);
                }
            }
        }
    }

    @Override
    public List<PrizeDto> getRaffleDrawPrizes(List<Prize> prizes) {
        Employee defaultUser = EmployeeLocalServiceUtil.fetchEmployee(DEFAULT_ECODE);
        String defaultPhotoId = defaultUser.getFileId();
        LocalDateTime now = getIstLocalDateTime();
        GameTimeline raffleTime = GameTimelineLocalServiceUtil.getCalibratedTimeline().stream().filter(g -> g.getName().equalsIgnoreCase("RAFFLE")).collect(Collectors.toList()).get(0);
        List<PrizeDto> prizeDtos = new ArrayList<>();
        boolean noFirstAction = true;
        for(Prize prize : prizes){
            PrizeDto prizeDto = new PrizeDto();
            prizeDto.setId(prize.getPrizeId());
            prizeDto.setDescription(prize.getDescription());
            prizeDto.setSurprise(prize.getSurprise());
            prizeDto.setPrizePic(prize.getFileId());
            prizeDto.setSequence(prize.getSequence());
            if(now.isAfter(convertDateToLocalDateTime(raffleTime.getActionDate()))){
                if(prize.getTicketId() != 0){
                    Ticket ticket = TicketLocalServiceUtil.fetchTicket(prize.getTicketId());
                    prizeDto.setTicketNumber(ticket.getTicketNumber());
                    String winnerEcode = prize.getWinner();
                    Employee winner = EmployeeLocalServiceUtil.fetchEmployee(winnerEcode);
                    prizeDto.setWinnerName(winner.getName());
                    prizeDto.setWinnerEcode(winnerEcode);
                    prizeDto.setWinnerAccount(winner.getAccount());
                    String photoId = defaultPhotoId;
                    if(null != winner.getFileId() && !winner.getFileId().equalsIgnoreCase(BLANK)){
                        photoId = winner.getFileId();
                    }
                    prizeDto.setWinnerPhoto(photoId);
                    prizeDto.setAction(false);
                    prizeDto.setView(true);
                } else {
                    prizeDto.setTicketNumber(BLANK);
                    if(noFirstAction){
                        prizeDto.setAction(true);
                        prizeDto.setView(true);
                        noFirstAction = false;
                    } else{
                        prizeDto.setAction(false);
                        prizeDto.setView(false);
                    }
                }
            } else {
                prizeDto.setAction(false);
                prizeDto.setView(true);
            }
            prizeDtos.add(prizeDto);
        }
        return prizeDtos;
    }

    @Override
    public void confirmWinner(long prizeId, long ticketId, int currentYear) {
        Ticket ticket = TicketLocalServiceUtil.fetchTicket(ticketId);
        Prize prize = PrizeLocalServiceUtil.fetchPrize(prizeId);
        prize.setTicketId(ticketId);
        prize.setWinner(ticket.getEcode());
        PrizeLocalServiceUtil.updatePrize(prize);
        //Send email to winners
        Employee employee = EmployeeLocalServiceUtil.fetchEmployee(ticket.getEcode());
        Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
        email.setToAddress(employee.getEmail());
        email.setCcAddress(DL_HR);
        email.setSubject(MessageFormat.format(SUBJECT_WINNER_TICKET, Integer.toString(currentYear)));
        email.setBody(MessageFormat.format(RAFFLE_WINNER_MAIL_BODY, employee.getName(), prize.getDescription(), Integer.toString(currentYear)));
        email.setModule(MODULE_GAME);
        email.setScheduled(false);
        email.setSent(false);
        email.setCreatedDate(getIstDate());
        EmailLocalServiceUtil.addEmail(email);
    }

    @Override
    public String getWinner() {
        int currentYear = getCurrentYear();
        PrizeDto winner  = new PrizeDto();
        List<Ticket> allTickets = TicketLocalServiceUtil.findTicketByYear(currentYear);
        List<Prize> allPrizes = PrizeLocalServiceUtil.findPrizesByYear(currentYear);
        List<Long> allTicketIds = allTickets.stream().filter(t->!t.getDraw()).map(TicketModel::getTicketId).collect(Collectors.toList());
        Set<Long> alreadyWinTickets = allPrizes.stream().map(PrizeModel::getTicketId).collect(Collectors.toSet());
        for(long s : alreadyWinTickets){
            allTicketIds.removeIf(t -> t == s);
        }
        Collections.shuffle(allTicketIds);
        Ticket winnerTicket = allTickets.stream().filter(t-> t.getTicketId()== allTicketIds.get(0)).collect(Collectors.toList()).get(0);
        Ticket tobeUpdatedTicket = TicketLocalServiceUtil.fetchTicket(winnerTicket.getTicketId());
        tobeUpdatedTicket.setDraw(true);
        TicketLocalServiceUtil.updateTicket(tobeUpdatedTicket);
        Employee employee = EmployeeLocalServiceUtil.fetchEmployee(winnerTicket.getEcode());
        winner.setTicketId(winnerTicket.getTicketId());
        winner.setTicketNumber(winnerTicket.getTicketNumber());
        winner.setWinnerEcode(employee.getEcode());
        winner.setWinnerName(employee.getName());
        winner.setWinnerAccount(employee.getAccount());
        String photoId;
        if(null != employee.getFileId() && !employee.getFileId().equalsIgnoreCase(BLANK) ){
            photoId = employee.getFileId();
        } else {
            Employee defaultUser = EmployeeLocalServiceUtil.fetchEmployee(DEFAULT_ECODE);
            photoId = defaultUser.getFileId();
        }
        winner.setWinnerPhoto(getBase64File(DriveService.getFile(photoId,employee.getEcode(),JPG)));
        List<Prize> employeePrize = allPrizes.stream().filter(p -> p.getWinner().equalsIgnoreCase(winnerTicket.getEcode())).collect(Collectors.toList());
        winner.setAction(!employeePrize.isEmpty());
        return convertToJson(winner);
    }

    @Override
    public void exportRaffle(ResourceResponse response) throws IOException {
        List<ExcelDto> raffleExcelDto = new ArrayList<>();
        int currentYear = getCurrentYear();
        ExcelDto sheet = new ExcelDto();
        sheet.setSheetName("All Tickets");
        sheet.setHeaders(HEADERS_RAFFLE_EXPORT);

        List<Ticket> tickets = TicketLocalServiceUtil.findTicketByYear(currentYear);
        List<Prize> prizes = PrizeLocalServiceUtil.findPrizesByYear(currentYear);
        List<Employee> employees = EmployeeLocalServiceUtil.findAllActiveEmployees();
        Set<String> ecodes = tickets.stream().map(Ticket::getEcode).collect(Collectors.toSet());
        List<List<String>> data = new ArrayList<>();
        for(String ecode : ecodes){
            List<String> lineData = new ArrayList<>();
            lineData.add(ecode);
            List<Employee> ecodeEmployees = employees.stream().filter(e -> e.getEcode().equalsIgnoreCase(ecode)).collect(Collectors.toList());
            if(!ecodeEmployees.isEmpty()){
                lineData.add(ecodeEmployees.get(0).getName());
            } else {
                lineData.add(BLANK);
            }

            long count = tickets.stream().filter(t -> t.getEcode().equalsIgnoreCase(ecode)).count();
            lineData.add(String.valueOf(count));
            lineData.add(String.valueOf(count*50));
            List<Prize> employeePrizes = prizes.stream().filter(p -> p.getWinner().equalsIgnoreCase(ecode)).collect(Collectors.toList());
            if(!employeePrizes.isEmpty()){
                lineData.add(employeePrizes.get(0).getDescription());
            } else {
                lineData.add(BLANK);
            }
            data.add(lineData);
        }
        sheet.setData(data);
        sheet.setSheetIndex(0);
        raffleExcelDto.add(sheet);
        ExcelService.createWorkBook(response,currentYear+"_RAFFLE_TICKETS",raffleExcelDto);
    }

    @Override
    public void exportSanta(ResourceResponse response) throws IOException {
        List<ExcelDto> santaExcelDto = new ArrayList<>();
        int currentYear = getCurrentYear();
        ExcelDto sheet = new ExcelDto();
        sheet.setSheetName("All Participants");
        sheet.setHeaders(HEADERS_SANTA_EXPORT);

        List<Santa> santas = SantaLocalServiceUtil.findSantaByYear(currentYear);
        List<Employee> employees = EmployeeLocalServiceUtil.findAllActiveEmployees();
        List<List<String>> data = new ArrayList<>();
        for(Santa santa : santas){
            List<String> lineData = new ArrayList<>();
            lineData.add(santa.getEcode());
            List<Employee> ecodeEmployees = employees.stream().filter(e -> e.getEcode().equalsIgnoreCase(santa.getEcode())).collect(Collectors.toList());
            if(!ecodeEmployees.isEmpty()){
                lineData.add(ecodeEmployees.get(0).getName());
            } else {
                lineData.add(BLANK);
            }
            lineData.add(santa.getMobile());
            String secretSanta = santa.getSantaEcode();
            String finalSecretSanta = secretSanta;
            List<Employee> santaEcodeEmployees = employees.stream().filter(e -> e.getEcode().equalsIgnoreCase(finalSecretSanta)).collect(Collectors.toList());
            if(!santaEcodeEmployees.isEmpty()){
                secretSanta = santaEcodeEmployees.get(0).getName() + "("+secretSanta+")";
            }
            lineData.add(secretSanta);
            lineData.add(santa.getSantaEcode().equalsIgnoreCase(santa.getGuessedEcode()) ? YES : NO);
            lineData.add(santa.isGiftSent() ? YES : NO);
            lineData.add(santa.isEmailSent() ? YES : NO);
            data.add(lineData);
        }
        sheet.setData(data);
        sheet.setSheetIndex(0);
        santaExcelDto.add(sheet);
        ExcelService.createWorkBook(response,getCurrentYear()+"_SECRET_SANTA",santaExcelDto);
    }
}
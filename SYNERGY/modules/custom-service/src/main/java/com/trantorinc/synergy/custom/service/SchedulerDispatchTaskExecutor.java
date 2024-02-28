package com.trantorinc.synergy.custom.service;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.dispatch.executor.BaseDispatchTaskExecutor;
import com.liferay.dispatch.executor.DispatchTaskExecutor;
import com.liferay.dispatch.executor.DispatchTaskExecutorOutput;
import com.liferay.dispatch.model.DispatchTrigger;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.trantorinc.synergy.custom.service.task.*;
import com.trantorinc.synergy.lms.core.model.Scheduler;
import com.trantorinc.synergy.lms.core.service.SchedulerLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import java.time.LocalDateTime;

import static com.trantorinc.synergy.common.service.constant.AppConstantService.*;
import static com.trantorinc.synergy.common.service.util.UtilService.*;
import static com.trantorinc.synergy.common.service.util.UtilService.convertLocalDateTimeToDate;

@Component(
        property = {
                "dispatch.task.executor.name=schedulerTask", "dispatch.task.executor.type=schedulerTask"
        },
        service = DispatchTaskExecutor.class
)
public class SchedulerDispatchTaskExecutor extends BaseDispatchTaskExecutor {

    private static final Log log = LogFactoryUtil.getLog(SchedulerDispatchTaskExecutor.class);
    @Override
    public void doExecute(DispatchTrigger dispatchTrigger, DispatchTaskExecutorOutput dispatchTaskExecutorOutput) {

        LocalDateTime now = getIstLocalDateTime();
        log.info("######## SYNERGY - SCHEDULER TRIGGERED AT "+now+" ################");

        // 1.25 AM
        if((now.getHour() == 1) && (now.getMinute() <=30)){
            LmsTaskService.execute(now);
        }

        // 5.25 AM
        else if ((now.getHour() == 5) && (now.getMinute() <= 30)) {
            GameTaskService.execute(now);
        }

        // 5.55 AM
        else if((now.getHour() == 5) && (now.getMinute() > 30)) {
            ProbationTaskService.executeAm(now);
        }

        // 6.25 AM
        else if((now.getHour() == 6) && (now.getMinute() <= 30)) {
            testSchedulerEntry(now,MODULE_EXIT,TASK_AM_EXIT);
        }

        // 7.25 AM
        else if((now.getHour() == 7) && (now.getMinute() <= 30)) {
            SkillTaskService.executeAm(now);
        }

        // 10.55 AM
        else if((now.getHour() == 10) && (now.getMinute() > 30)) {
            GenericTaskService.execute(now);
        }

        // 1.55 PM
        else if((now.getHour() == 13) && (now.getMinute() > 30)) {
            SkillTaskService.executePm(now);
        }

        // 2.25 PM
        else if((now.getHour() == 14) && (now.getMinute() <= 30)) {
            LmsTaskService.execute(now);
        }

        // 2.55 PM
        else if((now.getHour() == 14) && (now.getMinute() > 30)) {
            ProbationTaskService.executePm(now);
        }

        //Deployment window

        // 8.55 PM
        else if((now.getHour() == 20) && (now.getMinute() > 30)) {
            testSchedulerEntry(now,MODULE_EXIT,TASK_PM_EXIT);
        }

        // 11.55 PM
        else if((now.getHour() == 23) && (now.getMinute() > 30)) {
          PerformanceTaskService.execute(now);
        }

        now = getIstLocalDateTime();
        log.info("######## SYNERGY - SCHEDULER COMPLETED "+now+" ################");
    }

    @Override
    public String getName() {
        return "schedulerTask";
    }

    //TODO :
    private void testSchedulerEntry(LocalDateTime now, String moduleName, String taskName){
        log.info(moduleName + " task started !");
        Scheduler scheduler = SchedulerLocalServiceUtil.findSchedulerByNameAndDate(taskName, getStartOfDayDate(now.toLocalDate()));
        if(null == scheduler){
            scheduler = SchedulerLocalServiceUtil.createScheduler(CounterLocalServiceUtil.increment());
            scheduler.setName(taskName);
            scheduler.setStatus(false);
            scheduler.setOnDate(getStartOfDayDate(now.toLocalDate()));
            scheduler.setRunDate(convertLocalDateTimeToDate(now));
            SchedulerLocalServiceUtil.addScheduler(scheduler);
        } else {
            scheduler.setStatus(false);
            scheduler.setRunDate(convertLocalDateTimeToDate(now));
            SchedulerLocalServiceUtil.updateScheduler(scheduler);
        }
        log.info(moduleName + " task preset !!");

        scheduler.setStatus(true);
        SchedulerLocalServiceUtil.updateScheduler(scheduler);
        log.info(moduleName + " task completed !!!");


    }


}

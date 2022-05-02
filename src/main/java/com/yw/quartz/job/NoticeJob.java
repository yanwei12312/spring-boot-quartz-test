package com.yw.quartz.job;

import com.yw.quartz.entity.NoticeEntity;
import com.yw.quartz.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yw
 * @since 2022/05/01
 */

@Slf4j
public class NoticeJob implements Job {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    NoticeService noticeService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        log.info("定时任务执行了");

        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

        String id = context.getJobDetail().getJobDataMap().getString("id");
        String startTime = context.getJobDetail().getJobDataMap().getString("startTime");
        String endTime = context.getJobDetail().getJobDataMap().getString("endTime");

        NoticeEntity noticeEntity = new NoticeEntity();
        noticeEntity.setId(id);
        noticeEntity.setStartTime(new Date(Long.parseLong(startTime)));
        noticeEntity.setEndTime(new Date(Long.parseLong(endTime)));

        noticeService.updateStatus(noticeEntity);
    }
}

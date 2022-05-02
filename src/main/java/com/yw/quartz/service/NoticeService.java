package com.yw.quartz.service;

import com.yw.quartz.entity.NoticeEntity;
import com.yw.quartz.job.NoticeJob;
import com.yw.quartz.mapper.NoticeMapper;
import com.yw.quartz.utils.CronUtils;
import com.yw.quartz.utils.QuartzUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yw
 * @since 2022/05/01
 */

@Service
public class NoticeService  {

    @Autowired
    NoticeMapper noticeMapper;
    @Autowired
    QuartzUtils quartzUtils;

    public void create(NoticeEntity noticeEntity) {
        setStatus(noticeEntity);
//        quartzUtils.addCronJob(noticeEntity.getId(),);
        noticeMapper.insert(noticeEntity);
    }

    public void update(NoticeEntity noticeEntity) {
        setStatus(noticeEntity);
        noticeMapper.updateById(noticeEntity);
    }

    public void updateStatus(NoticeEntity noticeEntity) {
        setStatus(noticeEntity);
        noticeMapper.updateById(noticeEntity);
    }

    public void setStatus(NoticeEntity noticeEntity) {
        String startCron = CronUtils.createCronExpression(noticeEntity.getStartTime());
        String endCron = CronUtils.createCronExpression(noticeEntity.getEndTime());
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("id",noticeEntity.getId());
        dataMap.put("startTime", String.valueOf(noticeEntity.getStartTime().getTime()));
        dataMap.put("endTime", String.valueOf(noticeEntity.getEndTime().getTime()));

        quartzUtils.removeJob(noticeEntity.getId()+"_start");
        quartzUtils.removeJob(noticeEntity.getId()+"_end");
        if(new Date().before(noticeEntity.getStartTime())) {
            // 待运行
            noticeEntity.setStatus("1");
            quartzUtils.addCronJob(noticeEntity.getId()+"_start",startCron,dataMap, NoticeJob.class);
        } else {
            if(new Date().after(noticeEntity.getEndTime())) {
                // 已过期
                noticeEntity.setStatus("3");
            } else {
                // 正在运行
                noticeEntity.setStatus("2");
                quartzUtils.addCronJob(noticeEntity.getId()+"_end",endCron,dataMap, NoticeJob.class);
            }
        }
    }
}

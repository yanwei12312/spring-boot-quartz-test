package com.yw.quartz.controller;

import com.yw.quartz.entity.NoticeEntity;
import com.yw.quartz.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yw
 * @since 2022/05/01
 */

@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    @PostMapping("/create")
    public String createNotice(@RequestBody NoticeEntity noticeEntity) {
        noticeService.create(noticeEntity);
        return "success";
    }

    @PostMapping("/update")
    public String updateNotice(@RequestBody NoticeEntity noticeEntity) {
        noticeService.update(noticeEntity);
        return "success";
    }

}

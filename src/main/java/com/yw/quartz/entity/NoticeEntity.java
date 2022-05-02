package com.yw.quartz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;



/**
 * @author yw
 * @since 2022/05/01
 */

@TableName(value = "notice")
@Data
public class NoticeEntity {

    @TableId(value = "id",type = IdType.INPUT)
    private String id;

    private String title;

    private String content;

    private Date startTime;

    private Date endTime;

    private String status;

}

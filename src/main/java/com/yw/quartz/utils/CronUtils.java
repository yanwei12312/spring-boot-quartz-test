package com.yw.quartz.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yw
 * @since 2022/05/01
 */

@Slf4j
public class CronUtils {

    /***
     *
     * @param date
     * @param dateFormat : yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String formatDateByPattern(Date date, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String formatTimeStr = null;
        if (date != null) {
            formatTimeStr = sdf.format(date);
        }
        return formatTimeStr;
    }

    /***
     * convert Date to cron ,eg. "21 25 17 07 01 ? 2020"
     *
     * @param date : 时间点
     * @return
     */
    public static String createCronExpression(Date date) {
        String dateFormat = "ss mm HH dd MM ? yyyy";
        return formatDateByPattern(date, dateFormat);
    }

}

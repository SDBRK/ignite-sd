package com.lo.test;

import cn.hutool.core.date.DateUtil;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author RujiangLiu
 * @date 2022/12/14
 */
public class DateTranslateTest {

    @Test
    public void test() throws ParseException {
//        Date endTime = new Date();
//        String date = DateUtil.format(new Date(), "yyyy-MM-dd");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        endTime = sdf.parse(date + " " + "23:59:59");
//
//        System.out.println(endTime);
//        System.out.println(sdf.format(endTime));

//        Calendar calWeek = Calendar.getInstance();
//        calWeek.set(Calendar.DAY_OF_WEEK, calWeek.getActualMaximum(Calendar.DAY_OF_WEEK));
//        calWeek.add(Calendar.DAY_OF_WEEK, -1);
//        Date timeWeek = calWeek.getTime();
//        date = new SimpleDateFormat("yyyy-MM-dd").format(timeWeek) + " 23:59:59";
//        System.out.println(date);

//        Calendar calQuarter = Calendar.getInstance();
//        calQuarter.set(Calendar.DAY_OF_MONTH, 1);
//        calQuarter.set(Calendar.MONTH, calQuarter.get(Calendar.MONTH) / 3 * 3 + 2);
//        calQuarter.set(Calendar.DAY_OF_MONTH, calQuarter.getActualMaximum(Calendar.DAY_OF_MONTH));
//        Date timeQuarter = calQuarter.getTime();
//        date = new SimpleDateFormat("yyyy-MM-dd").format(timeQuarter) + " 23:59:59";
//        System.out.println(date);

//        LocalDate now = LocalDate.now();
//        Month month = Month.of(now.getMonth().firstMonthOfQuarter().getValue()).plus(2L);
//        LocalDateTime localDateTime =  LocalDateTime.of(LocalDate.of(now.getYear(), month, month.length(now.isLeapYear())), LocalTime.MAX);
////        date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
//        date = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        System.out.println(date);

        Date date = DateUtil.date();

        Date dateTime = DateUtil.parseDateTime("2022-12-18 17:00:00");

        long time = (date.getTime()-dateTime.getTime())/1000/60;

        System.out.println(time);

        System.out.println(24*60);


        long between = date.getTime()-dateTime.getTime();
        long day = between / (24 * 60 * 60 * 1000);
        long hour = (between / (60 * 60 * 1000) - day * 24);
        long a = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);

        System.out.println(a);

    }
}

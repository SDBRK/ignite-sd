package com.lo.test;

import cn.hutool.core.date.*;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author RujiangLiu
 * @date 2023/2/15
 */
public class DateFormatTest {

    @SneakyThrows
    public static void main(String[] args) {
        Date date = new Date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
//        String format = dateFormat.format(date);
//        String year = format.substring(0, 4);
//        System.out.println(year);
//        String month = format.substring(4, 6);
//        format = year.concat("01");
//        System.out.println(format);
//
//
//        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Shanghai"));
//        int y = calendar.get(Calendar.YEAR);
//        int m = calendar.get(Calendar.MONTH);
//
//        System.out.println(m);
//
//        System.out.println(y + String.valueOf(m));
//
//        LocalDateTime dateTime = LocalDateTime.now();
//        LocalDateTime firstDay = dateTime.with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN);
//        LocalDateTime lastDay = dateTime.with(TemporalAdjusters.lastDayOfMonth()).with(LocalTime.MAX);
//        Date startDate = Date.from(firstDay.atZone(ZoneId.systemDefault()).toInstant());
//        Date endDate = Date.from(lastDay.atZone(ZoneId.systemDefault()).toInstant());
//        System.out.println(firstDay);
//        System.out.println(lastDay);
//        System.out.println(startDate);
//        System.out.println(endDate);
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = dateFormat.parse("2023-02-23".replace("-", "") + " " + "10:52:00");
//        System.out.println(date);

//        Date tradingDate = DateUtil.offsetDay(new Date(), -1);
//        System.out.println(DateUtil.format(tradingDate,"yyyyMMdd"));

//        Date tradingDate = DateUtil.beginOfMonth(new Date());
//        tradingDate = DateUtil.offsetDay(tradingDate, 1);
//        String date = DateUtil.format(tradingDate,"yyyyMMdd");
//        System.out.println(date);
//

        int day = 11;

//        Date beginOfWeek = DateUtil.beginOfWeek(new Date(), false);
//        beginOfWeek = DateUtil.offsetDay(beginOfWeek, day);
//        System.out.println(beginOfWeek);
//        if (!beginOfWeek.after(dateFormat.parse("2023-03-02 00:00:10"))) {
//            beginOfWeek = DateUtil.offsetWeek(beginOfWeek, 1);
//        }
//        System.out.println(beginOfWeek);

//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(sdf.parse("20230226"));
////        int nowDay2 = calendar.get(Calendar.DAY_OF_WEEK);
//        Integer todayWeekday = (calendar.get(Calendar.DAY_OF_WEEK) + 5) % 7 + 1;
//        System.out.println(todayWeekday);

//        date = DateUtil.parse("20230316");
//        Date beginOfWeek = DateUtil.beginOfWeek(DateUtil.parse("20230316"));
//        int nowDay = (DateUtil.dayOfWeek(date) + 5) % 7 + 1;
//        int nowDay1 = (DateUtil.dayOfWeek(date) + 6) % 7 + 1;
//        System.out.println(nowDay);
//        System.out.println(nowDay1);
//        beginOfWeek = DateUtil.offsetWeek(beginOfWeek, 1);
//        beginOfWeek = DateUtil.offsetDay(beginOfWeek, day-1);
//        System.out.println(beginOfWeek);
        Date beginOfMonth = DateUtil.beginOfMonth(date);
        int nowDay = DateUtil.dayOfMonth(date);
        System.out.println(nowDay);
        if (day <= nowDay) {
            beginOfMonth = DateUtil.offsetMonth(beginOfMonth, 1);
        }
        beginOfMonth = DateUtil.offsetDay(beginOfMonth, day-1);
        System.out.println(beginOfMonth);

//        Date beginOfYear = DateUtil.beginOfYear(date);
//        int nowDay = DateUtil.dayOfYear(date);
//        System.out.println(nowDay);
//        if (day <= nowDay) {
//            beginOfYear = DateUtil.offset(beginOfYear, DateField.YEAR, 1);
//            beginOfYear = DateUtil.offsetDay(beginOfYear, day - 1);
//        }
//        System.out.println(beginOfYear);

//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        int nowDay = calendar.get(Calendar.DAY_OF_YEAR);
//        if (day <= nowDay) {
//            calendar.set(Calendar.DAY_OF_YEAR, 0);
//            calendar.add(Calendar.YEAR, 1);
//            calendar.add(Calendar.DAY_OF_YEAR, day);
//        }
//        System.out.println(sdf.format(calendar.getTime()));
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        int nowDay = calendar.get(Calendar.DAY_OF_MONTH);
//        if (day <= nowDay) {
//            calendar.add(Calendar.MONTH, 1);
//        }
//        calendar.set(Calendar.DAY_OF_MONTH, 0);
//        calendar.add(Calendar.DAY_OF_MONTH, day);
//        System.out.println(sdf.format(calendar.getTime()));

//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.DAY_OF_MONTH, 1);
//        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) / 3 * 3);
//        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
//        System.out.println(dateFormat.format(calendar.getTime()));

//        Date beginQuarter = DateUtil.beginOfQuarter(date);
//        System.out.println(beginQuarter);
//        // 第几个季度
//        int nowDay = DateUtil.quarter(date);
////        long between = DateUtil.between(beginQuarter, date, DateUnit.DAY);
//        long between = DateUtil.betweenDay(beginQuarter, date, true);
//        System.out.println(DateUtil.dayOfYear(date));
//        System.out.println(nowDay);
//        System.out.println(between);

//        Date beginOfQuarter = DateUtil.beginOfQuarter(date);
//        int nowDay = Math.toIntExact(DateUtil.betweenDay(beginOfQuarter, date, true)) + 1;
//        if (day <= nowDay) {
//            beginOfQuarter = DateUtil.offsetMonth(beginOfQuarter, 3);
//            beginOfQuarter = DateUtil.beginOfQuarter(beginOfQuarter);
//        }
//        System.out.println(beginOfQuarter);
//        System.out.println(DateUtil.offsetDay(beginOfQuarter, day - 1));

//        Date date1 = DateUtil.parseDate("2023-03-08 16:14:00");
//        System.out.println(date1);
//        Date date2 = DateUtil.parseTime("14:44:04");
//        System.out.println(date2);
//        Date date3 = DateUtil.parseDateTime("2023-03-08 14:44:04");
//        System.out.println(date3);
    }

    @Test
    public void offsetHour(){

        String eventDate = "2023-05-06";
        String time = "00:00:00";

        String date = eventDate+ " " +time;

        Date offsetDate =  DateUtil.offsetHour(DateUtil.parse(date, DatePattern.NORM_DATETIME_FORMATTER),-1);

        System.out.println(offsetDate);
    }

    @Test
    public void offsetMinutes(){

        String eventDate = "2023-05-16";
        String time = "15:10:00";

        String date = eventDate+ " " +time;

        Date offsetDate =  DateUtil.offsetMinute(DateUtil.parse(date, DatePattern.NORM_DATETIME_FORMATTER),5*-1);

        System.out.println(offsetDate);
    }

    @Test
    public void getF2(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -2);
        System.out.println(calendar.getTime());
    }

    @Test
    public void before(){
        Date startDate = DateUtil.parseDate("2024-05-30");
        Date endDate = DateUtil.parseDate("2024-05-31");

        System.out.println(startDate.before(endDate));
        System.out.println(endDate.after(startDate));
        System.out.println(startDate.compareTo(endDate));
    }

    @Test
    public void endOf(){
        System.out.println(DateUtil.endOfYear(new Date()).toDateStr());
    }

    @Test
    public void getTime(){
        String time = "15:10:00";
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        DateTime dateTime = DateUtil.parseTime(time);
        System.out.println(dateTime);
        System.out.println(DateUtil.formatTime(dateTime));
        System.out.println(timeFormat.format(dateTime));
    }
}

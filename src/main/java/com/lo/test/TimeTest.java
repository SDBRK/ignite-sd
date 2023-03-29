package com.lo.test;

import lombok.Data;
import org.junit.jupiter.api.Test;

/**
 * @author RujiangLiu
 * @date 2022/11/10
 */
public class TimeTest {

    @Test
    public void isIntersect(){

        Time[] times = new Time[2];

        times[0] = new Time("09:21","10:54");
        times[1] = new Time("06:54","08:23");

        for (Time time :times){
            int beginTime = Integer.parseInt(time.getBeginTime().replaceAll(":", ""));
            int endTime = Integer.parseInt(time.getBeginTime().replaceAll(":", ""));
            for (Time time2 :times){
                int beginTime2 =  Integer.parseInt(time2.getBeginTime().replaceAll(":", ""));
                int endTime2 =  Integer.parseInt(time2.getEndTime().replaceAll(":", ""));

                if (beginTime > beginTime2){
                    if (!(endTime > endTime2 && endTime > beginTime2)){
                        System.out.println(true);
                        break;
                    }
                }else if (beginTime < beginTime2){
                    if (!(endTime < endTime2 && endTime < beginTime2)){
                        System.out.println(true);
                        break;
                    }
                }else{
                    System.out.println(true);
                    break;//退不了双重循环 得换foreach
                }
            }
            System.out.println(false);
            return;
        }
    }

    @Data
    public static class Time{
        private String beginTime;
        private String endTime;

        public Time(String beginTime, String endTime) {
            this.beginTime = beginTime;
            this.endTime = endTime;
        }
    }


}

package com.lo.test;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

/**
 * @author RujiangLiu
 */
@Slf4j
public class Swapping {

    public static void main(String[] args) {
        try {
            Integer a = 1;
            Integer b = 2;
            swap(a, b);
            System.out.printf("a = %s, b = %s", a, b);
        } catch (Exception e) {
            log.error("空指针了朋友:", e);
//            e.printStackTrace();
        }
    }

    private static void swap(Integer a, Integer b) {
//        try {
//            int temp = a.intValue();
//            Field field = Integer.class.getDeclaredField("value");
//            field.setAccessible(true);
//            field.set(a, b);
//            field.set(b, new Integer(temp));
//        } catch (NoSuchFieldException | IllegalAccessException e) {
//            log.error("出错了", e);
//        }
        throw new NullPointerException();
    }
}

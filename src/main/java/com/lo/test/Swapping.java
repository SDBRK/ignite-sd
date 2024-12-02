package com.lo.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    /**
     * 测试形参传递
     */
    @Test
    public void transfer(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);

        int[] ints = new int[]{1, 2, 3, 4};

        String s = "world";
        int a = 3;

        // 改变
        change(list, ints);
        // 不变
        change(s, a);

        list.forEach(System.out::println);
        System.out.println(Arrays.toString(ints));

        System.out.println(s);
        System.out.println(a);
    }


    public void change(List<Integer> list, int[] ints){
        list.add(3);
        ints[0] = 4;
    }

    public void change(String s, int a){
        s = "hello";
        a = 5;
    }




}

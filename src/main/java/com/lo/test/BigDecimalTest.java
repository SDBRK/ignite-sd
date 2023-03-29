package com.lo.test;

import java.math.BigDecimal;

/**
 * @author RujiangLiu
 */
public class BigDecimalTest {

    public static void main(String[] args) {
/*        System.out.println(0.05 + 0.01);
        System.out.println(1.0 - 0.42);
        System.out.println(4.015 * 100);
        System.out.println(123.3 / 100);*/

        BigDecimal a = new BigDecimal(String.valueOf(1.01));
        BigDecimal b = new BigDecimal(String.valueOf(1.02));
        BigDecimal c = new BigDecimal("1.01");
        BigDecimal d = new BigDecimal("1.02");
        System.out.println(a.add(b));
        System.out.println(c.add(d));
    }

}

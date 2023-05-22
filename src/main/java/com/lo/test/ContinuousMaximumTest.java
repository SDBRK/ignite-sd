package com.lo.test;

import org.junit.jupiter.api.Test;

/**
 * @author RujiangLiu
 * @date 2023/5/5
 */
public class ContinuousMaximumTest {

    @Test
    public void test() {
//        String str = "123442567811";
        String str = "596123442567811";
        int max = 1;
        int maxStart = 0;
        int start = 0;
        int current = 1;

        while (start < str.length() - 1) {
            if (max > str.length() - 1 - start) {
                break;
            }
            String inxStr = str.substring(start, current);
            String currentStr = str.substring(current, current + 1);
            int inx = inxStr.indexOf(currentStr);
//            int inx = str.substring(start, current).indexOf(str.substring(current, current + 1));
            if (inx < 0 && current - start >= max) {
                maxStart = start;
                max = current - start + 1;
            }
            if (inx > -1) {
                start = start + inx + 1;
                current = start + 1;
                continue;
            }
            if (current >= str.length() - 1) {
                max += 1;
                break;
            }
            current += 1;
        }
        System.out.println(str.substring(maxStart, maxStart + max));
    }

    @Test
    public void test2() {
//        String str = "123442567811";
        String str = "596123442567811";
        int max = 1;
        int maxStart = 0;
        int start = 0;
        int current = 2;

        while (start < str.length() - 1) {
            if (max > str.length() - 1 - start) {
                break;
            }
            String inxStr = str.substring(start, current);
            String currentStr = str.substring(current, current + 1);
            int inx = inxStr.indexOf(currentStr);
            if (current - start >= max) {
                maxStart = start;
                max = current - start;
            }
            if (inx > -1 && current < str.length() - 1) {
                start = start + inx + 1;
                current = start + 1;
                continue;
            }
            if (current >= str.length() - 1) {
                if (inx == -1) {
                    max += 1;
                }
                break;
            }
            current += 1;
        }
        System.out.println(str.substring(maxStart, maxStart + max));
    }
}

package com.lo.test;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author RujiangLiu
 * @date 2024/11/13
 */
public class TranslateTest {

    // 一
    //【3 4 3 3】4
    //【9 1 7 9 7 9 7】1
    //【0 1 1 1】-1
    // nums=[3 4 3 3]

    @Test
    public void distinct() {
        int[] nums = {3, 4, 3, 3};
//        int[] nums = {9, 1, 7, 9, 7, 9, 7};
//        int[] nums = {0, 1, 1, 1};
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
        }
        for (Integer c : map.keySet()) {
            if (map.get(c) == 1) {
                if (c > 0) {
                    System.out.println(c);
                } else {
                    System.out.println(-1);
                }
            }
        }
    }

    // 二
    // 使用递归
    // A=1 B=10  10
    // A=3 B=4 12
    // 不允许用 * 乘号，可以使用加号 减号 位移

    @Test
    public void noMulti() {
        int rs = add(1, 10, 0);
        System.out.println(rs);
    }

    public int add(int a, int b, int x) {
        if (b == 0) {
            return x;
        } else {
            return add(a, b - 1, x + a);
        }
    }

    // 三
    // a = '3[a]2[bc]' 'aaa bcbc';
    // b = '3[a2[bc]]' 'a bcbc a bcbc a bcbc';

    @Test
    public void repeat() {
//        String s = "3[a]2[bc]";
//        String s = "3[a2[bc]]";
        String s = "3[a10[bc]2[de]]";
        // 重新循环遍历
        do {
            int iStart = s.lastIndexOf("[");
            int iEnd = s.indexOf("]", iStart);
            System.out.println("iStart:" + iStart + " " + "iEnd:" + iEnd);
            // 第一个需要替换的字符串
            String replaceStr = s.substring(iStart + 1, iEnd);
            System.out.println("需要替换的字符串replaceStr:" + replaceStr);
            // 找到循环的次数
            int repeatNum = 0;
            int repeatIndex = 0;
            for (int i = iStart; i > 0; i--) {
                try {
                    // 找到第一个数字
                    int num = Integer.parseInt(String.valueOf(s.charAt(i - 1)));
                    if (repeatNum >= 0  && num > 0) {
                        num = (int) (num * Math.pow(10, repeatIndex) + repeatNum);
                    }
                    repeatNum = num;
                    repeatIndex++;
                } catch (NumberFormatException e) {
                    // 不是数字，说明不是循环次数
                    break;
                }
            }
            if (repeatNum >= 0) {
                System.out.println("循环次数repeatNum:" + repeatNum);
                // 转换成需要的字符串
                StringBuilder repeatStr = new StringBuilder();
                for (int j = 0; j < repeatNum; j++) {
                    repeatStr.append(replaceStr);
                }
                System.out.println("重复的字符串repeatStr:" + repeatStr);
                // 记录字符串前一段+需要的字符串+后一段 拼接后，继续找需要替换的字符串
                s = s.substring(0, iStart - repeatIndex) + repeatStr + s.substring(iEnd + 1);
                System.out.println("替换后的字符串s:" + s);
            }
        } while (s.contains("[") && s.contains("]"));
        System.out.println("最终的字符串s:" + s);
    }
}

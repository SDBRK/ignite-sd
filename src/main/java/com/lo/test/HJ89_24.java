package com.lo.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author RujiangLiu
 * @date 2023/4/10
 */
public class HJ89_24 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        input(in);
    }

    private static void input(Scanner in) {
        String a = in.next();
        String b = in.next();
        String c = in.next();
        String d = in.next();
        int numA = getNum(a);
        int numB = getNum(b);
        int numC = getNum(c);
        int numD = getNum(d);
        if (numA == -1 || numB == -1 || numC == -1 || numD == -1) {
            System.out.print("ERROR");
            return;
        }
        if (!printResult(getNums(a, b, c, d), getOps())) {
            System.out.print("NONE");
        }
    }

    /**
     * 打印结果
     */
    private static boolean printResult(List<String> numList, List<String> opsList) {
        for (String item : numList) {
            String[] strs = item.split(",");
            int a = getNum(strs[0]);
            int b = getNum(strs[1]);
            int c = getNum(strs[2]);
            int d = getNum(strs[3]);
            for (String opStr : opsList) {
                int abResult = calc(a, b, opStr.charAt(0));
                if (opStr.charAt(0) == '/' && a % b != 0) {
                    continue;
                }
                int abcResult = calc(abResult, c, opStr.charAt(1));
                if (opStr.charAt(1) == '/' && abResult % c != 0) {
                    continue;
                }
                int abcdResult = calc(abcResult, d, opStr.charAt(2));
                if (opStr.charAt(2) == '/' && abcResult % d != 0) {
                    continue;
                }
                if (abcdResult == 24) {
                    System.out.print(strs[0] + String.valueOf(opStr.charAt(0)) + strs[1] + String.valueOf(opStr.charAt(1)) + strs[2] + String.valueOf(opStr.charAt(2)) + strs[3]);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 数字转换
     */
    private static int getNum(String str) {
        switch (str) {
            case "A":
                return 1;
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case "10":
                return Integer.parseInt(str);
            case "J":
                return 11;
            case "Q":
                return 12;
            case "K":
                return 13;
            default:
                return -1;
        }
    }

    /**
     * 获取扑克牌的全排列
     */
    private static List<String> getNums(String a, String b, String c, String d) {
        List<String> numList = new ArrayList<>();
        for (int indexA = 0; indexA < 4; indexA++) {
            for (int indexB = 0; indexB < 4; indexB++) {
                for (int indexC = 0; indexC < 4; indexC++) {
                    for (int indexD = 0; indexD < 4; indexD++) {
                        if (indexA == indexB || indexA == indexC || indexA == indexD || indexB == indexC || indexB == indexD || indexC == indexD) {
                            continue;
                        }
                        String[] nums = new String[4];
                        nums[indexA] = a;
                        nums[indexB] = b;
                        nums[indexC] = c;
                        nums[indexD] = d;
                        numList.add(String.join(",", nums));
                    }
                }
            }
        }
        return numList;
    }

    /**
     * 获取所有操作符
     */
    private static List<String> getOps() {
        // 0,1,2,3=> *,/,+,-
        char[] chs = new char[]{'*', '/', '+', '-'};
        List<String> opsList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    String s = chs[i] + String.valueOf(chs[j]) + chs[k];
                    if (i > 1) {
                        if (j > 1 && k > 1) {
                            opsList.add(s);
                        }
                    } else if (j > 1) {
                        if (k > 1) {
                            opsList.add(s);
                        }
                    } else {
                        opsList.add(s);
                    }
                }
            }
        }
        return opsList;
    }

    /**
     * 计算两个数的操作
     */
    private static int calc(int a, int b, char op) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
            default:
                return 0;
        }
    }
}

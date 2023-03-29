package com.lo.test;

import java.util.*;

/**
 * @author RujiangLiu
 */
public class Solution {

    public static void main(String[] args) {
//        int[] nums = {2, 1, 3, 3, 5, 4, 4, 6};
//        int k = 4;
        int[] nums = {3, 2, 1, 2, 3, 4, 3, 4, 5, 9, 10, 11};
        int k = 3;
        System.out.println(isPossibleDivide(nums, k));
    }

    public static boolean isPossibleDivide(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }
        System.out.println("第一次遍历：" + map);
        List<Integer> list = new ArrayList<>(map.keySet());
        Collections.sort(list);
        System.out.println("数组排序后：" + list);
        for (int i = 0; i <= list.size() - k; i++) {
            if (map.get(list.get(i)) == 0) {
                continue;
            }
            int cur = map.get(list.get(i));
            map.put(list.get(i), 0);
            int currentIndex = list.get(i);
            for (int j = 1; j < k; j++) {
                if (map.get(list.get(i + j)) < cur || list.get(i + j) - currentIndex != 1) {
                    return false;
                } else {
                    map.put(list.get(i + j), map.get(list.get(i + j)) - cur);
                }
                currentIndex = list.get(i + j);
            }

        }
        for (int i : map.values()) {
            if (i != 0) {
                return false;
            }
        }
        System.out.println(map);
        return true;
    }
}

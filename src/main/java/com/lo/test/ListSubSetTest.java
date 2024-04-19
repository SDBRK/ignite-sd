package com.lo.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author RujiangLiu
 * @date 2024/2/22
 */
public class ListSubSetTest {

    public static void main(String[] args) {
        List<String> originalSet = new ArrayList<>();
        originalSet.add("事前");
        originalSet.add("事中");
        originalSet.add("事后");

        List<List<String>> subsets = generateSubsets(originalSet);

        // 打印所有子集
        for (List<String> subset : subsets) {
            System.out.println(subset);
        }
    }

    public static List<List<String>> generateSubsets(List<String> set) {
        List<List<String>> subsets = new ArrayList<>();
        backtrack(set, new ArrayList<>(), subsets, 0);
        return subsets;
    }

    private static void backtrack(List<String> set, List<String> tempSet, List<List<String>> subsets, int start) {
        // 将当前临时集合添加到结果集中
        subsets.add(new ArrayList<>(tempSet));

        // 遍历集合中的每个元素
        for (int i = start; i < set.size(); i++) {
            // 将当前元素添加到临时集合中
            tempSet.add(set.get(i));
            // 递归，继续考虑下一个元素
            backtrack(set, tempSet, subsets, i + 1);
            // 回溯，移除最后一个添加到临时集合中的元素
            tempSet.remove(tempSet.size() - 1);
        }
    }
}

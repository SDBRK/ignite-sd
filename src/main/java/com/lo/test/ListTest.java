package com.lo.test;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author RujiangLiu
 * @date 2023/3/9
 */
public class ListTest {

    public static void main(String[] args) {

        List<Long> l1 = new ArrayList<>();
        l1.add(1L);
        l1.add(2L);

        List<Long> l2 = new ArrayList<>();
        l2.add(1L);
        l2.add(2L);
        l2.add(3L);

        System.out.println(l2.containsAll(l1));
    }

}

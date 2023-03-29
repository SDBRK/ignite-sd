package com.lo.test;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

import static java.lang.System.out;

/**
 * @author RujiangLiu
 */
public class StreamDicTest {

    @Test
    public void listDistinctByStreamDistinct() {
        // 1. 对于 Student 列表去重
        List<Student> studentList = new ArrayList<>();
        Student s1 = new Student();
        s1.setName("Tom");
        s1.setStuNo("001");
        studentList.add(s1);
        Student s2 = new Student();
        s2.setName("Tom");
        s2.setStuNo("001");
        studentList.add(s2);
        Student s3 = new Student();
        s3.setName("Tom");
        s3.setStuNo("002");
        studentList.add(s3);

        out.print("去重前：");
        out.println(studentList);
        studentList = studentList.stream().distinct().collect(Collectors.toList());
        out.print("distinct去重后：");
        out.println(studentList);
//        studentList = studentList.stream().collect(
//                collectingAndThen(
//                        toCollection(() -> new TreeSet<>(Comparator.comparing(Student::getName))), ArrayList::new
//                )
//        );
        studentList = studentList.stream().filter(distinctByKey(Student::getName)).collect(Collectors.toList());
        out.print("根据名字去重后：");
        out.println(studentList);
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

}

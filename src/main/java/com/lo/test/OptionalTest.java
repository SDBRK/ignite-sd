package com.lo.test;

import java.util.Optional;

/**
 * @author RujiangLiu
 * @date 2024/8/19
 */
public class OptionalTest {

    public static String getStr(){
        return "有值的";

    }
    public static String createStr(){
        return "创造值的";
    }

    public static void main(String[] args) {
        String s = Optional.ofNullable(getStr()).orElseGet(OptionalTest::createStr);
        System.out.println(s);
        String s1 = Optional.ofNullable(getStr()).orElse(createStr());
        System.out.println(s1);
    }

    /**
     * 饿汉式单例模式
     * 优点：线程安全，延迟加载，占用内存小
     * 缺点：类加载时就初始化，浪费资源
     */
    public static class Singleton {
        private static final Singleton instance = new Singleton();

        private Singleton() {
        }

        public static Singleton getInstance() {
            return instance;
        }
    }

    /**
     * 懒汉式单例模式
     * 优点：线程安全，延迟加载，占用内存小
     * 缺点：每次调用getInstance方法都要同步，浪费资源
     */
    public static class Singleton1 {
        private static Singleton1 instance;

        private Singleton1() {
        }

        public static synchronized Singleton1 getInstance() {
            if (instance == null) {
                instance = new Singleton1();
            }
            return instance;
        }
    }

}

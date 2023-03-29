package com.lo.test;

import cn.hutool.core.util.StrUtil;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import java.util.Arrays;

/**
 * @author RujiangLiu
 */
public class StringSubTest {


    @Test
    public void test(){
        String a = "*kkk";
        String b = "ooo*";
        String c = "kkk*ooo";

        String[] d = a.split("\\*");
        String[] e = b.split("\\*");
        String[] f = c.split("\\*");

        System.out.println(Arrays.toString(d));
        System.out.println(Arrays.toString(e));
        System.out.println(Arrays.toString(f));


        System.out.println(d.length);
        System.out.println(e.length);
        System.out.println(f.length);

        System.out.println("".equals(d[0]));
        System.out.println(d[1]);

//        String remoteFilePath = "/data/aaa/bb/wb*ss.txt";
//        int lastIndex = remoteFilePath.lastIndexOf("/")+1;
//        int lastIndex2 = remoteFilePath.lastIndexOf(".");
//
//        String[] match = remoteFilePath.substring(lastIndex,lastIndex2).split("\\*");
//        String remoteFilePath1 = remoteFilePath.substring(0, lastIndex);
//
//        String remoteFilePath2 = remoteFilePath.substring(lastIndex2);
//
//        System.out.println(lastIndex);
//        System.out.println(Arrays.toString(match));
//        System.out.println(remoteFilePath1);
//        System.out.println(remoteFilePath2);
    }

    @Test
    public void test2(){

        String a = "a.xyz.jpg";

        String b = StrUtil.removeSuffix(a,".*");

        System.out.println(b);

    }
}

package com.lo.test.AES;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * @author zzx
 */
public class MD5 {
    public static String md5(String s) {
        byte[] btInput = s.getBytes(StandardCharsets.UTF_8);
        return md5(btInput);
    }

    public static String md5(byte[] btInput) {
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            throw new IllegalStateException("md5加密失败: ", e);
        }
    }

    public static void main(String[] args) {
        System.out.println(MD5.md5("123456"));
    }

}

package com.lo.test.AES;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Random;

/**
 * @author RujiangLiu
 * @date 2024/4/10
 */
public class AESTest {

    private static final String SYSTEM_SECRET = "operations-manage-platform";

    public AESTest() {
    }

    private static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; ++i) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }

        return sb.toString();
    }

    public static String encrypt(String content) {
        return encrypt(content, SYSTEM_SECRET);
    }

    public static String encrypt(String content, String password) {
        try {
            String randomKey = getRandomString(16);
            byte[] signaturePart1 = encryptInner(randomKey.getBytes(StandardCharsets.UTF_8), password);
            byte[] signaturePart2 = encryptInner(content.getBytes(StandardCharsets.UTF_8), randomKey);
            byte[] data = new byte[signaturePart1.length + signaturePart2.length];
            System.arraycopy(signaturePart1, 0, data, 0, signaturePart1.length);
            System.arraycopy(signaturePart2, 0, data, signaturePart1.length, signaturePart2.length);
            return Base64.getEncoder().encodeToString(data);
        } catch (Exception exception) {
            throw new IllegalStateException("加密失败", exception);
        }
    }

    private static byte[] encryptInner(byte[] content, String password) throws Exception {
        byte[] key = HexUtils.fromHexString(MD5.md5(password));
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(1, new SecretKeySpec(key, "AES"), new IvParameterSpec(key));
        return cipher.doFinal(content);
    }

    private static byte[] decryptInner(byte[] content, String password) throws Exception {
        byte[] key = HexUtils.fromHexString(MD5.md5(password));
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(2, new SecretKeySpec(key, "AES"), new IvParameterSpec(key));
        return cipher.doFinal(content);
    }

    public static String decrypt(String content) {
        return decrypt(content, SYSTEM_SECRET);
    }

    public static String decrypt(String content, String password) {
        try {
            byte[] bytes = Base64.getDecoder().decode(content.getBytes(StandardCharsets.UTF_8));
            byte[] randomKeyByte = new byte[32];
            byte[] contentByte = new byte[bytes.length - 32];
            System.arraycopy(bytes, 0, randomKeyByte, 0, 32);
            System.arraycopy(bytes, 32, contentByte, 0, bytes.length - 32);
            String randomKey = new String(decryptInner(randomKeyByte, password), StandardCharsets.UTF_8);
            return new String(decryptInner(contentByte, randomKey), StandardCharsets.UTF_8);
        } catch (Exception exception) {
            throw new IllegalStateException("解密失败", exception);
        }
    }

    public static void main(String[] args) {
//        System.out.println(encrypt("cmz"));
//        System.out.println(decrypt("cR8rxQ8Tn8YWs9Krn8sXu14GFmW++LdFDz/AsSHPAwrOM15gSZP8XbQt/fnfpd/tnoRthYKpEw0UPySu60k7cA=="));
        System.out.println(decrypt("OsyrsF0oh46839gqgFL4PTsvTJBLqf6cHJcO4YzUtI3chd/pIW+bhnb+kOEJwBLckrMGWJQ99Q84vt0+VIsbsA==","apex-mass-mail-model"));
    }
}
package com.lo.test;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author RujiangLiu
 * @date 2023/1/16
 */
public class FileWriteTest {

    @Test
    @SneakyThrows
    public void test() {
        String fileName = "C:\\Users\\RujiangLiu\\Desktop\\新建文本文档 (2).txt";
        String fileName2 = "C:\\Users\\RujiangLiu\\Desktop\\新建文本文档 (3).txt";

        List<String> data = new ArrayList<>();
        File file = new File(fileName);
        File file2 = new File(fileName2);

        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        String line = "";
        int a = 764;
        while ((line = br.readLine()) != null) {
            a++;
            line = line.replace("(NATURE_DAY", "(ID,NATURE_DAY").replace("(2023", "(" + a + ",2023");
            line = line.concat("\r\n");
            data.add(line);
        }

        br.close();
        isr.close();
        fis.close();
        //检查输出文件夹是否存在，若不存在先创建
        if (!file2.exists()) {
            file2.createNewFile();
        }

        StringBuilder builder = new StringBuilder();
        for (String s : data) {
            builder.append(s);
        }

        //替换后输出的文件位置（切记这里的E:/ttt 在你的本地必须有这个文件夹）
        PrintWriter printWriter = new PrintWriter(file2);
        printWriter.write(String.valueOf(builder));
        printWriter.flush();
        printWriter.close();

    }
}

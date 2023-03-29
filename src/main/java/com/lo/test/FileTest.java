package com.lo.test;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.thread.ConcurrencyTester;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import info.monitorenter.cpdetector.io.*;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.util.ObjectUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author RujiangLiu
 * @date 2022/11/7
 */
public class FileTest {

    @Test
//    @SneakyThrows
    public void test() {
        String fileName = "C:\\Users\\RujiangLiu\\Desktop\\纳税申报采集\\NAV.txt";

        File file = new File(fileName);
        System.out.println(DateUtil.date(file.lastModified()));

        System.out.println(getFileCharsetName(file));
//        InputStream ins = new FileInputStream(file);
//        BufferedReader in = new BufferedReader(new InputStreamReader(ins, getFileCharsetName(file)));
//        List<List<Object>> ds =  datas.stream().filter(ol->{
//            for (Object o: ol){
//                if (ObjectUtils.isEmpty(o)){
//                    o = "";
//                }
//            }
//            return true;
//        }).collect(Collectors.toList());
//

//
//        ConcurrencyTester tester = ThreadUtil.concurrencyTest(30, () -> {
////            ExcelReader reader = ExcelUtil.getReader(fileName, 1);
////            List<List<Object>> datas = reader.read(1);
////            String result = datas.toString();
//            FileReader fileReader = new FileReader(fileName);
//            String result = fileReader.readString();
//        });
//        System.out.println("总计耗时:" + tester.getInterval());
//
    }

    @Test
    @SneakyThrows
    public String getFileCharsetName(File file) {
        CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
        detector.add(new ParsingDetector(false));
        detector.add(JChardetFacade.getInstance());
        detector.add(ASCIIDetector.getInstance());
        detector.add(UnicodeDetector.getInstance());
        Charset charset;
        charset = detector.detectCodepage(file.toURI().toURL());
        if (charset != null) {
            return charset.name();
        }
        return StandardCharsets.UTF_8.name();
    }

    @Test
    public void pattern(){
        String fileNamePattern = "capital.txt";
        Pattern pattern = Pattern.compile("^" + fileNamePattern + "$", Pattern.CASE_INSENSITIVE);

        String fileName = "capital.TXT";

        Matcher matcher = pattern.matcher(fileName);

        while (matcher.find()){
            System.out.println(matcher.group(0));
        }
    }

}

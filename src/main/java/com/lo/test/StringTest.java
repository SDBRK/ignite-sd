package com.lo.test;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author RujiangLiu
 */
public class StringTest {

//    public static final Pattern PATTERN = Pattern.compile("\\$\\{(.*)}");

    public static final Pattern GROUP_PATTERN = Pattern.compile("\\$\\{(yyyy|YYYY)?(mm|MM)?(dd|DD)?}");

//    public static final String yyyymmdd = "yyyymmdd";
//    public static final String yyyyMMdd = "yyyyMMdd";
//    public static final String YYYYMMDD = "YYYYMMDD";
//    public static final String yyyymm = "yyyymm";
//    public static final String yyyyMM = "yyyyMM";
//    public static final String yyyy = "yyyy";
//    public static final String yyyydd = "yyyydd";


    @Test
    public void test(){
//        String s = "aa.txt";
//        String fileName = s.substring(0,s.lastIndexOf("."));
//        String nameExt  = s.substring(s.lastIndexOf("."));
//
//        System.out.println(fileName);
//        System.out.println(nameExt);
//
//        String regex = "(.*)\\|";
//
//        String s = "aa-bb-cc.txt";
//        String ss = s.replaceAll("(.*)\\|","");
//        String ss = s.substring(s.lastIndexOf("-"));
//        String ss = s.replaceAll("-(.*)","");
//        String sss = s.replaceAll(ss,"");
//        s = s.substring(0,s.lastIndexOf("-"));
//        System.out.println(s);

//        JSONArray groupParams = JSONArray.parseArray("['基金','销售商']");
//
//        System.out.println(groupParams);


//        String s = "[57^21:B-A|2:B-C-A][51^1:A-B-C|17:B-C-D][8^5:A-B-C|7:B-C-E]";


//        String pattern = "\\[.*?\\]";
//
//        Pattern r = Pattern.compile(pattern);
//        Matcher m = r.matcher(s);
//        String[] arr = new String[3];

//        while (m.find()){
//            System.out.println(m.group().replace("[","").replace("]",""));
//        }
//        System.out.println(m.matches());

//        System.out.println(Arrays.toString(arr));

//        String s = "{\"isRelateGroupParams\":false,\"params\":{\"销售商代码\":\"'645'\"}}";
//        JSONObject json = JSONObject.parseObject(s);
//        boolean a = json.getBoolean("isRelateGroupParams");
//        boolean b = json.getBooleanValue("isRelateGroupParams");
//
//        System.out.println(a);
//        System.out.println(b);


        String name = "测试${YYYYMMDD}";
        String name2 = "测试${yyyyDDA}";
        String date = "";
        Matcher matcher = GROUP_PATTERN.matcher(name2);

        while (matcher.find()) {
            String fullName = matcher.group();
            System.out.println(fullName);
            String p = matcher.group(1);
            String p2 = matcher.group(2);
            String p3 = matcher.group(3);
            if (p != null) {
                date = DateUtil.format(new Date(), "yyyy");
            }
            if (p2 != null) {
                date = date.concat(DateUtil.format(new Date(), "MM"));
            }
            if (p3 != null) {
                date = date.concat(DateUtil.format(new Date(), "dd"));
            }
            name2 = name2.replace(fullName, date);
        }
        System.out.println(name2);


//            switch (p) {
//                case yyyymmdd:
//                case yyyyMMdd:
//                case YYYYMMDD:
//                    date = DateUtil.format(new Date(), "yyyyMMdd");
//                    // 包括占位符替换
//                    name = name.replace(fullName, date);
//                    break;
//                case yyyymm:
//                case yyyyMM:
//                    date = DateUtil.format(new Date(), "yyyyMM");
//                    name = name.replace(fullName, date);
//                    break;
//                default:
//            }

        // 创建一个 HashMap
//        HashMap<Integer, String> sites = new HashMap<>();
//
//        // 往 HashMap 添加一些元素
//        sites.put(1, "Google");
//        sites.put(2, null);
//        sites.put(3, "Taobao");
//        System.out.println("sites HashMap: " + sites);
//
//
//        // HashMap 不存在该key
//        sites.putIfAbsent(4, "Weibo");
//
//        // HashMap 中存在 Key
//        sites.putIfAbsent(2, "Wiki");
//        System.out.println("Updated Languages: " + sites);

//        String a = "abcd\nabcd\nabcd";
//        String b = a.replaceAll("\\n","<br>");
//        System.out.println(a);
//        System.out.println("------------------");
//        System.out.println(b);

//        String data = "20221020";
//        String src = "C:\\Users\\RujiangLiu\\Desktop\\东北证券\\测试1\\[YYYYMMDD]";
//        src = src.replaceAll("\\[YYYYMMDD\\]",data);
//        System.out.println(src);

//        String src = "{\"file\":[\"C:\\\\Users\\\\RujiangLiu\\\\Desktop\\\\东北证券\\\\测试\",\"C:\\\\Users\\\\RujiangLiu\\\\Desktop\\\\东北证券\\\\[YYYYMMDD]\"]}";
////        String src = "{\"file\":[\"s\",\"s1\",\"s2\"]}";
//        JSONObject srcPath = JSONObject.parseObject(src);
//        JSONArray srcArray = JSONArray.parseArray(srcPath.getString("file"));
//        System.out.println(srcArray);

//        String emailTitle = "测试邮件";
//        emailTitle = emailTitle.replace("${}", DateUtil.format(new Date(), DatePattern.PURE_DATE_FORMAT));
//        System.out.println(emailTitle);
//
//        String emailTitle2 = "测试邮件${}";
//        emailTitle2 = emailTitle2.replace("${}", DateUtil.format(new Date(), DatePattern.PURE_DATE_FORMAT));
//        System.out.println(emailTitle2);

//        String date = DateUtil.format(new Date(),"yyyyMMdd");
//        System.out.println(date);
//        String date2 = DateUtil.format(new Date(),"yyyyMM");
//        System.out.println(date2);
//        String date3 = DateUtil.format(new Date(),"MMdd");
//        System.out.println(date3);
//        String date4 = DateUtil.format(new Date(),"dd");
//        System.out.println(date4);
//        String date5 = DateUtil.format(new Date(),"yyyydd");
//        System.out.println(date5);
    }
}

package com.lo.test;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
    public void test() {
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


        String name2 = "测试${YYYYMMDD}";
//        String name2 = "测试${yyyyDDA}";
        String date = "";
        Matcher matcher = GROUP_PATTERN.matcher(name2);

        while (matcher.find()) {
            String fullName = matcher.group();
            System.out.println("fullName:" + fullName);
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

    @Test
    public void charToInt() {
        System.out.println((int) 'A');
        System.out.println((int) 'Z');
        System.out.println((int) 'a');
        System.out.println((int) 'z');
    }

    @Test
    public void replaceStr() {
//        String str = "yyyyMMdd今日文件";
        String str = null;

        String replace = str.replace("yyyyMMdd", DateUtil.format(new Date(), DatePattern.PURE_DATE_FORMAT));

        System.out.println(replace);
    }

    @Test
    public void joinStr() {

        List<String> str = null;

        String s = ObjectUtils.isEmpty(str) ? "aaaa" : String.join(",", str);

        System.out.println(s);
    }


    @Test
    public void toClass() {

        JSONObject jsonObject = new JSONObject();

        List<String> list = Arrays.asList("121212", "121213");

        jsonObject.put("destNumbers", list);
        jsonObject.put("attachFileRuleCodes", list);

        Config config = jsonObject.toJavaObject(Config.class);

        // Boolean值 如果为空 默认为false
        System.out.println(config.isNeedTaskDataForAttach);

        System.out.println(config);
    }

    @Data
    public static class Config {
        /**
         * 员工号
         */
        private List<String> destNumbers;
        private boolean isNeedTaskDataForAttach;
        private Set<String> attachFileRuleCodes;
    }

    @Test
    public void getMessage() {
        String data = "[{\"资产名称\":\"长江19号(乐享1天)\",\"提醒内容\":\"长江19号(乐享1天),有指令等待处理\"},{\"资产名称\":\"长江19号(乐享1天)\",\"提醒内容\":\"长江19号(乐享1天),有指令等待处理\"},{\"资产名称\":\"长江19号(乐享1天)\",\"提醒内容\":\"长江19号(乐享1天)," +
                "有指令等待处理\"},{\"资产名称\":\"长江19号(乐享1天)\",\"提醒内容\":\"长江19号(乐享1天),有指令等待处理\"}]";
        StringBuilder warnText = new StringBuilder();

        JSONArray jsonArray = JSONObject.parseArray(data);
        for (Object o : jsonArray) {
            String text = getMessage(String.valueOf(o));
            if (StringUtils.hasText(text)) {
                warnText.append(text).append(",");
            }
        }

        System.out.println(warnText);
    }

    public String getMessage(String data) {
        Map map = JSONObject.parseObject(data, Map.class);
        Object message = map.get("提醒内容");
        if (ObjectUtils.isEmpty(message)) {
            return null;
        }
        return String.valueOf(map.get("提醒内容"));
    }

    @Test
    public void exceptionData() {
        String data = "[{\"资产名称\":\"长江19号(乐享1天)\",\"提醒内容\":\"长江19号(乐享1天),有指令等待处理\",\"status\":\"1\"},{\"资产名称\":\"长江19号(乐享1天)\",\"提醒内容\":\"长江19号(乐享1天),有指令等待处理\",\"status\":\"1\"},{\"资产名称\":\"长江19号" +
                "(乐享1天)\",\"提醒内容\":\"长江19号(乐享1天)," +
                "有指令等待处理\",\"status\":\"1\"},{\"资产名称\":\"长江19号(乐享1天)\",\"提醒内容\":\"长江19号(乐享1天),有指令等待处理\",\"status\":\"1\"}]";
        List<Map> mapData = JSONObject.parseArray(data, Map.class);
        mapData = mapData.stream().filter(map -> "0".equals(map.get("status"))).collect(Collectors.toList());
        System.out.println(mapData);
        String transData = JSONObject.toJSONString(mapData);
        System.out.println(transData);
        if (StringUtils.hasText(transData)) {
            System.out.println("不是空的，长度：" + transData.length());
        }
        List<LinkedHashMap> firstList = JSONArray.parseArray(transData, LinkedHashMap.class);
        System.out.println(!CollectionUtils.isEmpty(firstList));
    }

    @Test
    public void getStamp() {
        // 16位时间戳
        System.out.println(String.valueOf(System.currentTimeMillis()));
    }
}

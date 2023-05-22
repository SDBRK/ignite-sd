package com.lo.test;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author RujiangLiu
 * @date 2023/4/11
 */
public class HttpCallOtherInterfaceUtilsTest {

    @Test
    public void getDate() {
        try {
            String dateStr = "202304";
            String url = "https://tool.bitefu.net/jiari/?d=" + dateStr + "&info=1";
            // 把字符串转换为URL请求地址
            URL thisUrl = new URL(url);
            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) thisUrl.openConnection();
            // 连接会话
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36");
            connection.setRequestProperty("content-type", "application/json; charset=utf-8");
            connection.connect();
            // 获取输入流
            InputStream is = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            String line;
//            String location = new String(br.readLine().getBytes("GBK"),"UTF-8");
            // 循环读取流
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close(); // 关闭流
            connection.disconnect(); // 断开连接
            String s = sb.toString();

            JSONObject jsonObject = JSONObject.parseObject(s).getJSONObject(dateStr);
            Map<String, Object> map = jsonObject.getInnerMap();

//            List<DateApi> dateApis = map.values().stream().map(o -> JSONObject.parseObject(JSONObject.toJSONString(o), DateApi.class)).collect(Collectors.toList());
            List<Day> dateApis = map.values().stream().map(o -> {
                DateApi dateApi = JSONObject.parseObject(JSONObject.toJSONString(o), DateApi.class);
                return new Day().toDay(dateApi);
            }).sorted(Comparator.comparingInt(Day::getNatureDay)).collect(Collectors.toList());
            System.out.println(dateApis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Data
    public static class DateApi {

//        private Integer status;
        private Integer type;
//        private String typename;
        private String day;
//        private Integer unixtime;
//        private String yearname;
//        private String nonglicn;
//        private String nongli;
//        private String shengxiao;
//        private String jieqi;
//        private String weekcn;
//        private String week1;
//        private String week2;
//        private String week3;
//        private String daynum;
//        private String weeknum;
//        private String avoid;
//        private String suit;
    }

    @Data
    public static class Day {
        private Integer natureDay;
        private boolean trading;
        private Integer calendarId;

        public Day toDay(DateApi dateApi) {
            natureDay = Integer.parseInt(dateApi.getDay());
            trading = dateApi.getType() == 0;
            calendarId = 2;
            return this;
        }
    }
}



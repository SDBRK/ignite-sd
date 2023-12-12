package com.lo.test;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.SneakyThrows;
import oracle.jdbc.pool.OracleDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author RujiangLiu
 * @date 2023/4/11
 */
public class HttpCallOtherInterfaceUtilsTest {


    public static void main(String[] args) throws Exception {
    }

    @Test
    @SneakyThrows
    @Transactional(rollbackFor = Exception.class)
    public void calendarDate(){
        // 数据库
        OracleDataSource dataSource = new OracleDataSource();
        dataSource.setURL("jdbc:oracle:thin:@127.0.0.1:1521:ORCL");
        dataSource.setUser("OMP2");
        dataSource.setPassword("OMP2");

        // 获取当年十二个月
        String year = "2024";
        List<String> dateStrs = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            if (i < 10) {
                dateStrs.add(year + "0" + i);
            } else {
                dateStrs.add(year + i);
            }
        }

        // 数据库连接语句预批处理插入
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into OMP2.T_DAY (ID, NATURE_DAY, TRADING, CALENDAR_ID) VALUES (SEQ_DAY.NEXTVAL,?, ?, ?)");
            // 查询十二个月的日期
            for (String dateStr : dateStrs) {
                List<Day> days = getDate(dateStr);
                assert days != null;
                for (Day day : days) {
                    preparedStatement.setInt(1, day.natureDay);
                    preparedStatement.setBoolean(2, day.isTrading());
                    preparedStatement.setInt(3, day.calendarId);
                    preparedStatement.addBatch();
                }
            }
            preparedStatement.executeBatch();

            System.out.println("新增日历数据完成");
        }

    }

    @Test
    public static List<Day> getDate(String dateStr) {
        try {
//            String dateStr = "202304";
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
            // 将接口返回的数据进行实体类转换
            List<Day> dateApis;
            dateApis = map.values().stream().map(o -> {
                DateApi dateApi = JSONObject.parseObject(JSONObject.toJSONString(o), DateApi.class);
                return new Day().toDay(dateApi);
            }).sorted(Comparator.comparingInt(Day::getNatureDay)).collect(Collectors.toList());
            return dateApis;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
            calendarId = 1; // 1|A股交易日 2|自然日
            return this;
        }
    }
}



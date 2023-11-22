package com.lo.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author RujiangLiu
 * @date 2022/11/9
 */
public class JsonArrayTest {

    @Test
    public void test() {

//        JSONArray jsonArray = JSON.parseArray("[{\"data\":\"a\"},{\"data\":\"b\"}]\n");
//
//        System.out.println(jsonArray.toJSONString());
//
//        System.out.println(jsonArray.size());
//        System.out.println(jsonArray.getJSONObject(0).toJSONString());

        String s = "{\"gdqxsq\":[{\"gdh\":\"A1\",\"jyqx\":\"1;2;8\"},{\"gdh\":\"A2\",\"jyqx\":\"1;8;10\"}]}";

        System.out.println(s);
        JSONArray array = JSONObject.parseObject(s).getJSONArray("gdqxsq");
        Map<String, JSONObject> jyqxMaps = new HashMap<>();

        for (Object object : array) {
            JSONObject gdqxsq = JSONObject.parseObject(JSONObject.toJSONString(object));
            String[] jyqxs = gdqxsq.getString("jyqx").split(";");
            for (String jyqx : jyqxs) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("gdh", gdqxsq.get("gdh"));
                jsonObject.put("jyqx", jyqx);
                if (jyqxMaps.containsKey(jyqx)) {
                    jyqxMaps.get(jyqx).getJSONArray("gdqxsq").add(jsonObject);
                } else {
                    JSONObject gdqxsqObject = new JSONObject();
                    JSONArray jsonArray = new JSONArray();
                    jsonArray.add(jsonObject);
                    gdqxsqObject.put("gdqxsq", jsonArray);
                    jyqxMaps.put(jyqx, gdqxsqObject);
                }
            }
        }

        jyqxMaps.values().forEach(System.out::println);

        System.out.println(jyqxMaps.values());
    }


    @Test
    public void  chaor(){
        // 原始的JSON数据
        String originalJson = "{\"gdqxsq\":[{\"gdh\":\"A1\",\"jyqx\":\"1;2;8\"},{\"gdh\":\"A2\",\"jyqx\":\"1;8;10\"}]}";
        JSONObject originalObj = JSONObject.parseObject(originalJson);
        JSONArray gdqxsqArray = originalObj.getJSONArray("gdqxsq");

        //类聚
        Map<String,List<String>> jyqxMap = new HashMap<>();
        for (int i = 0; i < gdqxsqArray.size(); i++) {
            JSONObject item = gdqxsqArray.getJSONObject(i);
            String[] jyqxValues = item.getString("jyqx").split(";");
            String gdh = item.getString("gdh");
            for (String jyqxValue : jyqxValues) {
                List<String> gdhMap = jyqxMap.get(jyqxValue) == null ? new ArrayList<>() : jyqxMap.get(jyqxValue);
                gdhMap.add(gdh);
                jyqxMap.put(jyqxValue,gdhMap);
            }
        }
        //重组
        ArrayList<JSONObject> newObj = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : jyqxMap.entrySet()) {
            ArrayList<JSONObject> jsonObjects = new ArrayList<>();
            for (String gdh : entry.getValue()) {
                JSONObject newJson = new JSONObject();
                newJson.put("gdh",gdh);
                newJson.put("jyqx",entry.getKey());
                jsonObjects.add(newJson);
            }
            JSONObject value = new JSONObject();
            value.put("gdqxsq",jsonObjects);
            newObj.add(value);
        }
        System.out.println(newObj);
    }


    @Test
    public void test1() {
        String s = "";
        System.out.println(s.length());
        System.out.println(s == null);
        System.out.println(s.isEmpty());
    }

    @Data
    public static class Node {
        private JSONObject data;
        private String id;
        private JSONObject position;
        private String type;
    }
}

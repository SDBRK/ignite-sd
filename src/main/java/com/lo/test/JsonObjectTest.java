package com.lo.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import org.junit.jupiter.api.Test;

/**
 * @author RujiangLiu
 * @date 2023/2/27
 */
public class JsonObjectTest {

    public static void main(String[] args) {

//        JSONObject jsonObject = JSONObject.parseObject("{\"id\":\"78425f5a-09c0-41b4-b181-3d8ad265091c\",\"销售商代码\":\"000\",\"销售商名称\":\"天哪银行\",\"不认购归销售商比例\":\"1\",\"申购归销售商比例\":\"1\"," +
//                "\"不赎回归销售商比例\":\"0" +
//                ".5\"}");
//        JSONObject jsonObject2 = JSONObject.parseObject("{\"销售商代码\":\"000\",\"销售商名称\":\"天哪银行\",\"不认购归销售商比例\":\"1\",\"申购归销售商比例\":\"1\",\"不赎回归销售商比例\":\"0" +
//                ".5\"}");
//        jsonObject2.put("id","65ab07f5-d23e-4c96-9182-19612a2ce9f0");
//
//        System.out.println(jsonObject);
//        System.out.println(jsonObject2);
//        System.out.println("------------------------------------");
//        JSONObject jsonObject3 = JSONObject.parseObject("{\"id\":\"78425f5a-09c0-41b4-b181-3d8ad265091c\",\"销售商代码\":\"000\",\"销售商名称\":\"天哪银行\",\"认购归销售商比例\":\"1\",\"申购归销售商比例\":\"1\"," +
//                "\"赎回归销售商比例\":\"0" +
//                ".5\"}");
//        JSONObject jsonObject4 = JSONObject.parseObject("{\"销售商代码\":\"000\",\"销售商名称\":\"天哪银行\",\"认购归销售商比例\":\"1\",\"申购归销售商比例\":\"1\",\"赎回归销售商比例\":\"0" +
//                ".5\"}");
//        jsonObject4.put("id","65ab07f5-d23e-4c96-9182-19612a2ce9f0");
//
//        System.out.println(jsonObject3);
//        System.out.println(jsonObject4);

        JSONObject jsonObject = JSONObject.parseObject("{\"id\":\"78425f5a-09c0-41b4-b181-3d8ad265091c\",\"认购归销售商比例\":\"000\",\"比例\":\"10\",\"label\":\"姚贝娜\",\"aaa\":\"bbb\"}");
        jsonObject.remove("id");
        jsonObject.put("id", "78425f5a-09c0-41b4-b181-3d8ad265091c");

        JSONObject jsonObject2 = JSONObject.parseObject("{\"认购归销售商比例\":\"000\",\"id\":\"78425f5a-09c0-41b4-b181-3d8ad265091c\",\"比例\":\"10\",\"label\":\"姚贝娜\",\"aaa\":\"bbb\"}");

        System.out.println(jsonObject);
        System.out.println(jsonObject2);
    }

    @Test
    public void stringToJsonObject(){

        String s = "{\"userName\":\"管理员\",\"user\":{\"userNum\":\"admin\",\"id\":0,\"roleList\":[],\"userName\":\"管理员\"},\"userId\":\"0\"}";

        System.out.println(s);

        JSONObject jsonObject = JSON.parseObject(s);

        System.out.println(jsonObject.getString("userName"));
    }
}

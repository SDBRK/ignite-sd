package com.lo.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.xml.internal.bind.v2.model.core.ID;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.List;

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

        String s = "{\n" +
                "    \"calendarId\": 1,\n" +
                "    \"code\": \"code\",\n" +
                "    \"name\": \"流程1\",\n" +
                "    \"priority\": 2,\n" +
                "    \"remark\": \"流程流程\",\n" +
                "    \"processData\": {\n" +
                "        \"nodes\": [\n" +
                "            {\n" +
                "                \"id\": \"node_input\",\n" +
                "                \"data\": {},\n" +
                "                \"position\": {\n" +
                "                    \"x\": 135,\n" +
                "                    \"y\": -75\n" +
                "                },\n" +
                "                \"type\": \"input\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"node_1_1668134641941\",\n" +
                "                \"data\": {\n" +
                "                    \"name\": \"节点1\",\n" +
                "                    \"limitHours\": 5,\n" +
                "                    \"sendTo\": \"AAA\",\n" +
                "                    \"remark\": \"节点节点节点\"\n" +
                "                },\n" +
                "                \"position\": {\n" +
                "                    \"x\": 120,\n" +
                "                    \"y\": 0\n" +
                "                },\n" +
                "                \"type\": \"default\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"node_output\",\n" +
                "                \"data\": {},\n" +
                "                \"position\": {\n" +
                "                    \"x\": 135,\n" +
                "                    \"y\": 75\n" +
                "                },\n" +
                "                \"type\": \"output\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"edges\": [\n" +
                "            {\n" +
                "                \"id\": \"reactflow__edge-node_input-node_1_1668134641941\",\n" +
                "                \"source\": \"node_input\",\n" +
                "                \"target\": \"node_1_1668134641941\",\n" +
                "                \"type\": \"step\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"reactflow__edge-node_1_1668134641941-node_output\",\n" +
                "                \"source\": \"node_1_1668134641941\",\n" +
                "                \"target\": \"node_output\",\n" +
                "                \"type\": \"step\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}";


        JSONObject a = JSONObject.parseObject(s);
        JSONObject processData = a.getJSONObject("processData");
//        System.out.println(processData.toJSONString());
//        List<Node> nodeList = processData.getJSONArray("nodes").toJavaList(Node.class);
//        for (Node o : nodeList) {
////            System.out.println(JSONObject.parseObject(JSONObject.toJSONString(o)).getString("type"));
//            System.out.println(o.getType());
//            System.out.println(o.getData());
//        }
        JSONArray nodeList = processData.getJSONArray("nodes");
        for (Object o :nodeList){
//            System.out.println(o);
//            System.out.println(o.toString());
            JSONObject nodeJson = JSONObject.parseObject(o.toString());
            System.out.println(nodeJson.getString("type"));
            System.out.println(JSONObject.parseObject(o.toString()).getString("type"));
//            System.out.println(JSONObject.toJSONString(o));
//            System.out.println(JSONObject.parseObject(JSONObject.toJSONString(o)));
//            System.out.println(JSONObject.parseObject(JSONObject.toJSONString(o)).getString("type"));
        }

    }

    @Data
    public static class Node {
        private JSONObject data;
        private String id;
        private JSONObject position;
        private String type;
    }
}

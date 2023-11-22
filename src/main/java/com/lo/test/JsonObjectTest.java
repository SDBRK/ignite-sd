package com.lo.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

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

    @Test
    public void toEntity(){

        JSONObject requestJson = new JSONObject();
        requestJson.put("esbUsername", "esbProperty.getUsername()");
        requestJson.put("esbPassword", "esbProperty.getPassword()");
        requestJson.put("srvUsername", "srvProperty.getUsername()");
        requestJson.put("srvPassword", "srvProperty.getPassword()");
        requestJson.put("destNumbers", "srvProperty.getDestNumbers()");
        requestJson.put("msgType", "srvProperty.getMsgType()");
        requestJson.put("destType", "srvProperty.getDestType()");
        requestJson.put("content", "messageInfo.getContent()");


        String request = requestJson.toJSONString();

        SendWeChatMessageRequestDto dto = JSON.parseObject(request, SendWeChatMessageRequestDto.class);

        System.out.println(dto);
    }

    @Data
    public static class SendWeChatMessageRequestDto {

        private SendWeChatFileRequestDto fileRequestDto;

        private String content;

    }

    @Data
    public static class SendWeChatFileRequestDto {
        /**
         * esb用户名
         */
        private String esbUsername;
        /**
         * esb密码
         */
        private String esbPassword;
        /**
         * 推送系统认证用户名(测试环境直接填AFA，生产环境需要走申请流程)
         */
        private String srvUsername;
        /**
         * 推送系统认证密码(测试环境直接填123123，生产环境需要走申请流程)
         */
        private String srvPassword;
        /**
         * 员工号（用,隔开,最多支持1000个）
         */
        private String destNumbers;
        /**
         * 业务类型编码,测试环境为10_1_1
         */
        private String msgType;
        /**
         * 账号类型(EMPNO)
         */
        private String destType;
        /**
         * 备注,可以自定义
         */
        private String remark;
        /**
         * 信息编辑者，请自定，后续用于标识调用接口的系统
         */
        private String author;
    }
}

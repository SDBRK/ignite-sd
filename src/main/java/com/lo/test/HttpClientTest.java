package com.lo.test;

import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.junit.jupiter.api.Test;

/**
 * @author RujiangLiu
 * @date 2024/2/27
 */
public class HttpClientTest {

    public static void main(String[] args) {

    }

    @SneakyThrows
    @Test
    public void timeExecution(){
        HttpClient client = new HttpClient();
        GetMethod getMethod = new GetMethod("http://localhost:8777/aggregativeTaskNode/timedExecution?nodeId=123456");
        getMethod.addRequestHeader("content-type", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36");
        getMethod.addRequestHeader("User-Agent", "application/json; charset=utf-8");

        // 中文乱码
        getMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");

        int status = client.executeMethod(getMethod);
        System.out.println(status);
         // 200
        if (status == HttpStatus.SC_OK){
            System.out.println("okok");
        }

        JSONObject jsonObject = JSONObject.parseObject(getMethod.getResponseBodyAsString());
        System.out.println(jsonObject);
        String code = jsonObject.getString("code");
        System.out.println(code);
    }

}

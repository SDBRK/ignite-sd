package com.lo.test;

import com.alibaba.fastjson.JSONObject;

/**
 * @author RujiangLiu
 */
public class FinallyEx {

    public static void main(String[] args) {
        System.out.println(findResult());

        String sj = "{\"baseInfo\":{\"fullName\":\"中\",\"shortName\":\"中\",\"code\":0,\"investment\":{\"investmentTyp\":\"111\",\"isMergerReorg\":\"222\",\"investmentTyP\":\"555\"},\"issupportAttain\":\"333\",\"isUnderlyingAss\":\"444\"}}\n";

        JSONObject jo  = JSONObject.parseObject(sj);

        JSONObject baseInfo= new JSONObject();

        JSONObject investment=new JSONObject();

        investment.put("ss","11");

        baseInfo.put("investment",investment);


    }

    @SuppressWarnings("finally")
    private static Integer findResult() {
        Integer integer = 5;
        try {
            return integer = 6;
        } catch (Exception e) {
            return integer = 7;
        } finally {
            return integer = 8;
        }
    }
}

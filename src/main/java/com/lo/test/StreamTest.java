package com.lo.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author RujiangLiu
 */
@Slf4j
public class StreamTest {

    @Test
    public void filterTest() {
        //filter : 对列表中的数据进行判断，过滤
//        List<String> strings = Arrays.asList("Hollis", "", "HollisChuang", "H", "hollis");
//        strings.stream().filter(string -> !"hollis".equals(string)).forEach(System.out::println);

        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        numbers.sort(Comparator.comparing(Integer::intValue));
//        //map : 对列表中的数据进行操作，处理
//        numbers.stream().map(i -> !i.equals(2)).forEach(System.out::println);
//        //limit : 对列表中的数据取前4个，丢弃剩余的
//        numbers.stream().limit(4).forEach(System.out::println);
//        //skip : 对列表中的数据丢弃前4个，取剩余的
//        numbers.stream().skip(4).forEach(System.out::println);
//        //sorted : 对列表中的数据进行排序(默认升序)
//        numbers.stream().sorted().forEach(System.out::println);
//        //reverseOrder : 对列表中的数据进行排序(降序)
//        numbers.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
//        //reversed : 对列表中的数据进行自定义字段排序
//        numbers.stream().sorted(Comparator.comparing(Integer::intValue).reversed().thenComparing(Integer::intValue)).forEach(System.out::println);
//        //count : 对列表中的数据进行统计个数
//        System.out.println(numbers.stream().count()+ "," + (long) numbers.size());

        String sourceStr = "[\n" +
                "    {\"id\":\"1001\",\"name\":\"测试1\",\"value\":\"111\",\"age\":null},\n" +
                "    {\"id\":\"1002\",\"name\":\"测试2\",\"value\":\"222\",\"age\":\"\"},\n" +
                "    {\"id\":\"1002\",\"name\":\"测试2\",\"value\":\"22222\",\"age\":\"16\"},\n" +
                "    {\"id\":\"1002\",\"name\":\"测试4\",\"value\":\"2222222\",\"age\":\"14\"},\n" +
                "    {\"id\":\"1003\",\"name\":\"测试3\",\"value\":\"333\",\"age\":\"13\"},\n" +
                "    {\"id\":\"1003\",\"name\":\"测试3\",\"value\":\"333333\",\"age\":\"13\"}\n" +
                "]";

        JSONArray data = JSONArray.parseArray(sourceStr);
//        Map<String, List<?>> dataMap = new HashMap<String, List<?>>();
//        List<List<Object>> lst = new ArrayList<>();
//        //数据分组算法
//        for (int i = 0; i < data.size(); i++) {
//            JSONObject json = (JSONObject)data.get(i);
//            List<Object> tempList = (List<Object>) json.get("id");
//            if(tempList == null) { //第一次则创建list
//                tempList = new ArrayList<>();
//                tempList.add(json);
////                dataMap.put(json.get("id").toString(), tempList);//放入map中
//                lst.add(tempList);
//            }else {
//                tempList.add(json);
//            }
//        }
////        //遍历
////        for (String id: dataMap.keySet()) {
////            System.out.println(id +":"+dataMap.get(id));
////        }
//        System.out.println(lst);
//        Map<Object,List<Object>> groupById = data.stream().collect(Collectors.groupingBy(x-> JSON.parseObject(x.toString()).get("id")));

        List<String> ss = Arrays.asList("id", "name");
        Map<Object, List<Object>> groupById = data.stream().collect(Collectors.groupingBy(x -> {
            StringJoiner joiner = new StringJoiner("_");
            for (String s : ss) {
                JSONObject jsonObject = JSON.parseObject(x.toString());
                if (!jsonObject.containsKey(s)) {
                    log.error("表格中不存在该字段:{}", s);
                    throw new RuntimeException("表格中不存在该字段");
                }
                Object o = jsonObject.get(s);
                joiner.add((CharSequence) o);
            }
//            joiner.add((CharSequence) JSON.parseObject(x.toString()).get("age"));
//            try {
//                Object  = JSON.parseObject(x.toString()).get(s);
//            }
            return joiner.toString();
        }));
//        Map<Object, List<Object>> groupById = data.stream().collect(Collectors.groupingBy(x -> JSON.parseObject(x.toString()).get("sex")));
        System.out.println(groupById);

    }

    @Test
    public void mapTest() {

        List<UserInfo> userInfos = new ArrayList<>();
        UserInfo userInfo = new UserInfo(1L, "江大神", true);
        UserInfo userInfo1 = new UserInfo(2L, "江哥", true);
        UserInfo userInfo2 = new UserInfo(3L, "江姐", true);
        UserInfo userInfo3 = new UserInfo(4L, "江老师", true);
        UserInfo userInfo4 = new UserInfo(5L, "江老师", false);

        userInfos.add(userInfo);
        userInfos.add(userInfo1);
        userInfos.add(userInfo2);
        userInfos.add(userInfo3);
        userInfos.add(userInfo4);

        Map<String, Long> usernames = userInfos.stream().filter(UserInfo::getStatus).collect(Collectors.toMap(UserInfo::getNickname, UserInfo::getId));

        System.out.println(usernames);

    }


    @Data
    public static class UserInfo {
        private Long id;
        private String nickname;
        private Boolean status;

        public UserInfo(Long id, String nickname, Boolean status) {
            this.id = id;
            this.nickname = nickname;
            this.status = status;
        }
    }

    @Test
    public void test() {
        Map<String, String> test1 = new ConcurrentHashMap<>();
        test1.put("测试", "测试");
        Map<String, String> test2 = test1.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        test1.clear();
        System.out.println(test2);
    }

}

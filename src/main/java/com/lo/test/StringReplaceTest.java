package com.lo.test;

import cn.hutool.core.io.FileUtil;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author RujiangLiu
 * @date 2023/6/15
 */
public class StringReplaceTest {

    @Test
    public void stringReplace() {

        int minId = 84820;
        int maxId = 167155;
        int baseId = minId;

        String baseSql1 = "UPDATE yypt.t_task t SET t.TYPE = (SELECT t2.TASK_TYPE FROM yypt.t_rule t2 WHERE t2.id=t.RULE_ID) WHERE ";
        String baseSql2 = "UPDATE yypt.t_task t SET t.RULE_CODE = (SELECT `CODE` from yypt.t_rule t2 where t.RULE_ID = t2.ID) WHERE ";
        String baseSql3 = "UPDATE yypt.t_task SET `STATUS` = 'ERROR' WHERE `STATUS` = '-1' AND ";
        String baseSql4 = "UPDATE yypt.t_task SET `STATUS` = 'SUCCESS' WHERE `STATUS` = '2' AND ";
        String baseSql5 = "UPDATE yypt.t_task SET `CODE` = (CONCAT(DATE_FORMAT(GENERATE_TIME,'%Y%m%d') ,'-',RULE_CODE)) WHERE ";
        String baseSql6 = "UPDATE yypt.t_task t SET t.PARENT_CODE = (CONCAT(DATE_FORMAT(GENERATE_TIME,'%Y%m%d') ,'-',(SELECT `CODE` from yypt.t_rule t2 where t.PARENT_ID = t2.ID))) WHERE ";

        List<String> baseSqls = new ArrayList<>();
        baseSqls.add(baseSql1);
        baseSqls.add(baseSql2);
        baseSqls.add(baseSql3);
        baseSqls.add(baseSql4);
        baseSqls.add(baseSql5);
        baseSqls.add(baseSql6);

        StringBuilder totalSql = new StringBuilder();

        int num = (maxId - minId) / 2000 + 1;

        for (String baseSql : baseSqls) {
            for (int i = 1; i <= num; i++) {
                totalSql.append(baseSql);
                totalSql.append("ID>=").append(baseId).append(" AND ID<").append(baseId + 2000).append(";").append(" \n");
                baseId += 2000;
            }
            baseId = minId;
            totalSql.append("\n");
        }
        String path = System.getProperty("user.dir");
//        System.out.println(Objects.requireNonNull(this.getClass().getClassLoader().getResource("")).getPath());
//        System.out.println(path);
        File file = FileUtil.touch(path + File.separator + "任务数据批量执行.sql");
        FileUtil.appendUtf8String(String.valueOf(totalSql), file);
//        System.out.println(totalSql);
    }

    @SneakyThrows
    public void exportTxt(HttpServletResponse response, String text) {
        response.setCharacterEncoding("utf-8");
        //设置响应的内容类型
        response.setContentType("text/plain");
        //设置文件的名称和格式 //设置名称格式，没有这个中文名称无法显示
        response.addHeader("Content-Disposition", "attachment;filename="
                + new String("任务数据批量执行".getBytes("gb2312"), "ISO8859-1")
                + ".txt");
        ServletOutputStream outStr = response.getOutputStream();
        BufferedOutputStream buff = new BufferedOutputStream(outStr);
        try {
            buff.write(text.getBytes(StandardCharsets.UTF_8));
            buff.flush();
            buff.close();
        } catch (Exception ignored) {
        } finally {
            try {
                buff.close();
                outStr.close();
            } catch (Exception ignored) {
            }
        }
    }

    @Test
    public void countNum() {
        int minId = 92426;
        int maxId = 160630;
        int num = (maxId - minId) / 2000 + 1;
        System.out.println(num);
        System.out.println(maxId - minId);
    }

}

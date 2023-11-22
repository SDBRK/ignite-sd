package com.lo.test;

import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import lombok.Data;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.util.ObjectUtils;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author RujiangLiu
 * @date 2023/5/22
 */
public class ExcelFileTest {

    @Test
    @SneakyThrows
    public void importExcel() {

        String fileName = "C:\\Users\\RujiangLiu\\Desktop\\纳税申报采集\\运营管控任务清单调整.xlsx";
        String opName = "C:\\Users\\RujiangLiu\\Desktop\\纳税申报采集\\运营管控任务清单调整-1.xlsx";

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        ExcelReader excelReader = ExcelUtil.getReader(new FileInputStream(fileName));
        excelReader.setHeaderAlias(AdjustV0.getAlias());
        List<AdjustV0> adjustV0s = excelReader.readAll(AdjustV0.class);
        excelReader.close();
        adjustV0s = adjustV0s.stream().peek(
                adjustV0 -> {
                    adjustV0.setStartTime(format.format(adjustV0.getStartTime()));
                    if (!ObjectUtils.isEmpty(adjustV0.getEndTime())) {
                        adjustV0.setEndTime(format.format(adjustV0.getEndTime()));
                    }
                }
        ).collect(Collectors.toList());

        System.out.println("结束");

        ExcelWriter excelWriter = ExcelUtil.getWriter(opName);
        excelWriter.write(adjustV0s);
        excelWriter.close();
    }

    @Data
    static class AdjustV0 {
        private String ruleCode;
        private Object startTime;
        private Object endTime;
        private Integer taskInterval;

        public static Map<String, String> getAlias() {
            Map<String, String> map = new HashMap<>();
            map.put("任务编码", "ruleCode ");
            map.put("开始时间", "startTime");
            map.put("结束时间（可不填，默认到第二天05:00:00）", "endTime");
            map.put("频率（正整数，单位s）", "taskInterval");
            return map;
        }
    }
}

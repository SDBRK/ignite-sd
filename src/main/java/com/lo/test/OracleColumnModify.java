package com.lo.test;

import java.util.Arrays;
import java.util.List;

/**
 * @author RujiangLiu
 * @date 2023/11/22
 */
public class OracleColumnModify {

    public static void main(String[] args) {

        // 表名
        String tableName = "TACCOREQUEST";
        // 日期字段调整 -迁移导致DATE字段变为VARCHAR2
        List<String> columns = Arrays.asList("D_DATE","D_CDATE", "D_REQUESTDATE");

        // 最终执行脚本
        String executeSql = "";

        // 新增DATE备份字段
        StringBuilder addBakSql = new StringBuilder();
        // 更新备份字段为日期格式的旧字段
        StringBuilder updateBakSql = new StringBuilder();
        // 重置原字段为NULL
        StringBuilder updateNullSql = new StringBuilder();
        // 更改原字段类型为DATE
        StringBuilder modifySql = new StringBuilder();
        // 回填原字段数据从备份字段
        StringBuilder updateSql = new StringBuilder();
        // 删除备份字段
        StringBuilder deleteBakSql = new StringBuilder();

        for (String column : columns) {
            addBakSql.append("ALTER TABLE SUBTA.").append(tableName).append(" ADD ").append(column).append("_BAK DATE NULL;\n");
            updateBakSql.append("UPDATE SUBTA.").append(tableName).append(" SET ").append(column).append("_BAK = TO_DATE(").append(column).append(", 'yyyy-MM-dd HH24:MI:SS');\n");
            updateNullSql.append("UPDATE SUBTA.").append(tableName).append(" SET ").append(column).append(" = NULL;\n");
            modifySql.append("ALTER TABLE SUBTA.").append(tableName).append(" MODIFY ").append(column).append(" DATE;\n");
            updateSql.append("UPDATE SUBTA.").append(tableName).append(" SET ").append(column).append(" = ").append(column).append("_BAK;\n");
            deleteBakSql.append("ALTER TABLE SUBTA.").append(tableName).append(" DROP COLUMN ").append(column).append("_BAK;\n");
        }

        executeSql = addBakSql + "\n" +
                updateBakSql + "\n" +
                updateNullSql + "\n" +
                modifySql + "\n" +
                updateSql + "\n" +
                deleteBakSql + "\n";

        System.out.println(executeSql);

    }
}

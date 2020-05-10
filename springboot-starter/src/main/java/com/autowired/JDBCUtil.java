package com.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class JDBCUtil {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /* 查询一个字段 */
    public List<String> selectOneColumn(String sql) {
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);
        List<String> columnList = new ArrayList<>();
        for (int i = 0; i < mapList.size(); i++) {
            for (Map.Entry<String, Object> entry : mapList.get(i).entrySet()) {
                columnList.add((String) entry.getValue());
            }
        }
        return columnList;
    }

    /* 查询多个字段 */
    public List<Map<String, Object>> selectMultiColumn(String sql) {
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);
        List<Map<String, Object>> columnList = new ArrayList<>();
        for (int i = 0; i < mapList.size(); i++) {
            Map<String, Object> record = new LinkedHashMap<>();
            for (Map.Entry<String, Object> entry : mapList.get(i).entrySet()) {
                record.put(entry.getKey(), entry.getValue());
            }
            columnList.add(record);
        }
        return columnList;
    }

}

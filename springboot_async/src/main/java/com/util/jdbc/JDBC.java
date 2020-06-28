package com.util.jdbc;

import com.annotation.Consume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

@Component
public class JDBC {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Async
    @Consume(unit = "ms")
    public Future<String> list(String sql) {
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);
        System.out.println(mapList.size());
        return new AsyncResult<>("");
    }

}

package com;

import com.autowired.JDBCUtil;
import com.util.MBPGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class StarterTest {

    @Autowired
    private JDBCUtil JDBCUtil;

    @Test
    public void template() {
        JDBCUtil.selectOneColumn("SHOW TABLES FROM untitled");
    }

    //    @Test
    public void generate() {
        String[] tableArray = {""};
        MBPGenerator.generate(
                "jdbc:mysql://127.0.0.1:3306/untitled?useSSL=false&characterEncoding=utf-8",
                "root",
                "2468",
                "C:/Users/Administrator/IdeaProjects(2)/untitled", /* Idea Project */
                "springboot_editor", /* 项目 */
                "test", /* 包名 */
                "com.module", /* 父包名 */
                tableArray); /* 表名 */
    }

}

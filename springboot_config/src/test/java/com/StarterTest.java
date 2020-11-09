package com;

import com.util.JDBCUtil;
import com.util.MBPGenerator;
import com.util.StarterUtil;
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

    @Autowired
    private StarterUtil starterUtil;

    //    @Test
    public void test() {
        starterUtil.test();
    }

    /* 接口放在springboot-starter，实现放在springboot_config+@Component */
    @Test
    public void test2() {
        starterUtil.test2();
    }

    //    @Test
    public void test3() {
        JDBCUtil.selectOneColumn("SHOW TABLES FROM untitled");
    }

    //    @Test
    public void test4() {
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

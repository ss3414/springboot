package com;

import com.util.JDBCUtil;
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

}

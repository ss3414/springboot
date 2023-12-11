package com;

import com.controller.IndexController;
import com.dao.UserDao;
import com.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

/*
 * Test启动Spring容器
 * ①Spring
 * TestNG：@ContextConfiguration+继承AbstractTransactionalTestNGSpringContextTests
 * JUnit4：@ContextConfiguration+@RunWith
 * ②SpringBoot
 * ①JUnit4：@SpringBootTest+@RunWith
 * ②JUnit5：@SpringBootTest+@ExtendWith
 * */
@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ApplicationTest {

    @Autowired
    private UserDao userDao;

    //    @Test
    public void test() {
        Optional<User> result = userDao.findById(1L);
        User user = new User();
        if (result.isPresent()) {
            user = result.get();
        }
        log.info(user.toString());
    }

    /************************************************************分割线************************************************************/
    /* 测试Controller */

    private MockMvc mvc;

    //    @Before
    public void before() {
        mvc = MockMvcBuilders.standaloneSetup(new IndexController()).build();
    }

    //    @Test
    public void test2() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()) /* 正常响应 */
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    /************************************************************分割线************************************************************/
    /* 测试Jasypt */

    @Value("${spring.datasource.password}")
    private String password;

    @Test
    public void test3() {
        log.info("password:{}", password);
    }

}

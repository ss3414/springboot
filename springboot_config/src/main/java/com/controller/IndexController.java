package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.annotation.Consume;
import com.model.User;
import com.model.User2;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("")
public class IndexController {

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("/index");
    }

    /************************************************************分割线************************************************************/

    /*
     * ①@Autowired通过注入的方式获取User类的对象
     * ②Spring自动装配Bean
     * ③允许null值
     * */
    @Autowired(required = false)
    private User user;

    @GetMapping("/user")
    public Map user() {
        Map map = new HashMap();
        map.put("id", user.getId());
        map.put("name", user.getName());
        map.put("pwd", user.getPassword());
        return map;
    }

    @Autowired(required = false)
    private User2 user2;

    @GetMapping("/user2")
    public Map user2() {
        Map map = new HashMap();
        map.put("id", user2.getId());
        map.put("name", user2.getName());
        map.put("pwd", user2.getPassword());
        return map;
    }

    /************************************************************分割线************************************************************/

    @Autowired
    private Environment environment;

    /* 获取服务端IP地址+端口号 */
    @GetMapping("/server")
    public Map server(HttpServletRequest request) throws UnknownHostException {
        Map map = new HashMap();
        map.put("address1", request.getLocalAddr());
        map.put("address2", Inet4Address.getLocalHost().getHostAddress());
        map.put("port", environment.getProperty("local.server.port"));
        return map;
    }

    /* 获取服务端时间 */
    @GetMapping("/time")
    public Map time() {
        LocalDateTime localDateTime = LocalDateTime.now();
        String time = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Map map = new HashMap();
        map.put("time", time);
        return map;
    }

    /* 获取外网IP */
    @GetMapping("/ip")
    public Map ip() throws IOException {
        JSONObject jsonObject = null;
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet("http://httpbin.org/get");
        HttpResponse response = client.execute(get);
        jsonObject = JSONObject.parseObject(EntityUtils.toString(response.getEntity()));

        Map map = new HashMap();
        map.put("ip", jsonObject.getString("origin"));
        return map;
    }

    /*
     * SpringBoot部署fat jar问题
     * ①开发环境：file:/C:/Users/Administrator/IdeaProjects(2)/springboot/springboot_config/target/classes/
     * ②生产环境：jar:file:/app.jar!/BOOT-INF/classes!/
     * ③不要用文件输入流读取配置，直接用ClassLoader().getResourceAsStream读取配置
     * */
    @GetMapping("/resource")
    public Map<String, Object> resource() {
        Map<String, Object> map = new HashMap<>();
        map.put("resource", Thread.currentThread().getContextClassLoader().getResource(""));
        return map;
    }

    /************************************************************分割线************************************************************/

    @Consume
    @GetMapping("/consume")
    public Map consume() {
        Map map = new HashMap();
        return map;
    }

}

package com.demo;

import com.alibaba.fastjson.JSONObject;
import com.demo.annotation.Consume;
import com.demo.util.StarterUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.net.Inet4Address;
import java.net.URI;
import java.net.UnknownHostException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

@RestController
@SpringBootApplication
public class Config {

    public static void main(String[] args) {
        SpringApplication.run(Config.class, args);
    }

    @RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView("/index");
    }

    /*
     * ①@Autowired通过注入的方式获取User类的对象
     * ②Spring自动装配Bean
     * ③允许null值
     * */
    @Autowired(required = false)
    private User user;

    @RequestMapping("/user")
    public Map user() {
        Map map = new LinkedHashMap();
        map.put("id", user.getId());
        map.put("name", user.getName());
        map.put("pwd", user.getPassword());
        return map;
    }

    @Autowired
    private StarterUtil starterUtil;

    @Consume
    @RequestMapping("/starter")
    public Map starter() {
        starterUtil.test();
        return new LinkedHashMap();
    }

    /************************************************************分割线************************************************************/

    @Autowired
    private Environment environment;

    /* 获取服务端IP地址+端口号 */
    @RequestMapping("/server")
    public Map server(HttpServletRequest request) throws UnknownHostException {
        Map map = new LinkedHashMap();
        map.put("address1", request.getLocalAddr());
        map.put("address2", Inet4Address.getLocalHost().getHostAddress());
        map.put("port", environment.getProperty("local.server.port"));
        return map;
    }

    /* 获取服务端时间 */
    @RequestMapping("/time")
    public Map time() {
        LocalDateTime localDateTime = LocalDateTime.now();
        String time = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Map map = new LinkedHashMap();
        map.put("time", time);
        return map;
    }

    /* 获取外网IP */
    @SneakyThrows
    @RequestMapping("/ip")
    public Map ip() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://httpbin.org/get")).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        var jsonObject = JSONObject.parseObject(response.body());

        Map map = new LinkedHashMap();
        map.put("ip", jsonObject.getString("origin"));
        return map;
    }

    /*
     * SpringBoot部署fat jar问题
     * ①开发环境：file:/C:/Users/Administrator/IdeaProjects(2)/springboot/springboot3_config/target/classes/
     * ②生产环境：jar:file:/app.jar!/BOOT-INF/classes!/
     * ③不要用文件输入流读取配置，直接用ClassLoader().getResourceAsStream读取配置
     * */
    @SneakyThrows
    @RequestMapping("/resource")
    public Map<String, Object> resource() {
        Map<String, Object> map = new LinkedHashMap<>();
        Properties properties = new Properties();
        properties.load(Config.class.getResourceAsStream("/application.properties"));
        return map;
    }

}

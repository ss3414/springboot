package actuator.controller;

import actuator.mapper.UserMapper;
import actuator.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("")
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/")
    public ModelAndView index() {
        log.info("index");
        return new ModelAndView("/index");
    }

    @RequestMapping("/user")
    public Map<String, Object> user() {
        User user = userMapper.selectOne(1);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("user", user);
        return map;
    }

}

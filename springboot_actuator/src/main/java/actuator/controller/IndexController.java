package actuator.controller;

import actuator.mapper.UserMapper;
import actuator.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("")
public class IndexController {

    private final static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("/index");
    }

    @GetMapping("/user")
    public Map<String, Object> user() {
        User user = userMapper.selectOne(1);
        logger.info("/user");

        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        return map;
    }

}

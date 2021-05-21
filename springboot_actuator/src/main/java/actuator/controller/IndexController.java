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

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("")
public class IndexController {

    /* SLF4J+LogBack */
    private final static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public ModelAndView index() {
        logger.info("index");
        return new ModelAndView("/index");
    }

    @GetMapping("/user")
    public Map<String, Object> user() {
        User user = userMapper.selectOne(1);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("user", user);
        return map;
    }

}

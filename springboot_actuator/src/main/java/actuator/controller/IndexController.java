package actuator.controller;

import actuator.mapper.UserMapper;
import actuator.model.User;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
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
        User user = userMapper.selectByPrimaryKey(1);
        logger.info("/user");

        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        return map;
    }

    @GetMapping("/userPage")
    public Map<String, Object> userPage(@RequestParam(defaultValue = "1") Integer currentPage) {
        /* PageHelper分页插件 */
        PageHelper.startPage(currentPage, 2);
        List<User> userList = userMapper.selectAll();

        Map<String, Object> map = new HashMap<>();
        map.put("result", userList.size());
        return map;
    }

}

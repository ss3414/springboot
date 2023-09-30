package com.controller;

import com.dao.UserDao;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDao userDao;

    /* 获取列表 */
    @GetMapping(value = "/")
    public List<User> list() {
        return userDao.findAll();
    }

    /* 新增 */
    @PostMapping(value = "/")
    public User post(@ModelAttribute User user) {
        return userDao.save(user);
    }

    /* 获取单个 */
    @GetMapping(value = "/{id}")
    public User get(@PathVariable Long id) {
        return userDao.findById(id).get();
    }

    /* 修改 */
    @PutMapping(value = "/{id}")
    public User put(@PathVariable Long id, @ModelAttribute User user) {
        user.setId(id);
        return userDao.save(user);
    }

    /* 删除 */
    @DeleteMapping(value = "/{id}")
    public Map delete(@PathVariable Long id) {
        userDao.deleteById(id);
        Map map = new LinkedHashMap();
        map.put("result", "删除成功");
        return map;
    }

}

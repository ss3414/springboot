package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller /* 控制器 */
@RequestMapping("") /* 路由 */
public class IndexController {

    @ResponseBody /* 指定响应体为方法的返回值（Map自动转JSON） */
    @RequestMapping("/")
    public Map index() {
        Map map = new LinkedHashMap();
        map.put("status", 1000);
        return map;
    }

}

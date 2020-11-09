package com.controller;

import com.dao.UserDao;
import com.model.PageBean;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("")
public class IndexController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("/index");
    }

    @GetMapping("/list")
    public ModelAndView list(
            HttpServletRequest request,
            @RequestParam(defaultValue = "1") Integer currentPage) {
        int pageSize = 2;
        Page<User> userPage = userDao.findAll(PageRequest.of(currentPage - 1, pageSize));
        PageBean pageBean = new PageBean(userPage.getContent(), (int) userPage.getTotalElements(), pageSize, currentPage);
        pageBean.setPageURL(request.getRequestURI() + "?currentPage");

        ModelAndView view = new ModelAndView();
        view.addObject("pageBean", pageBean);
        view.setViewName("/list");
        return view;
    }

    @GetMapping("/user")
    public ModelAndView user(@RequestParam(defaultValue = "1") Long id) {
        ModelAndView view = new ModelAndView();
        view.addObject("user", userDao.findById(id).get());
        view.setViewName("/user");
        return view;
    }

}

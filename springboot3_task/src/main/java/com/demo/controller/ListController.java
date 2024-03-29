package com.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.mapper.QrtzCronTriggersMapper;
import com.demo.mapper.QrtzFiredTriggersMapper;
import com.demo.mapper.QrtzJobDetailsMapper;
import com.demo.mapper.QrtzTriggersMapper;
import com.demo.model.QrtzCronTriggers;
import com.demo.model.QrtzFiredTriggers;
import com.demo.model.QrtzJobDetails;
import com.demo.model.QrtzTriggers;
import com.demo.task.ScheduledTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("")
public class ListController {

    @Autowired
    private QrtzJobDetailsMapper qrtzJobDetailsMapper;

    @Autowired
    private QrtzTriggersMapper qrtzTriggersMapper;

    @Autowired
    private QrtzCronTriggersMapper qrtzCronTriggersMapper;

    @Autowired
    private QrtzFiredTriggersMapper qrtzFiredTriggersMapper;

    @RequestMapping("/listJob")
    public Map<String, Object> listJob() {
        List<QrtzJobDetails> qrtzJobDetailsList = qrtzJobDetailsMapper.selectList(new QueryWrapper<>());

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("qrtzJobDetailsList", qrtzJobDetailsList);
        return map;
    }

    @RequestMapping("/listTrigger")
    public Map<String, Object> listTrigger() {
        List<QrtzTriggers> qrtzTriggersList = qrtzTriggersMapper.selectList(new QueryWrapper<>());

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("qrtzTriggersList", qrtzTriggersList);
        return map;
    }

    @RequestMapping("/listCron")
    public Map<String, Object> listCron() {
        List<QrtzCronTriggers> qrtzCronTriggersList = qrtzCronTriggersMapper.selectList(new QueryWrapper<>());

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("qrtzCronTriggersList", qrtzCronTriggersList);
        return map;
    }

    @RequestMapping("/listFired")
    public Map<String, Object> listFired() {
        List<QrtzFiredTriggers> qrtzFiredTriggersList = qrtzFiredTriggersMapper.selectList(new QueryWrapper<>());

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("qrtzFiredTriggersList", qrtzFiredTriggersList);
        return map;
    }

    /************************************************************分割线************************************************************/

    @Autowired
    private ScheduledTask scheduledTask;

    @RequestMapping("/listScheduled")
    public ModelAndView listScheduled() {
        Method[] methods = ScheduledTask.class.getDeclaredMethods();
        ModelAndView view = new ModelAndView();
        view.addObject("methods", methods);
        view.setViewName("/scheduled");
        return view;
    }

    @RequestMapping("/execute")
    public Map<String, Object> execute(String methodName) {
        try {
            Method method = scheduledTask.getClass().getDeclaredMethod(methodName, null);
            method.invoke(scheduledTask, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new LinkedHashMap<>();
    }

}

package com.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mapper.QrtzCronTriggersMapper;
import com.mapper.QrtzFiredTriggersMapper;
import com.mapper.QrtzJobDetailsMapper;
import com.mapper.QrtzTriggersMapper;
import com.model.QrtzCronTriggers;
import com.model.QrtzFiredTriggers;
import com.model.QrtzJobDetails;
import com.model.QrtzTriggers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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

    @GetMapping("/listJob")
    public Map<String, Object> listJob() {
        List<QrtzJobDetails> qrtzJobDetailsList = qrtzJobDetailsMapper.selectList(new QueryWrapper<>());

        Map<String, Object> map = new HashMap<>();
        map.put("qrtzJobDetailsList", qrtzJobDetailsList);
        return map;
    }

    @GetMapping("/listTrigger")
    public Map<String, Object> listTrigger() {
        List<QrtzTriggers> qrtzTriggersList = qrtzTriggersMapper.selectList(new QueryWrapper<>());

        Map<String, Object> map = new HashMap<>();
        map.put("qrtzTriggersList", qrtzTriggersList);
        return map;
    }

    @GetMapping("/listCron")
    public Map<String, Object> listCron() {
        List<QrtzCronTriggers> qrtzCronTriggersList = qrtzCronTriggersMapper.selectList(new QueryWrapper<>());

        Map<String, Object> map = new HashMap<>();
        map.put("qrtzCronTriggersList", qrtzCronTriggersList);
        return map;
    }

    @GetMapping("/listFired")
    public Map<String, Object> listFired() {
        List<QrtzFiredTriggers> qrtzFiredTriggersList = qrtzFiredTriggersMapper.selectList(new QueryWrapper<>());

        Map<String, Object> map = new HashMap<>();
        map.put("qrtzFiredTriggersList", qrtzFiredTriggersList);
        return map;
    }

}

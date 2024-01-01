package com.demo.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.mapper.NodeMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ExcelListener extends AnalysisEventListener<ExcelData> {

    private final List<ExcelData> dataList = new ArrayList<>();

    private final NodeMapper nodeMapper;

    public ExcelListener(NodeMapper nodeMapper) {
        this.nodeMapper = nodeMapper;
    }

    @Override
    public void invoke(ExcelData data, AnalysisContext analysisContext) {
        dataList.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info(dataList.toString());
        log.info(nodeMapper.selectList(new QueryWrapper<>()).toString());
    }

}

package com.util.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mapper.NodeMapper;

import java.util.ArrayList;
import java.util.List;

public class ExcelListener extends AnalysisEventListener<ExcelData> {

    private List<ExcelData> dataList = new ArrayList<>();

    private NodeMapper nodeMapper;

    public ExcelListener(NodeMapper nodeMapper) {
        this.nodeMapper = nodeMapper;
    }

    @Override
    public void invoke(ExcelData data, AnalysisContext analysisContext) {
        dataList.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println(dataList);
        System.out.println(nodeMapper.selectList(new QueryWrapper<>()));
    }

}

package com.demo.async.service;

import com.demo.annotation.Consume;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.mapper.ProductMapper;
import com.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Future;

@Service
public class ProductService extends ServiceImpl<ProductMapper, Product> {

    @Autowired
    private ProductMapper productMapper;

    @Async
    @Consume(unit = "ms")
    public Future<List<Product>> selectAll() {
        return new AsyncResult<>(productMapper.selectList(new QueryWrapper<>()));
    }

}

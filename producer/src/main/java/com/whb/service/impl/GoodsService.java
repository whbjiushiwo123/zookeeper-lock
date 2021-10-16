package com.whb.service.impl;

import com.whb.dao.GoodsMapper;
import com.whb.model.GoodsInfoEntity;
import com.whb.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService implements IGoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Override
    @CachePut(value = {"goodsInfo"}, key = "#id")
    public List<GoodsInfoEntity> getGoodsInfo(String id) {
        List<GoodsInfoEntity> entities= goodsMapper.getGoodsInfo(id);
        return entities;
    }
}

package com.whb.service;

import com.whb.model.GoodsInfoEntity;

import java.util.List;

public interface IGoodsService {
    List<GoodsInfoEntity> getGoodsInfo(String id);
}

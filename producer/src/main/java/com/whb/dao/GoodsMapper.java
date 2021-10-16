package com.whb.dao;

import java.util.List;

import com.whb.model.GoodsInfoEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsMapper {
    List<GoodsInfoEntity> getGoodsInfo(@Param("id") String id);
}
package com.whb.dao;

import com.whb.model.OrderInfoEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {
    int saveOrder(OrderInfoEntity orderInfoEntity);
}

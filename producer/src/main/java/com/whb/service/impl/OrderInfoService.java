package com.whb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.whb.dao.OrderMapper;
import com.whb.model.OrderInfoEntity;
import com.whb.mq.OrderSendMQ;
import com.whb.service.IOrderInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

@Service
public class OrderInfoService implements IOrderInfoService {
    private Logger logger = LoggerFactory.getLogger(OrderInfoService.class);
    private final static String ORDER_SAVE_EXCHANGE = "order-save-exchange";
    private final static String ORDER_SAVE_ROUTEKEY = "order";

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderSendMQ sendMQ;

    @Override
    public void saveOrder(OrderInfoEntity orderInfoEntity) {
        logger.info("保存订单------------------- start："+JSONObject.toJSONString(orderInfoEntity));
        orderMapper.saveOrder(orderInfoEntity);
        sendMQ.sendOrder(orderInfoEntity);
        logger.info("保存订单------------------- end");

    }
}

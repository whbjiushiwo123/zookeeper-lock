package com.whb.mq;

import com.alibaba.fastjson.JSONObject;
import com.whb.model.OrderInfoEntity;
import com.whb.util.EncrypDES;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderSendMQ {
    private static Logger logger = LoggerFactory.getLogger(OrderSendMQ.class);
    @Autowired
    private RabbitTemplate rabbitTemplate;
    public void sendOrder(OrderInfoEntity orderInfoEntity){
        logger.info("开始发送消息------------------- start："+JSONObject.toJSONString(orderInfoEntity));
        MessageProperties properties = new MessageProperties();
        properties.setDelay(5000);
        properties.setExpiration("5000");
        rabbitTemplate.send("order-save-exchange","order",
                new Message(EncrypDES.encrypt(JSONObject.toJSONString(orderInfoEntity).getBytes(),"12345678"),properties));
        logger.info("end-------------------消息发送结束！");
    }
}

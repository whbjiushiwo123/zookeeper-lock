package com.whb.controller;

import com.whb.model.OrderInfoEntity;
import com.whb.service.IOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/orderInfo")
public class OrderInfoController {
    @Autowired
    private IOrderInfoService orderInfoService;
    @ResponseBody
    @RequestMapping("/saveOrder")
    public String saveOrder(@RequestBody OrderInfoEntity orderInfoEntity){
        orderInfoService.saveOrder(orderInfoEntity);
        return "OK";
    }
}

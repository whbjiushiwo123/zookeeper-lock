package com.whb.controller;

import com.alibaba.fastjson.JSONObject;
import com.whb.model.GoodsInfoEntity;
import com.whb.service.IGoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/goodsInfo")
public class GoodsInfoController {
    private Logger logger = LoggerFactory.getLogger(GoodsInfoController.class);
    @Autowired
    private IGoodsService goodsService;
    public static Map m = new HashMap<>();
    @ResponseBody
    @RequestMapping("/getGoodsInfo")
    public List<GoodsInfoEntity>  getGoodsInfo(@RequestBody GoodsInfoEntity entity) throws InterruptedException {
        System.out.println("开始："+ JSONObject.toJSONString(entity));
        List<GoodsInfoEntity> infoEntities = goodsService.getGoodsInfo(entity.getId());
        return infoEntities;
    }

}

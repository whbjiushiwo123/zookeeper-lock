package org.example;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.CountDownLatch;

@RunWith(SpringJUnit4ClassRunner.class) //使用junit4进行测试

@ContextConfiguration(locations={"classpath:testApplication.xml"}) //加载配置文件
public class AppTest {
//    private IGoodsService goodsService;
    @Test
    public void test02(){
//        List<GoodsInfoEntity> entities =  goodsService.getGoodsInfo("1");
//        System.out.println("查到商品数据:"+JSONObject.toJSON(entities));
    }
}

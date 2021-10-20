package com.whb.config;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.CountDownLatch;

@Configuration
public class ZookeeperConfig {
    private static Logger logger = LoggerFactory.getLogger(ZookeeperConfig.class);
    @Value("${zookeeper.address}")
    private String connectString;

    @Value("${zookeeper.timeout}")
    private int timeout;
    @Bean(name = "zkClient")
    public ZooKeeper zkClient(){
        ZooKeeper zooKeeper = null;
        try{
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            zooKeeper = new ZooKeeper(connectString, timeout, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    if(Event.KeeperState.SyncConnected == watchedEvent.getState()){
                        countDownLatch.countDown();
                    }
                }
            });
            countDownLatch.await();
            logger.info("【初始化ZooKeeper连接状态....】={}",zooKeeper.getState());
        }catch (Exception e){
            logger.error("初始化ZooKeeper连接异常....】={}",e);
        }
        return  zooKeeper;
    }
}

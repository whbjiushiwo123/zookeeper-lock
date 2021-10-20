package com.whb.util;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class ZkApi {
    private static Logger logger = LoggerFactory.getLogger(ZkApi.class);
    @Autowired
    private ZooKeeper zkClient;

    public Stat exits(String path,boolean needWatch){
        try {
            return zkClient.exists(path,needWatch);
        } catch (Exception e) {
            logger.error("【断指定节点是否存在异常】{},{}",path,e);
            e.printStackTrace();
            return null;
        }
    }

    public Stat exits(String path, Watcher watcher){
        try {
            return zkClient.exists(path,watcher);
        } catch (Exception e) {
            logger.error("【断指定节点是否存在异常】{},{}",path,e);
            e.printStackTrace();
            return null;
        }
    }

    public boolean createNode(String path,String data){
        try {
            zkClient.create(path,data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            return true;
        } catch (Exception e) {
            logger.error("【创建持久化节点异常】{},{},{}",path,data,e);
            return false;
        }
    }


    public boolean updateNode(String path,String data){
        //zk的数据版本是从0开始计数的。如果客户端传入的是-1，
        // 则表示zk服务器需要基于最新的数据进行更新。
        // 如果对zk的数据节点的更新操作没有原子性要求则可以使用-1.
        //version参数指定要更新的数据的版本, 如果version和真实的版本不同,
        // 更新操作将失败. 指定version为-1则忽略版本检查
        try {
            zkClient.setData(path,data.getBytes(),-1);
            return true;
        } catch (Exception e) {
            logger.error("【修改持久化节点异常】{},{},{}",path,data,e);
            return false;
        }
    }

    public boolean deleteNode(String path){
        //version参数指定要更新的数据的版本, 如果version和真实的版本不同, 更新操作将失败. 指定version为-1则忽略版本检查
        try {
            zkClient.delete(path,-1);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 获取当前节点的子节点(不包含孙子节点)
     * @param path 父节点path
     */
    public List<String> getChildren(String path){
        try {
           return zkClient.getChildren(path,false);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public String getData(String path,Watcher watcher){
        try {
            Stat stat = new Stat();
            byte[] bytes = zkClient.getData(path,watcher,stat);
            return new String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostConstruct
    public void init(){
        String path="/zk-watcher-2";
        logger.info("【执行初始化测试方法。。。。。。。。。。。。】");
        createNode(path,"测试");
        String value=getData(path,new WatcherApi());
        logger.info("【执行初始化测试方法getData返回值。。。。。。。。。。。。】={}",value);

        // 删除节点出发 监听事件
        deleteNode(path);
    }
}

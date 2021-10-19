package com.whb;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class Zookeeper_Constructor_Usage_Simple extends ZookeeperBase{


    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        Zookeeper_Constructor_Usage_Simple simple = new Zookeeper_Constructor_Usage_Simple();
        simple.connect("39.101.143.248:2181");
        System.out.println("开始给zookeeper服务端创建节点=========");
        simple.create("/zk-test-ephemeral-","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL,
                new IStringCallback(),"I am contest");
        simple.create("/zk-test-ephemeral-","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL,
                new IStringCallback(),"I am contest");
        Thread.sleep(10000);
    }
    /**
     * 异步创建节点：方法中调用zookeeper实例的create()方法来创建一个znode;
     * @param path znode节点的绝对路径
     * @param bytes znode节点的内容（一个二进制数组）
     * @param ACL access control list(ACL，访问控制列表，这里使用完全开放模式)
     * @param createMode znode的性质，分为EPHEMERAL（临时）、PERSISTENT（持久）、EPHEMERAL_SEQUENTIAL临时顺序和PERSISTENT_SEQUENTIAL持久顺序
     * @param string 一个java的字符串
     * @param iStringCallback 回调函数
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void create(String path,byte[] bytes,ArrayList<ACL> ACL,CreateMode createMode, IStringCallback iStringCallback, String string) throws KeeperException, InterruptedException{
        create(path,bytes,ACL,createMode);
    }
    /**
     * 同步删除znode的节点方法
     * @param path znode节点的绝对路径
     * @param version znode节点的版本号（-1表示不匹配版本号）
     * @throws InterruptedException
     * @throws KeeperException
     */
    public void syncDelete(String path,int version) throws InterruptedException, KeeperException{
        zooKeeper.delete(path, version);
    }

}

class IStringCallback implements AsyncCallback.StringCallback{

    @Override
    public void processResult(int rc, String path, Object ctx, String name) {
        System.out.println("Create path result: [" + rc + ", " + path + ", " + ctx + ", real path name: " + name);
    }
}

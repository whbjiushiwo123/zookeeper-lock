package com.whb;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class ZookeeperBase  implements Watcher {
    private static final int SESSION_TIMEOUT = 5000;

    //对执行中的线程进行管理，等待线程完成某些操作后，再对此线程做处理（起到过河拆桥、卸磨杀驴的作用）
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    ZooKeeper zooKeeper;
    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("Recive watched event: "+watchedEvent);
        if(Event.KeeperState.SyncConnected == watchedEvent.getState()){
            System.out.println("连接成功：不再阻塞当前线程");
            connectedSemaphore.countDown();
        }
    }
    public void create(String path, byte[]bytes, ArrayList<ACL> acls, CreateMode createMode) throws InterruptedException, KeeperException {
        String znodePath = zooKeeper.create(path,bytes,acls,createMode);
        System.out.println("Success create znode: " + znodePath);
    }


    public void connect(String host) throws IOException, InterruptedException {
        zooKeeper = new ZooKeeper(host,SESSION_TIMEOUT,this);
        System.out.println("zk的连接状态："+zooKeeper.getState());
        connectedSemaphore.await();
        System.out.println("Zookeeper session established");
    }

}

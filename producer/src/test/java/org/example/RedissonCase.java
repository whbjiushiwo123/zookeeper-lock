package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

public class RedissonCase {
    private RedissonClient client;
    private RedissonReactiveClient reactiveClient;
    private RedissonRxClient rxClientl;


    @Before
    public void before(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://39.101.143.248:6381").setPassword("80909891aB=");
        config.useSingleServer().setConnectionMinimumIdleSize(10);
        client = Redisson.create(config);
        reactiveClient = Redisson.createReactive(config);
        rxClientl = Redisson.createRx(config);
    }
    @After
    public void after(){
        client.shutdown();
        reactiveClient.shutdown();
        rxClientl.shutdown();
    }

    //限流
    @Test
    public void rateLimiter() throws InterruptedException {
        RRateLimiter limiter = client.getRateLimiter("rateLimiter");
        //初始化 最大流速:每1秒钟产生5个令牌
        limiter.trySetRate(RateType.OVERALL,5,1,RateIntervalUnit.SECONDS);
        for(int i=0;i<10;i++){
            new Thread(new Runnable() {
                int i=0;
                @Override
                public void run() {
                    limiter.acquire(1);
                    System.out.println(Thread.currentThread() + "-" + System.currentTimeMillis() + "-" + i++);
                }
            }).start();
        }
        Thread.sleep(10000 * 5);
    }
    /**
     * RMap实现了java.util.concurrent.ConcurrentMap接口和java.util.Map接口
     * @throws Exception
     */
    @Test
    public void map(){
        RMap<String ,String>map = client.getMap("map");
        map.put("name", "赵云");
        map.put("location", "常山");
        map.put("camp", "蜀");
        map.remove("location");
        map.forEach((key, value) -> {System.out.println("key=" + key + ",value=" + value);});

    }

    @Test
    public void lock() throws InterruptedException {
        RLock lock = client.getLock("lock");
        for(int i=0;i<30;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    lock.lock();
                    try{
                        System.out.println(Thread.currentThread() + "-" + System.currentTimeMillis() + "-" + "获取了锁");
                        Thread.sleep(1000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        lock.unlock();
                    }
                }
            }).start();
            Thread.sleep(1000 * 5);
        }
    }
    /**
     * Redisson利用Redis实现了Java分布式布隆过滤器（Bloom Filter）
     */
    @Test
    public void bf(){
        RBloomFilter<String> bf = client.getBloomFilter("qq");
        if(!bf.isExists()){
            bf.tryInit(150000000L, 0.05);
            bf.add("test");
            bf.expire(200, TimeUnit.SECONDS);
        }

        bf.add("https://www.baidu.com/");
        bf.add("https://www.tmall.com/");
        bf.add("https://www.jd.com/");
        System.out.println(bf.contains("https://www.tmall.com/"));
        System.out.println(bf.count());
    }
}

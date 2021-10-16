package org.example;

import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;
import java.util.logging.SimpleFormatter;

public class ThreadChangeDemo {
    public static void main(String [] args) throws InterruptedException {
        dynamicModifyExecutor();
    }

    private static void dynamicModifyExecutor() throws InterruptedException {
        ThreadPoolExecutor executor = buildThreadPoolExecutor();
        for(int i=0;i<15;i++){
            executor.submit(()->{
                threadPoolStatus(executor,"创建任务"+new Date());
                try{
                    TimeUnit.SECONDS.sleep(10);
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
        }
        threadPoolStatus(executor,"改变之前");
        executor.setCorePoolSize(10);
        executor.setMaximumPoolSize(10);
        threadPoolStatus(executor,"改变之后");
        Thread.currentThread().join();
    }

    private static void threadPoolStatus(ThreadPoolExecutor executor, String name) {
        LinkedBlockingQueue queue = (LinkedBlockingQueue) executor.getQueue();
        System.out.println(Thread.currentThread().getName()+"-"+name+"-:"+
                "核心线程数："+executor.getCorePoolSize()+
                "活动线程数："+executor.getActiveCount()+
                "最大线程数："+executor.getMaximumPoolSize()+
                "线程池活跃度："+divide(executor.getActiveCount(),executor.getMaximumPoolSize())+
                "任务完成数："+executor.getCompletedTaskCount()+
                "队列大小："+(queue.size()+queue.remainingCapacity())+
                "当前任务排队数："+queue.size()+
                "队列剩余大小："+queue.remainingCapacity()+
                "队列使用度："+divide(queue.size(),queue.size()+queue.remainingCapacity()));
    }

    private static String divide(int num1, int num2) {
        return String.format("%1.2f%%",Double.parseDouble(num1+"")/Double.parseDouble(num2+"")*100);
    }

    private static ThreadPoolExecutor buildThreadPoolExecutor() {
        return new ThreadPoolExecutor(2,5,60,TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(10),new NamedThreadFactory("why技术"));
    }


}
class NamedThreadFactory implements ThreadFactory {
    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss");
    private String name;
    public NamedThreadFactory(String name) {
        this.name = name;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        String tn = sdf.format(new Date())+"-"+name;
        thread.setName(tn+"-"+thread.getName());
        return thread;
    }
}
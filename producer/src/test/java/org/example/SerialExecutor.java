package org.example;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;

public class SerialExecutor implements Executor {
    final Queue<Runnable> tasks = new ArrayDeque<>();
    final Executor executor;
    Runnable active;

    SerialExecutor(Executor executor){
        this.executor =executor;
    }
    @Override
    public synchronized void execute(Runnable command) {
        tasks.offer(new Runnable() {
            @Override
            public void run() {
                try{
                    command.run();
                }finally {
                    scheduleNext();
                }
            }
        });
        if(active == null){
            scheduleNext();
        }
    }
    protected void scheduleNext() {
        if((active = tasks.poll()) != null){
            active.run();
        }
    }
}

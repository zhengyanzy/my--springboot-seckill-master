package com.jesper.seckill.limit;

import com.google.common.util.concurrent.RateLimiter;
import com.jesper.seckill.result.CodeMsg;
import com.jesper.seckill.result.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by jiangyunxiong on 2018/5/31.
 */
public class RateLimiterDemo {
    public static void main(String[] args) {

        //1秒钟只有两个线程跑，也就是，第二个线程等待第一个现场0.5秒
        RateLimiter rateLimiter = RateLimiter.create(2);
        List<Runnable> tasks = new ArrayList<Runnable>();
        for(int i = 0;i < 100; i++){
            tasks.add(new UserRequest(i));
        }
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (Runnable runnable : tasks){
            //每个线程需要等待1/4秒
            //System.out.println("等待时间：" + rateLimiter.acquire());

            if(rateLimiter.tryAcquire(400, TimeUnit.MILLISECONDS)) { //未请求到limiter则立即返回false
                //doSomething();
                System.out.println("成功");
            }else{
                //doSomethingElse();
                System.out.println("失败");
            }
            threadPool.execute(runnable);
        }
    }

    private static class UserRequest implements Runnable {
        private int id;

        public UserRequest(int id) {
            this.id = id;
        }

        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(id+"执行run");
        }
    }
}

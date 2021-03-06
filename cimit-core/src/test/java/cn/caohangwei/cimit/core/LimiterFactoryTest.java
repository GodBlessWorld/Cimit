package cn.caohangwei.cimit.core;

import cn.caohangwei.cimit.common.CimitRule;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

class LimiterFactoryTest {

    @Test
    void getLeakyBucketLimiter() {
        LeakyBucketLimiter limiter = (LeakyBucketLimiter) CimitFactory.getLeakyBucketLimiter("Cimit");
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                if (limiter.tryAcquire()) {
                    System.out.println("Hello Cimit!");
                }
            }).start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void distributed(){
        CimitRule rule = new CimitRule("distributed",10,10,1, TimeUnit.SECONDS,true);
        AbstractLimiter limiter = CimitFactory.getLeakyBucketLimiter(rule);
        limiter.tryAcquire();
        for(int i=0;i<20;i++){
            new Thread(()->{
                if(limiter.tryAcquire()){
                    System.out.println("Hello Cimit!");
                }
            }).start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
package com.example.rabbitmq.completableFuture;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Function;

public class CompletableFutureDemo {
    private static final Executor executor = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        System.out.println("demo start....." + startTime);
        test();
        System.out.println("demo end.....costTime = " + (System.currentTimeMillis() - startTime));
    }

    public static void test(){
        List resultList = new ArrayList();
        List<CompletableFuture<List>> completableFutureList = new ArrayList<>();
        completableFutureList.add(testA().thenApplyAsync(new Function<List<Logger1>, List>() {
            @Override
            public List apply(List<Logger1> logger1s) {
                logger1s.stream().forEach(a ->{
                    Logger2 logger2 = new Logger2();
                    logger2.setUser(a.getUser());
                    logger2.setReqUrl(a.getReqUrl());
                    resultList.add(logger2);
                });
                return logger1s;
            }
        },executor));
        completableFutureList.add(testB().thenApplyAsync(new Function<List<Logger2>, List>() {
            @Override
            public List apply(List<Logger2> logger2s) {
                resultList.addAll(logger2s);
                return logger2s;
            }
        },executor));

        CompletableFuture.allOf(completableFutureList.toArray(new CompletableFuture[completableFutureList.size()])).join();
        System.out.println("完事了......................."+resultList);

    }

    public static CompletableFuture<List<Logger1>> testA(){
        System.out.println("testA start+++++++++++" + System.currentTimeMillis());

        return CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(1000);
                System.out.println("线程A：" + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            List<Logger1> list = new ArrayList<>();
            Logger1 logger1 = new Logger1();
            logger1.setUser("zhaoyun");
            logger1.setReqUrl("baidu.com");
            list.add(logger1);
            return list;
        });
    }

    public static CompletableFuture<List<Logger2>> testB(){
        System.out.println("testB start+++++++++++" + System.currentTimeMillis());
        return CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(2000);
                System.out.println("线程A：" + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            List<Logger2> list = new ArrayList<>();
            Logger2 logger2 = new Logger2();
            logger2.setUser("guanyu");
            logger2.setReqUrl("alibaba.com");
            list.add(logger2);
            return list;
        });
    }

}

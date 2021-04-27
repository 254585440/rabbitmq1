package com.example.rabbitmq.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @description
 * @author: Sam.Zhao
 * @date: 2021-04-27 10:54
 **/
public class Test1 {

    /**
     * 这个方式也可以使线程阻塞等待
     * @param args
     */
    public static void main(String[] args) {
        CompletableFuture<Logger1> supplyAsync1= CompletableFuture.supplyAsync(()->{
           return getLogger1();
        });

        CompletableFuture<Logger2> supplyAsync2= CompletableFuture.supplyAsync(()->{
           return getLogger2();
        });

        try {
            Logger1 logger1 = supplyAsync1.get();
            Logger2 logger2 = supplyAsync2.get();
            System.out.println(logger1);
            System.out.println(logger2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static Logger1 getLogger1(){
        System.out.println("进入logger1");
        Logger1 logger1 = new Logger1();
        logger1.setUser("user1");
        logger1.setReqUrl("reqUrl1");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return logger1;
    }
    private static Logger2 getLogger2(){
        System.out.println("进入logger2");
        Logger2 logger2 = new Logger2();
        logger2.setUser("user2");
        logger2.setReqUrl("reqUrl2");
        return logger2;
    }
}

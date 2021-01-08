package com.example.rabbitmq.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class CompletableFutureTest {

    @Autowired
    private ThreadPoolTaskExecutor executors;

    @RequestMapping("/test")
    public void test(){
        List<CompletableFuture<String>> list = new ArrayList<>();
        CompletableFuture<String> str1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("我是1");
            return "1";
        },executors);


        CompletableFuture<String> str2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("我是2");
            return "2";
        },executors);

        CompletableFuture<String> str3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("我是3");
            return "3";
        },executors);

        str1.thenAcceptAsync(System.out::println);
        str2.thenAcceptAsync(System.out::println);
        str3.thenAcceptAsync(System.out::println);

        list.add(str1);
        list.add(str2);
        list.add(str3);
        CompletableFuture[] completableFuture =list.stream().toArray(size -> new CompletableFuture[size]);
        CompletableFuture.allOf(completableFuture).join();
        System.out.println("完成");
    }

}

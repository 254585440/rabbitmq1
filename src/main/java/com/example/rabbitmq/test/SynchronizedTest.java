package com.example.rabbitmq.test;

public class SynchronizedTest {

    public static void main(String[] args) {
        final Object lock1 = new Object();
        final Object lock2 = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock1){
                    try{
                        Thread.sleep(2000);
                        System.out.println("synchronized1 lock lock1");
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    synchronized(lock2){
                        System.out.println("synchronized1 lock lock2");
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized(lock2){
                    System.out.println("synchronized2 lock lock2");
                    synchronized (lock1){
                        System.out.println("synchronized2 lock lock1");
                    }
                }
            }
        }).start();

    }

}

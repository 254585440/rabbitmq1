package com.example.rabbitmq.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SnycTest {
    static int count = 1;

    public void print1(){
        for(int i=0;i<10;i++){
            try{
                synchronized (SnycTest.class){
                    while(count != 1){
                        SnycTest.class.wait();
                    }
                    count = 2;
                    System.out.println(Thread.currentThread().getName() + ":" + i);
                    SnycTest.class.notifyAll();
                }

            } catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    public void print2(){
        for(int i=0;i<10;i++){
            try{
                synchronized (SnycTest.class){
                    while(count != 2){
                        SnycTest.class.wait();
                    }
                    count = 3;
                    System.out.println(Thread.currentThread().getName() + ":" + i);
                    SnycTest.class.notifyAll();
                }

            } catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    public void print3(){
        for(int i=0;i<10;i++){
            try{
                synchronized (SnycTest.class){
                    while(count != 3){
                        SnycTest.class.wait();
                    }
                    count = 1;
                    System.out.println(Thread.currentThread().getName() + ":" + i);
                    SnycTest.class.notifyAll();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        SnycTest test = new SnycTest();
        new Thread(()-> test.print1()).start();
        new Thread(()-> test.print2()).start();
        new Thread(()-> test.print3()).start();
    }

}

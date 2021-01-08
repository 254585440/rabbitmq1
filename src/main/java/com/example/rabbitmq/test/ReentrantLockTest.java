package com.example.rabbitmq.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    //公平锁，非公平锁
    private static final Lock lock = new ReentrantLock(true);
    public static void main(String[] args) {
        new Thread(()-> test(),"线程A").start();
        new Thread(()-> test(),"线程B").start();
        new Thread(()-> test(),"线程C").start();
        new Thread(()-> test(),"线程D").start();
        new Thread(()-> test(),"线程E").start();
    }
    public static void test(){
        for(int i=0;i<2;i++){
            try{
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "获取了锁");
                Thread.sleep(200);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                System.out.println(Thread.currentThread().getName() + "释放了锁");
                lock.unlock();
            }
        }
    }


}

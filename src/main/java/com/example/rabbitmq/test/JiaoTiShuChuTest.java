package com.example.rabbitmq.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class JiaoTiShuChuTest {
    ReentrantLock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    static int count = 1;

    public void print1(){
        for(int i=0;i<10;i++){
            try{
                lock.lock();
                while(count != 1){
                    condition1.await();
                }
                count = 2;
                System.out.println(Thread.currentThread().getName() + ":" + i);
                condition2.signal();
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

    }

    public void print2(){
        for(int i=0;i<10;i++){
            try{
                lock.lock();
                while(count != 2){
                    condition2.await();
                }
                count = 3;
                System.out.println(Thread.currentThread().getName() + ":" + i);
                condition3.signal();
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

    }

    public void print3(){
        for(int i=0;i<10;i++){
            try{
                lock.lock();
                while(count != 3){
                    condition3.await();
                }
                count = 1;
                System.out.println(Thread.currentThread().getName() + ":" + i);
                condition1.signal();
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

    }

    public static void main(String[] args) {
        JiaoTiShuChuTest test = new JiaoTiShuChuTest();
        new Thread(()-> test.print1()).start();
        new Thread(()-> test.print2()).start();
        new Thread(()-> test.print3()).start();
    }

}

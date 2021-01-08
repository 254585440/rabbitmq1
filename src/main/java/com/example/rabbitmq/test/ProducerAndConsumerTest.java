package com.example.rabbitmq.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerAndConsumerTest {
    private static Integer count = 0;
    private static Integer fullCount = 10;

    private ReentrantLock lock = new ReentrantLock();
    private final Condition emptyCondition = lock.newCondition();
    private final Condition fullCondition = lock.newCondition();

    public static void main(String[] args) {
        ProducerAndConsumerTest test = new ProducerAndConsumerTest();
        new Thread(test.new Producer()).start();
        new Thread(test.new Consumer()).start();
//        new Thread(test.new Producer()).start();
//        new Thread(test.new Consumer()).start();
//        new Thread(test.new Producer()).start();
//        new Thread(test.new Consumer()).start();
    }

    class Producer implements Runnable{

        @Override
        public void run() {
            for(int i=0;i<50;i++){
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //获取锁
                lock.lock();
                try{
                    while(count == fullCount){
                        fullCondition.await();
                    }
                    count++;
                    System.out.println(Thread.currentThread().getName() + "生产者生产，目前一共：" + count);
                    emptyCondition.signal();
                } catch (InterruptedException e){
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    class Consumer implements Runnable{

        @Override
        public void run() {
            for(int i=0;i<50;i++){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //获取锁
                lock.lock();
                try{
                    while(count == 0){
                        emptyCondition.await();
                    }
                    count--;
                    System.out.println(Thread.currentThread().getName() + "消费者消费，目前一共：" + count);
                    fullCondition.signal();
                } catch (InterruptedException e){
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}

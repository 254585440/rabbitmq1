package com.example.rabbitmq.test;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    static class ThreadTask extends Thread{
        Semaphore semaphore;
        public ThreadTask(Semaphore semaphore){
            this.semaphore = semaphore;
        }

        @Override
        public void run(){
            try {
                Thread.sleep(1000);
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + "acquire");
                Thread.sleep(1000);
                semaphore.release();
                System.out.println(Thread.currentThread().getName() + "release");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        for(int i=0;i<10;i++){
            new ThreadTask(semaphore).start();
        }
    }
}

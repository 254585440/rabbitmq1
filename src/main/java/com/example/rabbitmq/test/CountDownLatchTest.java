package com.example.rabbitmq.test;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

    static class TaskThread extends Thread{
        CountDownLatch countDownLatch;

        public TaskThread(CountDownLatch countDownLatch){
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run(){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                System.out.println("第"+Thread.currentThread().getName() + "驶过收费站...");
                countDownLatch.countDown();
                if(countDownLatch.getCount() == 0){
                    System.out.println("全部车辆行驶完毕，请放行");
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        System.out.println("A8888开过收费站");
        System.out.println("被告知需要等待前面两辆车先走");
        System.out.println("A8888停下等待");
        CountDownLatch latch = new CountDownLatch(2);

        for(int i=0;i<2;i++){
            TaskThread taskThread = new TaskThread(latch);
            taskThread.start();
        }

        latch.await();
        System.out.println("A8888继续行驶");
    }

}

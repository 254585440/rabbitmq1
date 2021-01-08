package com.example.rabbitmq.test;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

    static class ThreadTask extends Thread{
        CyclicBarrier cyclicBarrier;

        public ThreadTask(CyclicBarrier cyclicBarrier){
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println("第"+Thread.currentThread().getName() + "驶过收费站...");
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("A8888开过收费站");
        System.out.println("被告知需要等待前面两辆车先走");
        System.out.println("A8888停下等待");
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10, new Runnable() {
            @Override
            public void run() {
                System.out.println("全部车辆行驶完毕，请放行");
                System.out.println("A8888继续行驶");
            }
        });

        for(int i=0;i<10;i++){
            new ThreadTask(cyclicBarrier).start();
        }

        System.out.println("132131313131");
    }
}

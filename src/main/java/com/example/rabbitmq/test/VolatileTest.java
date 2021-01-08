package com.example.rabbitmq.test;

public class VolatileTest {
    public static void main(String[] args) {
        Aobing aobing = new Aobing();
        aobing.start();
        for(;;){
//            synchronized (aobing){
                if(aobing.isFlag()){
                    System.out.println("成功");
                    break;
                }
//            }
        }
    }

    static class Aobing extends Thread{
        private volatile boolean flag = false;

        public boolean isFlag(){
            return flag;
        }

        @Override
        public void run(){
            try{
                Thread.sleep(1000);
            } catch (Exception e){
                e.printStackTrace();
            }
            flag = true;
            System.out.println("flag=" + flag);
        }
    }
}

package com.example.rabbitmq.invoke;

/**
 * @description
 * @author: Sam.Zhao
 * @date: 2021-04-25 15:01
 **/
public class OrderImpl implements Order {

    @Override
    public boolean createOrder(String OrderInfo) {
        System.out.println("创建订单");
        return true;
    }
}

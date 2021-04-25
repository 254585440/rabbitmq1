package com.example.rabbitmq.invoke;

/**
 * @description
 * @author: Sam.Zhao
 * @date: 2021-04-25 15:00
 **/
public interface Order {

    boolean createOrder(String OrderInfo);
}

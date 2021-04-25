package com.example.rabbitmq.rabbitmq.helloword;

import com.example.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer {
    private final static String QUEUE_NAME = "simple_queue";
    public static void main(String[] args) throws Exception {
        // 1、获取到连接
        Connection connection = ConnectionUtil.getConnection();
        // 2、从连接中创建通道，使用通道才能完成消息相关的操作
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //参数1：消费哪个队列的消息
        //参数2：开启消息的自动确认机制
        //参数3：消费时的回调接口
        channel.basicConsume(QUEUE_NAME,true,new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                System.out.println("new String(body) = " + new String(body));
            }
        });
    }
}

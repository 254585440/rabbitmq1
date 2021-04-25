package com.example.rabbitmq.rabbitmq.topic;

import com.example.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer1 {
    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        //将通道声明交换机  //参数1：交换机名称 参数2：交换机类型  topic 动态路由模式
        channel.exchangeDeclare("topic","topic");

        //创建临时队列
        String queneName = channel.queueDeclare().getQueue();

        //基于路由key绑定交换机和队列 *匹配一个字符，#匹配多个字符
        channel.queueBind(queneName,"topic","user.*");

        //消费消息
        channel.basicConsume(queneName,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1：" + new String(body));
            }
        });

    }
}

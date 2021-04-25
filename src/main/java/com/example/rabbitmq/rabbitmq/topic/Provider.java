package com.example.rabbitmq.rabbitmq.topic;

import com.example.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Provider {
    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        //将通道声明交换机  //参数1：交换机名称 参数2：交换机类型  topic 动态路由模式
        channel.exchangeDeclare("topic","topic");

        //发送消息
        String key = "user.save";
        channel.basicPublish("topic",key,null,("这个topic发布的基于 key：{"+key+"}的消息").getBytes());

        ConnectionUtil.closeConnectionAndChanel(connection,channel);
    }
}

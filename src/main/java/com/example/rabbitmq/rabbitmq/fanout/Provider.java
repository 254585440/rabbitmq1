package com.example.rabbitmq.rabbitmq.fanout;

import com.example.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;


public class Provider {
    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        //将通道声明交换机  //参数1：交换机名称 参数2：交换机类型  fanout 广播类型
        channel.exchangeDeclare("orders","fanout");

        //发送消息
        channel.basicPublish("orders","",null,"fanout type message".getBytes());

        ConnectionUtil.closeConnectionAndChanel(connection,channel);
    }
}

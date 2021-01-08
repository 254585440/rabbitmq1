package com.example.rabbitmq.direct;

import com.example.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Provider {
    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        //将通道声明交换机  //参数1：交换机名称 参数2：交换机类型  direct 路由模式
        channel.exchangeDeclare("logs_direct","direct");

        //发送消息
        String key = "info";
        channel.basicPublish("logs_direct",key,null,("这个redict发布的基于 key：{"+key+"}的消息").getBytes());

        ConnectionUtil.closeConnectionAndChanel(connection,channel);
    }
}

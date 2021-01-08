package com.example.rabbitmq.workquene;

import com.example.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Provider {
    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        //通过通道声明队列
        channel.queueDeclare("work",true,false,false,null);

        for(int i=0;i<10;i++){
            //生产消息
            channel.basicPublish("","work",null,(i+"hello work quene").getBytes());
        }

        ConnectionUtil.closeConnectionAndChanel(connection,channel);
    }
}

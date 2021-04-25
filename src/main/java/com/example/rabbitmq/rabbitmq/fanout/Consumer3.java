package com.example.rabbitmq.rabbitmq.fanout;

import com.example.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;


public class Consumer3 {
    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        //将通道声明交换机  //参数1：交换机名称 参数2：交换机类型  fanout 广播类型
        channel.exchangeDeclare("orders","fanout");

        //创建临时队列
        String queneName = channel.queueDeclare().getQueue();

        //绑定交换机队列
        channel.queueBind(queneName,"orders","");

        //消费消息
        channel.basicConsume(queneName,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者3：" + new String(body));
            }
        });

    }
}

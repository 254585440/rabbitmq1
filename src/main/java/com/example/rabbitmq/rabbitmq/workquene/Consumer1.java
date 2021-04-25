package com.example.rabbitmq.rabbitmq.workquene;

import com.example.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer1 {
    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.basicQos(1);

        //通过通道声明队列
        channel.queueDeclare("work",true,false,false,null);

        channel.basicConsume("work",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                System.out.println("消费者1："+ new String(body));
                try{
                    Thread.sleep(5000);
                }catch (Exception e){
                    e.printStackTrace();
                }

                //手动确认
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });

    }
}

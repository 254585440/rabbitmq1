package com.example.rabbitmq.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnectionUtil {
    private static ConnectionFactory factory;

    static {
        //定义连接工厂
        factory = new ConnectionFactory();
        //设置服务地址
        factory.setHost("127.0.0.1");
        //端口
        factory.setPort(5672);
        //设置账号信息，用户名、密码、vhost
        factory.setVirtualHost("/ems");//设置虚拟机，一个mq服务可以设置多个虚拟机，每个虚拟机就相当于一个独立的mq
        factory.setUsername("ems");
        factory.setPassword("ems");
    }

    /**
     * 建立与RabbitMQ的连接
     * @return
     * @throws Exception
     */
    public static Connection getConnection() throws Exception {
        try{
            // 通过工厂获取连接
            Connection connection = factory.newConnection();
            return connection;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //关闭通道和关闭连接工具方法
    public static void closeConnectionAndChanel(Connection connection, Channel channel){
        try{
            if(channel!=null) channel.close();
            if(connection!=null) connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

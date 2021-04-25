package com.example.rabbitmq.invoke;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description
 * @author: Sam.Zhao
 * @date: 2021-04-25 15:01
 **/
public class ProxyTest implements InvocationHandler {
    Object target;

    public Object bind(Object target){
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("做限流操作1111");
        Object obj = method.invoke(target,args);
        System.out.println("做限流操作2222");
        return obj;
    }


    public static void main(String[] args) {
        ProxyTest proxyTest = new ProxyTest();
        Order order = (Order)proxyTest.bind(new OrderImpl());
        order.createOrder("");
    }
}

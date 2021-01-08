package com.example.rabbitmq;

import com.example.rabbitmq.aop.AspectInterface;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@ComponentScan("com.example.rabbitmq.aop")
public class SpringAopApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(SpringAopApplication.class);
        AspectInterface aspectInterface = annotationConfigApplicationContext.getBean(AspectInterface.class);
        aspectInterface.harden();
    }
}

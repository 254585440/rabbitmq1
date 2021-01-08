package com.example.rabbitmq.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@org.aspectj.lang.annotation.Aspect
@Component
public class Aspect {

    @Pointcut("execution(public * com.example.rabbitmq.aop.AspectServiceImpl.*(..))")
    public void Aspect(){

    }
//    @Pointcut("execution(public * com.example.rabbitmq.aop.AopTestController.*(..)) && args(point)")
//    public void Aspect(int point){
//
//    }

//    @Before("Aspect()")
//    public void beforeAspect(){
//        System.out.println("经纪人在处理赛前事务");
//    }
//
//    @After("Aspect()")
//    public void afterAspect(){
//        System.out.println("经纪人为球星鼓掌");
//    }
//
//    @AfterReturning("Aspect()")
//    public void afterReturningAspect(){
//        System.out.println("返回通知：经纪人为球星鼓掌");
//    }
//
//    @AfterThrowing("Aspect()")
//    public void afterThrowingAspect(){
//        System.out.println("异常通知：球迷要求退票");
//    }

    @Around("Aspect()")
    public void aroundAspect(ProceedingJoinPoint pjp){
        try {
            System.out.println("经纪人在处理赛前事务");
            pjp.proceed();
            System.out.println("经纪人为球星鼓掌");
        } catch (Throwable throwable) {
            System.out.println("异常通知：球迷要求退票");
        }
    }

//    @Around("Aspect(point)")
//    public void aroundAspect(ProceedingJoinPoint pjp,int point){
//        try {
//            System.out.println("经纪人在处理赛前事务");
//            pjp.proceed();
//            System.out.println("球星本场得到" + point +"分");
//        } catch (Throwable throwable) {
//            System.out.println("异常通知：球迷要求退票");
//        }
//    }

    public static void main(String[] args) {
        List list = new ArrayList();
        Map map = new HashMap();
        for(int i =0 ;i < 20;i++){
            list.add(i+1);
        }
    }

}

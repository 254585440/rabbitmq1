package com.example.rabbitmq.aop;

import java.lang.annotation.*;

/**
 * @description
 * @author: Sam.Zhao
 * @date: 2021-03-18 11:11
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuditLog {
    String operation();
}

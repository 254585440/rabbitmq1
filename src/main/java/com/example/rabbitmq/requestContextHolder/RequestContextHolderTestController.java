package com.example.rabbitmq.requestContextHolder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @description
 * @author: Sam.Zhao
 * @date: 2021-03-22 14:23
 **/
@RestController
public class RequestContextHolderTestController {
    @GetMapping("/hello")
    public void hello(String name) {

        new Thread(() -> {
        // 获取HttpServletRequest对象
        HttpServletRequest request = null;
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            request = ((ServletRequestAttributes) requestAttributes).getRequest();
        }

        if (request != null) {
            System.out.println("可以获取到request对象");
        } else {
            System.out.println("获取不到request对象");
        }
        }).start();
    }
}

package com.example.rabbitmq.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aopController1")
public class AopTestController1 {

    @GetMapping("/harden")
    @AuditLog(operation = "harden1")
    public void harden1() throws Exception {
        System.out.println("====+++++++++++++++++++++++");
    }
}

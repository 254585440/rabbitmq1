package com.example.rabbitmq.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aopController")
public class AopTestController {

    @Autowired
    private AspectInterface anInterface;

    @GetMapping("/harden")
    public void harden() throws Exception {
        anInterface.harden();
    }

    @RequestMapping("/durant/{point}")
    public void durant(@PathVariable("point") int point){
        System.out.println("杜兰特上场打球了！！");
    }

}

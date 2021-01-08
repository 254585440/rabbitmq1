package com.example.rabbitmq.aop;

import org.springframework.stereotype.Service;

@Service
public class AspectServiceImpl implements AspectInterface {
    @Override
    public void harden() {
        System.out.println("哈登上场打球了！！");
    }

    @Override
    public void durant() {

    }
}

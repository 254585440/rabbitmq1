package com.example.rabbitmq.aop;

import org.springframework.stereotype.Service;

@Service
public interface AspectInterface {

    void harden();
    void durant();
}

package com.example.rabbitmq.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @description
 * @author: Sam.Zhao
 * @date: 2021-04-25 15:26
 **/
public class ReduceTest {
    static List<Integer> list1 = new ArrayList<>();
    static {
        list1.add(1);
        list1.add(2);
        list1.add(3);
    }

    public Integer get(Function<Integer,Integer> by){
        return list1.stream().map(by).reduce((x,y)->x+y).get();
    }

    public static void main(String[] args) {
        ReduceTest reduceTest = new ReduceTest();
        System.out.println(reduceTest.get(e->2));
        System.out.println(reduceTest.get(e->e*reduceTest.get(e1->e1*2)));
        System.out.println(reduceTest.get(e->e*reduceTest.get(e1->e*2)));
    }
}

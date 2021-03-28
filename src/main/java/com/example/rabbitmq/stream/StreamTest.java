package com.example.rabbitmq.stream;

import com.rabbitmq.tools.json.JSONUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {
    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();
        userList.add(new User("1","牛旺旺",20,"上海市","1999-02-03 02:02:02"));
        userList.add(new User("2","赵龙飞",21,"北京市","2000-02-03 02:02:02"));
        userList.add(new User("3","张三",30,"北京市","1989-02-03 02:02:02"));
        userList.add(new User("4","李四",50,"北京市","1990-02-03 02:02:02"));
        userList.add(new User("5","牛旺旺",30,"北京市","2003-02-03 02:02:02"));
        userList.add(new User("6","赵六",10,"北京市","1995-02-03 02:02:02"));
        userList.add(new User("7","赵qi",10,"北京市","1994-02-03 02:02:02"));

        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order(789,"123","7"));
        orderList.add(new Order(123,"123","1"));
        orderList.add(new Order(234,"123","2"));
        orderList.add(new Order(456,"123","3"));
        orderList.add(new Order(789,"123","4"));
        orderList.add(new Order(123,"123","5"));
        orderList.add(new Order(234,"123","6"));
        orderList.add(new Order(456,"123","7"));


        List<User> userList1 = userList.stream()
                .distinct()
                .sorted((a,b) ->{
                    if(a.getAge().compareTo(b.getAge()) == 0){
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date o1 = null;
                        Date o2 = null;
                        try {
                            o1 =simpleDateFormat.parse(a.getBirthday());
                            o2 =simpleDateFormat.parse(b.getBirthday());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if(o1.before(o2)){
                            return 1;
                        } else if(o2.before(o1)){
                            return -1;
                        } else {
                            return 0;
                        }
                    } else {
                        return a.getAge().compareTo(b.getAge());
                    }
                })
                .limit(3)
                .collect(Collectors.toList());
        userList1.stream().forEach(System.out::println);

        List<Order> orderList1 = new ArrayList<>();

        userList1.stream().forEach(u ->{
            orderList.stream()
                    .forEach(o -> {
                        if(o.getUserId().equals(u.getUserId())){
                            orderList1.add(o);
                        }
                    });
        });

        orderList1.stream().forEach(System.out::println);
    }
}

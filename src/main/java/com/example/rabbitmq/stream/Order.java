package com.example.rabbitmq.stream;

public class Order {
    private Integer orderId;
    private String orderNum;
    private String userId;

    public Order(Integer orderId, String orderNum, String userId) {
        this.orderId = orderId;
        this.orderNum = orderNum;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderNum='" + orderNum + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

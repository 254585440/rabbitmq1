package com.example.rabbitmq.completableFuture;


public class Logger2 {
    private String user;
    private String reqUrl;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getReqUrl() {
        return reqUrl;
    }

    public void setReqUrl(String reqUrl) {
        this.reqUrl = reqUrl;
    }

    @Override
    public String toString() {
        return "Logger2{" +
                "user='" + user + '\'' +
                ", reqUrl='" + reqUrl + '\'' +
                '}';
    }
}

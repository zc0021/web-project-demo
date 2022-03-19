package com.fanfan.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String sayHello(String userName) {
        return "hello " + userName;
    }

}

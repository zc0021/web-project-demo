package com.fanfan.web;

import com.fanfan.interfaces.model.ServerResult;
import com.fanfan.service.HelloService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HelloController {

    @Resource
    private HelloService helloService;

    @PutMapping(value = "sayHello")
    public ServerResult<String> sayHello(@RequestParam String userName) {
        return ServerResult.success(helloService.sayHello(userName));
    }

}

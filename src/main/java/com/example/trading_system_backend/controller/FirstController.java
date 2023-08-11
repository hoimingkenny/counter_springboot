package com.example.trading_system_backend.controller;

import com.example.trading_system_backend.util.DbUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {

    // not need to write this as we can call static method from DatabaseUtil class
//    @Autowired
//    private DatabaseUtil databaseUtil;

    @Autowired
    private StringRedisTemplate template;

//    @RequestMapping("/hello")
//    public String Hello() {
////        return "Hello World";
//        return "" + DbUtil.getName();
//    }

    @RequestMapping("/hello2")
    public String Hello2() {
        template.opsForValue().set("test:Hello", "World");
        return template.opsForValue().get("test:Hello");
    }
}

package com.example.trading_system_backend.controller;

import com.example.trading_system_backend.util.DatabaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {

    // not need to write this as we calling static method from DatabaseUtil
//    @Autowired
//    private DatabaseUtil databaseUtil;

    @RequestMapping("/hello")
    public String Hello() {
//        return "Hello World";
        return "" + DatabaseUtil.getId();
    }
}

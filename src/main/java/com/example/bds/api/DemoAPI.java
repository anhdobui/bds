package com.example.bds.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoAPI {
    @GetMapping("/hello")
    public String hello(){
        return "hello word";
    }
}

package org.example.testspring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    @GetMapping("/hello")
    String sayHello(){
        return "hello world";
    }
}

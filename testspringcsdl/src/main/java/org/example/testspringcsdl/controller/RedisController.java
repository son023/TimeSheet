package org.example.testspringcsdl.controller;

import org.example.testspringcsdl.service.IBaseRedisService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/redis")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RedisController {
    IBaseRedisService baseRedisService;

    @PostMapping
    public void set() {
        baseRedisService.set("hihi", "ah");
    }
}

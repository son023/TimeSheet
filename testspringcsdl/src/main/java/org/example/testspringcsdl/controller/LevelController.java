package org.example.testspringcsdl.controller;

import java.util.List;

import org.example.testspringcsdl.entity.Level;
import org.example.testspringcsdl.service.impl.LevelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/levels")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LevelController {
    LevelService levelService;

    @GetMapping
    List<Level> getAllLevels() {
        return levelService.getLevel();
    }
}

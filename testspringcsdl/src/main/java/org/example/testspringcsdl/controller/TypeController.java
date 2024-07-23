package org.example.testspringcsdl.controller;

import java.util.List;

import org.example.testspringcsdl.entity.Type;
import org.example.testspringcsdl.service.impl.TypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/types")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TypeController {
    TypeService typeService;

    @GetMapping
    List<Type> getAllTypes() {
        return typeService.getType();
    }
}

package org.example.testspringcsdl.controller;

import java.util.List;

import org.example.testspringcsdl.entity.Position;
import org.example.testspringcsdl.service.impl.PositionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/positions")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PositionController {
    PositionService positionService;

    @GetMapping
    List<Position> getAllPositions() {
        return positionService.getPosition();
    }
}

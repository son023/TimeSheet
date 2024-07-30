package org.example.testspringcsdl.service.impl;

import java.util.List;

import org.example.testspringcsdl.entity.Position;
import org.example.testspringcsdl.repository.PositionRepository;
import org.example.testspringcsdl.service.IPositionService;
import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PositionService implements IPositionService {
    PositionRepository positionRepository;

    @Override
    public List<Position> getPosition() {
        return positionRepository.findAll();
    }
}

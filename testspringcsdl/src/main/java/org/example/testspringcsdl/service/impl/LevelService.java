package org.example.testspringcsdl.service.impl;

import java.util.List;

import org.example.testspringcsdl.entity.Level;
import org.example.testspringcsdl.repository.LevelRepository;
import org.example.testspringcsdl.service.ILevelService;
import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LevelService implements ILevelService {
    LevelRepository levelRepository;

    @Override
    public List<Level> getLevel() {
        return levelRepository.findAll();
    }
}

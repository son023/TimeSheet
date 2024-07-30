package org.example.testspringcsdl.service.impl;

import java.util.List;

import org.example.testspringcsdl.entity.Type;
import org.example.testspringcsdl.repository.TypeRepository;
import org.example.testspringcsdl.service.ITypeService;
import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TypeService implements ITypeService {
    TypeRepository typeRepository;

    @Override
    public List<Type> getType() {
        return typeRepository.findAll();
    }
}

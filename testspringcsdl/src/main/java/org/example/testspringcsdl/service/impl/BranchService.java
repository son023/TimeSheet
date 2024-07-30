package org.example.testspringcsdl.service.impl;

import java.util.List;

import org.example.testspringcsdl.entity.Branch;
import org.example.testspringcsdl.repository.BranchRepository;
import org.example.testspringcsdl.service.IBranchService;
import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BranchService implements IBranchService {
    BranchRepository branchRepository;

    @Override
    public List<Branch> getBranch() {
        return branchRepository.findAll();
    }
}

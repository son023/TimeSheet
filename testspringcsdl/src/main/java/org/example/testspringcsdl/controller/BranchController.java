package org.example.testspringcsdl.controller;

import java.util.List;

import org.example.testspringcsdl.entity.Branch;
import org.example.testspringcsdl.service.impl.BranchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/branchs")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BranchController {
    BranchService branchService;

    @GetMapping
    List<Branch> getAllBranchs() {
        return branchService.getBranch();
    }
}

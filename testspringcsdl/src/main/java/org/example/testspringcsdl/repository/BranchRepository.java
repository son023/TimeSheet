package org.example.testspringcsdl.repository;

import org.example.testspringcsdl.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, Integer> {}

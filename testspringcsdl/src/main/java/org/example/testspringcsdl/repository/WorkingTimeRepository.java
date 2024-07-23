package org.example.testspringcsdl.repository;

import org.example.testspringcsdl.entity.WorkingTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkingTimeRepository extends JpaRepository<WorkingTime, Integer> {}

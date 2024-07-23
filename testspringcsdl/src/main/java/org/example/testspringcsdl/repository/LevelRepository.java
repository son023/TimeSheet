package org.example.testspringcsdl.repository;

import org.example.testspringcsdl.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LevelRepository extends JpaRepository<Level, Integer> {}

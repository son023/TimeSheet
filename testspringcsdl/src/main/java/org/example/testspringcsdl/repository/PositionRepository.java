package org.example.testspringcsdl.repository;

import org.example.testspringcsdl.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Integer> {}

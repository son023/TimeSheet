package org.example.testspringcsdl.repository;

import org.example.testspringcsdl.entity.InvalidatedToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, Integer> {
    boolean existsById(String id);
}

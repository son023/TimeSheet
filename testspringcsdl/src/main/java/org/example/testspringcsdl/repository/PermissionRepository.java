package org.example.testspringcsdl.repository;

import org.example.testspringcsdl.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
    boolean existsByPermissionName(String permissionName);
}

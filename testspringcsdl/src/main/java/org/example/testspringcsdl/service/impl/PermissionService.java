package org.example.testspringcsdl.service.impl;

import java.util.List;

import org.example.testspringcsdl.dto.request.PermissionCreationRequest;
import org.example.testspringcsdl.dto.respone.PermissionResponse;
import org.example.testspringcsdl.entity.Permission;
import org.example.testspringcsdl.mapper.PermissionMapper;
import org.example.testspringcsdl.repository.PermissionRepository;
import org.example.testspringcsdl.service.IPermissionService;

public class PermissionService implements IPermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public List<Permission> getPermission() {
        return permissionRepository.findAll();
    }

    public PermissionResponse createPermission(PermissionCreationRequest request) {

        if (permissionRepository.existsByPermissionName(request.getPermissionName())) {
            throw new RuntimeException("218172871");
        }
        Permission permission = permissionMapper.toPermission(request);

        return permissionMapper.permissionToResponse(permissionRepository.save(permission));
    }
}

package org.example.testspringcsdl.service.impl;

import java.util.List;

import org.example.testspringcsdl.dto.request.PermissionCreationRequest;
import org.example.testspringcsdl.dto.respone.PermissionResponse;
import org.example.testspringcsdl.entity.Permission;
import org.example.testspringcsdl.mapper.PermissionMapper;
import org.example.testspringcsdl.repository.PermissionRepository;
import org.example.testspringcsdl.service.IPermissionService;
import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService implements IPermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    @Override
    public List<PermissionResponse> getPermission() {
        return permissionMapper.permissionsToResponse(permissionRepository.findAll());
    }

    @Override
    public PermissionResponse createPermission(PermissionCreationRequest request) {

        if (permissionRepository.existsByPermissionName(request.getPermissionName())) {
            throw new RuntimeException("PERMISSION_EXISTED");
        }
        Permission permission = permissionMapper.toPermission(request);

        return permissionMapper.permissionToResponse(permissionRepository.save(permission));
    }
}

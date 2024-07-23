package org.example.testspringcsdl.mapper;

import java.util.List;

import org.example.testspringcsdl.dto.request.PermissionCreationRequest;
import org.example.testspringcsdl.dto.respone.PermissionResponse;
import org.example.testspringcsdl.entity.Permission;

public interface PermissionMapper {
    Permission toPermission(PermissionCreationRequest request);

    PermissionResponse permissionToResponse(Permission permission);

    List<PermissionResponse> permissionsToResponse(List<Permission> permissions);
}

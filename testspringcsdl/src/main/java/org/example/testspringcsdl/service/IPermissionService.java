package org.example.testspringcsdl.service;

import org.example.testspringcsdl.dto.request.PermissionCreationRequest;
import org.example.testspringcsdl.dto.respone.PermissionResponse;
import org.example.testspringcsdl.entity.Permission;

import java.util.List;

public interface IPermissionService {
    List<PermissionResponse> getPermission();
    PermissionResponse createPermission(PermissionCreationRequest request);
}

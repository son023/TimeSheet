package org.example.testspringcsdl.service;

import java.util.List;

import org.example.testspringcsdl.dto.request.PermissionCreationRequest;
import org.example.testspringcsdl.dto.respone.PermissionResponse;

public interface IPermissionService {
    List<PermissionResponse> getPermission();

    PermissionResponse createPermission(PermissionCreationRequest request);
}

package org.example.testspringcsdl.controller;

import java.util.List;

import org.example.testspringcsdl.dto.ApiResponse;
import org.example.testspringcsdl.dto.request.PermissionCreationRequest;
import org.example.testspringcsdl.dto.respone.PermissionResponse;
import org.example.testspringcsdl.service.impl.PermissionService;
import org.springframework.web.bind.annotation.*;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/permissions")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionController {
    PermissionService permissionService;

    @GetMapping
    List<PermissionResponse> getAllPermission() {
        return permissionService.getPermission();
    }

    @PostMapping
    ApiResponse<PermissionResponse> createPermission(@RequestBody PermissionCreationRequest request) {
        ApiResponse<PermissionResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(permissionService.createPermission(request));
        return apiResponse;
    }
}

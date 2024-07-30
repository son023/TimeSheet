package org.example.testspringcsdl.controller;

import java.util.List;

import org.example.testspringcsdl.dto.ApiResponse;
import org.example.testspringcsdl.dto.request.RoleCreationRequest;
import org.example.testspringcsdl.dto.respone.RoleResponse;
import org.example.testspringcsdl.entity.Role;
import org.example.testspringcsdl.service.impl.RoleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/roles")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    RoleService roleService;

    @PostMapping
    ApiResponse<RoleResponse> createRole(@RequestBody RoleCreationRequest request) {
        ApiResponse<RoleResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(roleService.createRole(request));
        return apiResponse;
    }

    @PreAuthorize("hasAuthority('Admin.Users.View')")
    @GetMapping
    List<RoleResponse> getAllRoles() {
        return roleService.getRole();
    }

    @GetMapping("/{roleId}")
    RoleResponse getRoleById(@PathVariable("roleId") int roleId) {
        return roleService.getRoleResponeById(roleId);
    }

    @DeleteMapping("/{roleId}")
    String deleteRoleById(@PathVariable("roleId") int roleId) {
        roleService.deleteRoleById(roleId);
        return "Role deleted";
    }

    @PutMapping("/{roleId}")
    Role updateRole(@PathVariable("roleId") int roleId, @RequestBody RoleCreationRequest request) {
        return roleService.updateRole(roleId, request);
    }
}

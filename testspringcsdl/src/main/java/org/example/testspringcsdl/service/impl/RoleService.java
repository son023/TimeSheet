package org.example.testspringcsdl.service.impl;

import java.util.HashSet;
import java.util.List;

import org.example.testspringcsdl.dto.request.RoleCreationRequest;
import org.example.testspringcsdl.dto.respone.RoleResponse;
import org.example.testspringcsdl.entity.Role;
import org.example.testspringcsdl.mapper.RoleMapper;
import org.example.testspringcsdl.repository.PermissionRepository;
import org.example.testspringcsdl.repository.RoleRepository;
import org.example.testspringcsdl.service.IRoleService;
import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService implements IRoleService {
    RoleRepository roleRepository;
    RoleMapper roleMapper;
    PermissionRepository permissionRepository;

    @Override
    public RoleResponse createRole(RoleCreationRequest request) {

        var permissions = permissionRepository.findAllById(request.getPermissions());

        Role role = roleMapper.toRole(request);

        role.setPermissions(new HashSet<>(permissions));

        return roleMapper.roleToRespone(roleRepository.save(role));
    }

    @Override
    public List<RoleResponse> getRole() {
        return roleMapper.rolesToRespone(roleRepository.findAll());
    }

    @Override
    public RoleResponse getRoleResponeById(int roleId) {
        return roleMapper.roleToRespone(getRoleById(roleId));
    }

    @Override
    public Role getRoleById(int roleId) {
        return roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
    }

    @Override
    public void deleteRoleById(int roleId) {
        roleRepository.deleteById(roleId);
    }

    @Override
    public Role updateRole(int roleId, RoleCreationRequest request) {
        Role role = getRoleById(roleId);
        roleMapper.updateRole(role, request);
        return roleRepository.save(role);
    }
}

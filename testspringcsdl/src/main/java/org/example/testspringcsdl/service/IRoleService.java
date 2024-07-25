package org.example.testspringcsdl.service;

import org.example.testspringcsdl.dto.request.RoleCreationRequest;
import org.example.testspringcsdl.dto.respone.RoleResponse;
import org.example.testspringcsdl.entity.Role;

import java.util.List;

public interface IRoleService {
    RoleResponse createRole(RoleCreationRequest request);
    List<RoleResponse> getRole() ;

    RoleResponse getRoleResponeById(int roleId) ;

    Role getRoleById(int roleId) ;

    void deleteRoleById(int roleId);

    Role updateRole(int roleId, RoleCreationRequest request);
}

package org.example.testspringcsdl.mapper;

import org.example.testspringcsdl.dto.request.RoleCreationRequest;
import org.example.testspringcsdl.dto.respone.RoleResponse;
import org.example.testspringcsdl.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    void updateRole(@MappingTarget Role role, RoleCreationRequest request);

    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleCreationRequest request);
    //    @Mapping(source = "roleName",target="displayName")

    RoleResponse roleToRespone(Role role);
}

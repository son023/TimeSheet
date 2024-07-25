package org.example.testspringcsdl.mapper;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.example.testspringcsdl.dto.request.RoleCreationRequest;
import org.example.testspringcsdl.dto.respone.PermissionResponse;
import org.example.testspringcsdl.dto.respone.RoleResponse;
import org.example.testspringcsdl.entity.Permission;
import org.example.testspringcsdl.entity.Role;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public void updateRole(Role role, RoleCreationRequest request) {
        if ( request == null ) {
            return;
        }

        role.setRoleName( request.getRoleName() );
        role.setRoleDisplay( request.getRoleDisplay() );
    }

    @Override
    public Role toRole(RoleCreationRequest request) {
        if ( request == null ) {
            return null;
        }

        Role role = new Role();

        role.setRoleName( request.getRoleName() );
        role.setRoleDisplay( request.getRoleDisplay() );

        return role;
    }

    @Override
    public RoleResponse roleToRespone(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleResponse roleResponse = new RoleResponse();

        roleResponse.setId( role.getId() );
        roleResponse.setRoleName( role.getRoleName() );
        roleResponse.setPermissions( permissionSetToPermissionResponseSet( role.getPermissions() ) );

        return roleResponse;
    }

    @Override
    public List<RoleResponse> rolesToRespone(List<Role> role) {
        if ( role == null ) {
            return null;
        }

        List<RoleResponse> list = new ArrayList<RoleResponse>( role.size() );
        for ( Role role1 : role ) {
            list.add( roleToRespone( role1 ) );
        }

        return list;
    }

    protected PermissionResponse permissionToPermissionResponse(Permission permission) {
        if ( permission == null ) {
            return null;
        }

        PermissionResponse permissionResponse = new PermissionResponse();

        permissionResponse.setId( permission.getId() );
        permissionResponse.setPermissionName( permission.getPermissionName() );
        permissionResponse.setMultiTenancySides( permission.getMultiTenancySides() );
        permissionResponse.setPermissionDisplay( permission.getPermissionDisplay() );
        permissionResponse.setIsConfiguration( permission.getIsConfiguration() );

        return permissionResponse;
    }

    protected Set<PermissionResponse> permissionSetToPermissionResponseSet(Set<Permission> set) {
        if ( set == null ) {
            return null;
        }

        Set<PermissionResponse> set1 = new LinkedHashSet<PermissionResponse>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Permission permission : set ) {
            set1.add( permissionToPermissionResponse( permission ) );
        }

        return set1;
    }
}

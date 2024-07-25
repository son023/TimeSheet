package org.example.testspringcsdl.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.example.testspringcsdl.dto.request.PermissionCreationRequest;
import org.example.testspringcsdl.dto.respone.PermissionResponse;
import org.example.testspringcsdl.entity.Permission;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class PermissionMapperImpl implements PermissionMapper {

    @Override
    public Permission toPermission(PermissionCreationRequest request) {
        if ( request == null ) {
            return null;
        }

        Permission permission = new Permission();

        permission.setPermissionName( request.getPermissionName() );
        permission.setMultiTenancySides( request.getMultiTenancySides() );
        permission.setPermissionDisplay( request.getPermissionDisplay() );
        permission.setIsConfiguration( request.getIsConfiguration() );

        return permission;
    }

    @Override
    public PermissionResponse permissionToResponse(Permission permission) {
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

    @Override
    public List<PermissionResponse> permissionsToResponse(List<Permission> permissions) {
        if ( permissions == null ) {
            return null;
        }

        List<PermissionResponse> list = new ArrayList<PermissionResponse>( permissions.size() );
        for ( Permission permission : permissions ) {
            list.add( permissionToResponse( permission ) );
        }

        return list;
    }
}

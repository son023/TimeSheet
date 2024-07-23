package org.example.testspringcsdl.mapper;

import java.util.List;

import org.example.testspringcsdl.dto.request.UserCreationRequest;
import org.example.testspringcsdl.dto.respone.UserResponse;
import org.example.testspringcsdl.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    void updateUser(@MappingTarget User user, UserCreationRequest request);

    User toUser(UserCreationRequest request);
    //    @Mapping(source = "roleName",target="displayName")
    //    @Mapping(source="id",ignore = true)
    UserResponse userToResponse(User user);

    List<UserResponse> usersToResponse(List<User> user);
}

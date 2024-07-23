package org.example.testspringcsdl.service;

import java.util.List;

import org.example.testspringcsdl.dto.request.UserCreationRequest;
import org.example.testspringcsdl.dto.respone.UserResponse;
import org.example.testspringcsdl.entity.User;

public interface IUserService {
    UserResponse createUser(UserCreationRequest request);

    UserResponse getMyInfo();

    User getUserById(int userId);

    UserResponse getUserByUserName(String userName);

    UserResponse getUserResponeById(int userId);

    List<UserResponse> getUser();

    void deleteUserById(int userId);

    void deleteByUserName(String userName);

    UserResponse updateUser(String userName, UserCreationRequest request);
}

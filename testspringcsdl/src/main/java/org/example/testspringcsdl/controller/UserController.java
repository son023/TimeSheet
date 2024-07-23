package org.example.testspringcsdl.controller;

import java.security.Principal;
import java.util.List;

import org.example.testspringcsdl.dto.ApiResponse;
import org.example.testspringcsdl.dto.request.UserCreationRequest;
import org.example.testspringcsdl.dto.request.UserSearchRequest;
import org.example.testspringcsdl.dto.respone.UserResponse;
import org.example.testspringcsdl.entity.User;
import org.example.testspringcsdl.service.impl.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    UserService userService;

    @PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody UserCreationRequest request) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(request));
        return apiResponse;
    }

    @GetMapping("/myinfo")
    UserResponse getMyInfo() {
        return userService.getMyInfo();
    }

    @GetMapping("/{userId}")
    ApiResponse<UserResponse> getUserById(@PathVariable("userId") int userId) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.getUserResponeById(userId));
        return apiResponse;
    }

    @GetMapping("/getByUserName/{userName}")
    ApiResponse<UserResponse> getUserById(@PathVariable("userName") String userName) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.getUserByUserName(userName));
        return apiResponse;
    }

    @GetMapping("/getByUserName/{userName}")
    //    @PreAuthorize("hasAuthority('Admin.Users.View')")
    List<UserResponse> getAllUser() {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        return userService.getUser();
    }
    @GetMapping("/searchUser")
    public List<User> searchUsers(
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String branchName,
            @RequestParam(required = false) String positionName,
            @RequestParam(required = false) String roleName,
            @RequestParam(required = false) String levelName) {

        return userService.searchUsers(new UserSearchRequest(userName, branchName, positionName, roleName, levelName));

    }



    @DeleteMapping("/{userId}")
    String deleteUserById(@PathVariable("userId") int userId) {
        userService.deleteUserById(userId);
        return "User deleted";
    }

    @DeleteMapping("/deleteByUserName/{userName}")
    String deleteUserByUserName(@PathVariable("userName") String userName) {
        userService.deleteByUserName(userName);
        return "User deleted";
    }

    @PutMapping("/{userName}")
    ApiResponse<UserResponse> updateUser(
            @PathVariable("userName") String userName, @RequestBody UserCreationRequest request) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.updateUser(userName, request));
        return apiResponse;
    }

}

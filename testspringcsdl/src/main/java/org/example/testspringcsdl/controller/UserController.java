package org.example.testspringcsdl.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.example.testspringcsdl.dto.ApiResponse;
import org.example.testspringcsdl.dto.request.UserCreationRequest;
import org.example.testspringcsdl.dto.request.UserSearchRequest;
import org.example.testspringcsdl.dto.respone.UserResponse;
import org.example.testspringcsdl.entity.User;
import org.example.testspringcsdl.service.impl.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@RestController
@EnableMethodSecurity
@RequestMapping("/api/v1/users")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    UserService userService;

    @PostMapping
    ApiResponse<UserResponse> createUser(@Valid @RequestBody UserCreationRequest request) {
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

        @GetMapping()
//        @PreAuthorize("hasAuthority('Admin.Users.View')")
        List<UserResponse> getAllUser() {
//            Principal principal = SecurityContextHolder.getContext().getAuthentication();
            return userService.getUser();
        }


    @GetMapping("/searchUser")
    public List<User> searchUsers(
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) Long branchId,
            @RequestParam(required = false) Long positionId,
            @RequestParam(required = false) Long roleId,
            @RequestParam(required = false) Long typeId) {

        return userService.searchUsers(new UserSearchRequest(userName, branchId, positionId, roleId, typeId));
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
    @PreAuthorize("hasAuthority('Admin.Users.View')")
    @GetMapping("/page")
    public Page<UserResponse> pageUser(@RequestParam(required = false) Integer pageNo) {
        return userService.getPageUser(pageNo);
    }
}

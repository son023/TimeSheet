package org.example.testspringcsdl.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.example.testspringcsdl.dto.request.UserCreationRequest;
import org.example.testspringcsdl.dto.request.UserSearchRequest;
import org.example.testspringcsdl.dto.respone.UserResponse;
import org.example.testspringcsdl.entity.User;
import org.example.testspringcsdl.exception.AppException;
import org.example.testspringcsdl.exception.ErrorCode;
import org.example.testspringcsdl.mapper.UserMapper;
import org.example.testspringcsdl.repository.*;
import org.example.testspringcsdl.service.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService implements IUserService {
    UserMapper userMapper;
    UserRepository userRepository;

    BranchRepository branchRepository;
    WorkingTimeRepository workingTimeRepository;
    RoleRepository roleRepository;
    TypeRepository typeRepository;
    LevelRepository levelRepository;
    PositionRepository positionRepository;

    @Override
    public UserResponse createUser(UserCreationRequest request) {
        if (userRepository.existsByUserName(request.getUserName())) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //        Long salary=Long.parseLong(String.valueOf(request.getSalary()));
        var user = User.builder()
                .userName(request.getUserName())
                .passWord(passwordEncoder.encode(request.getPassWord()))
                .fullName(request.getFullName())
                .email(request.getEmail())
                .position(positionRepository.getById(request.getPositionId()))
                .branch(branchRepository.getById(request.getBranchId()))
                .workingTime(workingTimeRepository.getById(request.getWorkingTimeId()))
                .role(roleRepository.getById(request.getRoleId()))
                .type(typeRepository.getById(request.getTypeId()))
                .level(levelRepository.getById(request.getLevelId()))
                .startDate(request.getStartDate())
                .allowedDay(request.getAllowedDay())
                .salary(request.getSalary())
                .salaryAt(LocalDate.parse(request.getSalaryAt(), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .isActive(request.getIsActive())
                .sex(request.getSex())
                .build();
        return userMapper.userToResponse(userRepository.save(user));
    }

    @Override
    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        User byUserName = userRepository.findByUsername(name);
        return userMapper.userToResponse(byUserName);
    }

    @Override
    public User getUserById(int userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Role not found"));
    }

    public UserResponse getUserByUserName(String userName) {
        return userMapper.userToResponse(userRepository.findByUsername(userName));
    }

    public UserResponse getUserResponeById(int userId) {
        return userMapper.userToResponse(getUserById(userId));
    }

    //    @PreAuthorize("hasAuthority('Admin.Users.View')")
    @Override
    public List<UserResponse> getUser() {
        System.out.println("123");
        log.info("ok");
        return userMapper.usersToResponse(userRepository.findAll());
    }

    @Override
    public void deleteUserById(int userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void deleteByUserName(String userName) {
        userRepository.deleteByUsername(userName);
    }

    @Override
    public UserResponse updateUser(String userName, UserCreationRequest request) {
        User user = userRepository.findByUsername(userName);

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setStartDate(request.getStartDate());
        user.setAllowedDay(request.getAllowedDay());
        user.setSalary(request.getSalary());
        user.setSalaryAt(LocalDate.parse(request.getSalaryAt(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        user.setIsActive(request.getIsActive());
        user.setSex(request.getSex());
        if (user.getPosition().getId() != request.getPositionId()) {
            user.setPosition(positionRepository.getById(request.getPositionId()));
        }

        if (user.getBranch().getId() != request.getBranchId()) {
            user.setBranch(branchRepository.getById(request.getBranchId()));
        }
        if (user.getWorkingTime().getId() != request.getWorkingTimeId()) {
            user.setWorkingTime(workingTimeRepository.getById(request.getWorkingTimeId()));
        }
        if (user.getRole().getId() != request.getRoleId()) {
            user.setRole(roleRepository.getById(request.getRoleId()));
        }
        if (user.getType().getId() != request.getTypeId()) {
            user.setType(typeRepository.getById(request.getTypeId()));
        }
        if (user.getLevel().getId() != request.getLevelId()) {
            user.setLevel(levelRepository.getById(request.getLevelId()));
        }
        return userMapper.userToResponse(userRepository.save(user));
    }

    public List<User> searchUsers(UserSearchRequest userSearchRequest) {

        String userName = (userSearchRequest.getUserName() == null
                        || userSearchRequest.getUserName().isBlank())
                ? null
                : userSearchRequest.getUserName();

        return userRepository.searchUsers(
                userName,
                userSearchRequest.getBranchId() == null ? null : userSearchRequest.getBranchId(),
                userSearchRequest.getPositionId() == null ? null : userSearchRequest.getPositionId(),
                userSearchRequest.getRoleId() == null ? null : userSearchRequest.getRoleId(),
                userSearchRequest.getTypeId() == null ? null : userSearchRequest.getTypeId());
    }

    public Page<UserResponse> getPageUser(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 10); // Trang 0-based
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.map(userMapper::userToResponse);
    }
}

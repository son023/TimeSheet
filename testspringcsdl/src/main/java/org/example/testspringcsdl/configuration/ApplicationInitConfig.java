package org.example.testspringcsdl.configuration;

import java.time.LocalDate;

import org.example.testspringcsdl.entity.User;
import org.example.testspringcsdl.repository.*;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationInitConfig {
    PasswordEncoder passwordEncoder;
    BranchRepository branchRepository;
    WorkingTimeRepository workingTimeRepository;
    RoleRepository roleRepository;
    TypeRepository typeRepository;
    LevelRepository levelRepository;
    PositionRepository positionRepository;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {

        return args -> {
            if (userRepository.findByUserName("admin").isEmpty()) {
                var user = User.builder()
                        .userName("admin")
                        .passWord(passwordEncoder.encode("1"))
                        .fullName("Ad Văn Min")
                        .email("so321@gmgail.com")
                        .position(positionRepository.getById(1))
                        .branch(branchRepository.getById(1))
                        .workingTime(workingTimeRepository.getById(1))
                        .role(roleRepository.getById(4))
                        .type(typeRepository.getById(1))
                        .level(levelRepository.getById(1))
                        .startDate(LocalDate.now())
                        .allowedDay(1)
                        .salary((long) 1000)
                        .salaryAt(LocalDate.now())
                        .isActive(1)
                        .sex("Nam")
                        .build();
                userRepository.save(user);
                log.warn("Thanh cong");
            }
        };
    }
}

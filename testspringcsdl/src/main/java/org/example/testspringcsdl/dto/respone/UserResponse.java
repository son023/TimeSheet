package org.example.testspringcsdl.dto.respone;

import java.time.LocalDate;

import org.example.testspringcsdl.entity.*;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String userName;
    String fullName;
    String email;
    Position position;
    Branch branch;
    WorkingTime workingTime;
    Role role;
    Type type;
    Level level;
    LocalDate startDate;
    int allowedDay;
    Long salary;
    LocalDate salaryAt;
    int isActive;
    String sex;
}

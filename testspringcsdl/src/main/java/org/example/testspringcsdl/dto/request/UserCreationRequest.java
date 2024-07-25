package org.example.testspringcsdl.dto.request;

import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.testspringcsdl.exception.ErrorCode;
import org.example.testspringcsdl.validator.DobConstraint;

import java.time.LocalDate;

import org.example.testspringcsdl.exception.ErrorCode;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    ErrorCode errorCode;
    @NotBlank(message = "userName cannot be blank")
            @Size(min=4,message = "USERNAME_INVALID")
    String userName;

    @NotBlank(message = "Password cannot be blank")
    String passWord;

    @NotBlank(message = "fullName cannot be blank")
    String fullName;

    @NotBlank(message = "email cannot be blank")
    String email;


    int positionId;


    int branchId;


    int workingTimeId;

    int roleId;


    int typeId;


    int levelId;


    @DobConstraint(min = 2, message = "INVALID_DOB")
    LocalDate startDate;

    int allowedDay;

    Long salary;

    @NotBlank(message = "salaryAt cannot be blank")
    String salaryAt;


    int isActive;

    String sex;
}

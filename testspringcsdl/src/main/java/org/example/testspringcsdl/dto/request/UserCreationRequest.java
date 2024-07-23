package org.example.testspringcsdl.dto.request;

import jakarta.validation.constraints.NotBlank;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @NotBlank(message = "userName cannot be blank")
    String userName;

    @NotBlank(message = "Password cannot be blank")
    String passWord;

    @NotBlank(message = "fullName cannot be blank")
    String fullName;

    @NotBlank(message = "email cannot be blank")
    String email;

    @NotBlank(message = "position cannot be blank")
    int positionId;

    @NotBlank(message = "branch_id cannot be blank")
    int branchId;

    @NotBlank(message = "workingTime_id cannot be blank")
    int workingTimeId;

    @NotBlank(message = "role_id cannot be blank")
    int roleId;

    @NotBlank(message = "type_id cannot be blank")
    int typeId;

    @NotBlank(message = "level_id cannot be blank")
    int levelId;

    @NotBlank(message = "startDate cannot be blank")
    String startDate;

    @NotBlank(message = "allowedDay cannot be blank")
    int allowedDay;

    @NotBlank(message = "salary cannot be blank")
    Long salary;

    @NotBlank(message = "salaryAt cannot be blank")
    String salaryAt;

    @NotBlank(message = "isActive cannot be blank")
    int isActive;

    String sex;
}

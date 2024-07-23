package org.example.testspringcsdl.dto.request;

import java.util.Set;

import jakarta.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleCreationRequest {

    @Size(min = 3, message = "ROLENAME_VALID")
    private String roleName;

    @Size(min = 3, message = "DISPLAY_NAME_VALID")
    private String roleDisplay;

    Set<Integer> permissions;
}

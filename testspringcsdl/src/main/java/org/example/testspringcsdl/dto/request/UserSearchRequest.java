package org.example.testspringcsdl.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserSearchRequest {
    String userName;
    Long branchId;
    Long positionId;
    Long roleId;
    Long typeId;
}

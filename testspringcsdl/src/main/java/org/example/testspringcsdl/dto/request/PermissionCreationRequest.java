package org.example.testspringcsdl.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PermissionCreationRequest {

    String permissionName;

    int multiTenancySides;

    String permissionDisplay;

    String isConfiguration;
}

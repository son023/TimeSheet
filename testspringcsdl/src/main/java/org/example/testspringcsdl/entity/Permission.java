package org.example.testspringcsdl.entity;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "permissions")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    int id;

    @Column(name = "permission_name", nullable = false)
    String permissionName;

    @Column(name = "multi_tenancy_sides", nullable = false)
    int multiTenancySides;

    @Column(name = "permission_display", nullable = false)
    String permissionDisplay;

    @Column(name = "is_configuration", nullable = false)
    String isConfiguration;
}

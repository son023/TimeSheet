package org.example.testspringcsdl.entity;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "branchs")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branch_id")
    int id;

    @Column(name = "branch_name", nullable = false)
    String branchName;

    @Column(name = "branch_display", nullable = false)
    String branchDisplay;

    @Column(name = "code", nullable = false)
    String code;

    @Column(name = "color", nullable = false)
    String color;

    @ManyToOne
    @JoinColumn(name = "working_time_id")
    WorkingTime workingTime;
}

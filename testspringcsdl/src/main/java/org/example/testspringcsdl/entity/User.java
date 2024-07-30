package org.example.testspringcsdl.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "users")
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    int id;

    @Column(name = "user_name", nullable = false)
    String userName;

    @Column(name = "pass_word", nullable = false)
    String passWord;

    @Column(name = "full_name", nullable = false)
    String fullName;

    @Column(name = "email", nullable = false)
    String email;

    @ManyToOne
    @JoinColumn(name = "position_id")
    Position position;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    Branch branch;

    @ManyToOne
    @JoinColumn(name = "working_time_id")
    WorkingTime workingTime;

    @ManyToOne
    @JoinColumn(name = "role_id")
    Role role;

    @ManyToOne
    @JoinColumn(name = "type_id")
    Type type;

    @ManyToOne
    @JoinColumn(name = "level_id")
    Level level;

    @Column(name = "start_date", nullable = false)
    LocalDate startDate;

    @Column(name = "allowed_day", nullable = false)
    int allowedDay;

    @Column(name = "salary", nullable = false)
    Long salary;

    @Column(name = "salary_at", nullable = false)
    LocalDate salaryAt;

    @Column(name = "is_active", nullable = false)
    int isActive;

    @Column(name = "sex")
    String sex;
}

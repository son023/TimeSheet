package org.example.testspringcsdl.entity;

import java.time.LocalTime;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "working_times")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class WorkingTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "working_time_id")
    int id;

    @Column(name = "working_time_name", nullable = false)
    String workingTimeName;

    @Column(name = "morning_start_time", nullable = false)
    LocalTime moringStartTime;

    @Column(name = "morning_end_time", nullable = false)
    LocalTime moringEndTime;

    @Column(name = "afternoon_start_time", nullable = false)
    LocalTime afternoonStartTime;

    @Column(name = "afternoon_end_time", nullable = false)
    LocalTime afternoonEndTime;
}

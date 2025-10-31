package com.yashdkadam.board.entity;

import com.yashdkadam.board.entity.Project;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Tasks")
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.TO_DO;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority = Priority.MEDIUM;

    private LocalDate dueDate;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "assigned_to")
    private Long assignedTo;

    @Column(name = "project_id")
    private Long projectId;


    public enum Status {
        TO_DO,
        IN_PROGRESS,
        REVIEW,
        DONE
    }

    public enum Priority {
        LOW,
        MEDIUM,
        HIGH,
        CRITICAL
    }
}
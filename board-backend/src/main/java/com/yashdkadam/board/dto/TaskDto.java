package com.yashdkadam.board.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskDto {

    private Long taskId;
    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private LocalDate dueDate;
    private Long createdByUserId;
    private Long assignedToUserId;
    private Long projectId;

    public enum Status {
        TO_DO,
        IN_PROGRESS,
        REVIEW,
        DONE;

        public String getValue() {
            return this.name();
        }

        public static Status fromValue(String value) {
            return Status.valueOf(value.toUpperCase());
        }
    }

    public enum Priority {
        LOW,
        MEDIUM,
        HIGH,
        CRITICAL;

        public String getValue() {
            return this.name();
        }

        public static Priority fromValue(String value) {
            return Priority.valueOf(value.toUpperCase());
        }
    }
}
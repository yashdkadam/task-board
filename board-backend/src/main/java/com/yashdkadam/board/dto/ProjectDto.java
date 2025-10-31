package com.yashdkadam.board.dto;

import lombok.*;

@Data
public class ProjectDto {

    private Long projectId;
    private String name;
    private String description;
    private Long createdByUserId;
}
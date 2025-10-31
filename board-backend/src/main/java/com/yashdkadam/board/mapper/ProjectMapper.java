package com.yashdkadam.board.mapper;

import com.yashdkadam.board.dto.ProjectDto;
import com.yashdkadam.board.entity.Project;

public class ProjectMapper {

    public static ProjectDto mapToProjectDto(Project project, ProjectDto projectDto) {
        if (project == null) return null;

        projectDto.setProjectId(project.getProjectId());
        projectDto.setName(project.getName());
        projectDto.setDescription(project.getDescription());
        projectDto.setCreatedByUserId(project.getCreatedBy());

        return projectDto;
    }

    public static Project mapToProject(Project project, ProjectDto projectDto) {
        if (projectDto == null) return null;

        project.setProjectId(projectDto.getProjectId());
        project.setName(projectDto.getName());
        project.setDescription(projectDto.getDescription());
        project.setCreatedBy(projectDto.getCreatedByUserId());

        return project;
    }
}
package com.yashdkadam.board.service;

import com.yashdkadam.board.dto.ProjectDto;
import com.yashdkadam.board.dto.TaskDto;

import java.util.List;

public interface IProjectService {
    void createProject(ProjectDto projectDto);

    List<ProjectDto> fetchProjects();

    ProjectDto fetchProject(Long id);

    boolean updateProject(ProjectDto projectDto);

    boolean deleteProject(Long id);
}

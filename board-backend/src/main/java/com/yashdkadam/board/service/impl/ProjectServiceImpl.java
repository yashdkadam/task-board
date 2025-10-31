package com.yashdkadam.board.service.impl;

import com.yashdkadam.board.dto.ProjectDto;
import com.yashdkadam.board.dto.TaskDto;
import com.yashdkadam.board.entity.Project;
import com.yashdkadam.board.entity.Task;
import com.yashdkadam.board.exception.ResourceNotFoundException;
import com.yashdkadam.board.mapper.ProjectMapper;
import com.yashdkadam.board.mapper.TaskMapper;
import com.yashdkadam.board.repository.ProjectsRepository;
import com.yashdkadam.board.service.IProjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements IProjectService {
    private ProjectsRepository projectsRepository;

    @Override
    public void createProject(ProjectDto projectDto) {
        Project project = ProjectMapper.mapToProject(new Project(), projectDto);
        project.setProjectId(null);
        projectsRepository.save(project);
    }

    @Override
    public List<ProjectDto> fetchProjects() {
        List<Project> Projects = projectsRepository.findAll();
        List<ProjectDto> projects = new ArrayList<>();
        for(Project p: Projects){
            ProjectDto projectDto = new ProjectDto();
            projectDto = ProjectMapper.mapToProjectDto(p, projectDto);
            projects.add(projectDto);
        }
        return projects;
    }

    @Override
    public ProjectDto fetchProject(Long id) {
        Optional<Project> project = projectsRepository.findById(id);
        ProjectDto projectDto = ProjectMapper.mapToProjectDto(project.orElse(null), new ProjectDto());
        return projectDto;
    }

    @Override
    public boolean updateProject(ProjectDto projectDto) {
        boolean isUpdated = false;
        if(projectDto != null){
            Project project = projectsRepository.findById(projectDto.getProjectId()).orElseThrow(
                    () -> new ResourceNotFoundException("Project", "ProjectId", projectDto.getProjectId().toString())
            );
            project = ProjectMapper.mapToProject(project, projectDto);
        }

        return isUpdated;
    }

    @Override
    public boolean deleteProject(Long id) {
        Project project = projectsRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Project", "ProjectId", id.toString())
        );
        projectsRepository.deleteById(id);
        return true;
    }
}

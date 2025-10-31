package com.yashdkadam.board.service;

import com.yashdkadam.board.dto.TaskDto;

import java.util.List;

public interface ITaskService {
    void createTask(TaskDto taskDto);

    List<TaskDto> fetchTasks();

    TaskDto fetchTask(Long id);

    boolean updateTask(TaskDto taskDto);

    boolean deleteTask(Long id);
}

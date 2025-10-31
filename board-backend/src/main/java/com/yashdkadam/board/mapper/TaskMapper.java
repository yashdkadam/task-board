package com.yashdkadam.board.mapper;

import com.yashdkadam.board.dto.TaskDto;
import com.yashdkadam.board.entity.Task;

public class TaskMapper {

    public static TaskDto mapToTaskDto(Task task, TaskDto taskDto) {
        if (task == null) return null;

        taskDto.setTaskId(task.getTaskId());
        taskDto.setTitle(task.getTitle());
        taskDto.setDescription(task.getDescription());
        taskDto.setStatus(TaskDto.Status.valueOf(task.getStatus().name()));
        taskDto.setPriority(TaskDto.Priority.valueOf(task.getPriority().name()));
        taskDto.setDueDate(task.getDueDate());
        taskDto.setCreatedByUserId(task.getCreatedBy());
        taskDto.setAssignedToUserId(task.getAssignedTo());
        taskDto.setProjectId(task.getProjectId());

        return taskDto;
    }

    public static Task mapToTask(Task task, TaskDto taskDto) {

        if (taskDto == null) return null;

        task.setTaskId(taskDto.getTaskId());
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(Task.Status.valueOf(taskDto.getStatus().name()));
        task.setPriority(Task.Priority.valueOf(taskDto.getPriority().name()));
        task.setDueDate(taskDto.getDueDate());
        task.setCreatedBy(taskDto.getCreatedByUserId());
        task.setAssignedTo(taskDto.getAssignedToUserId());
        task.setProjectId(taskDto.getProjectId());

        return task;
    }
}
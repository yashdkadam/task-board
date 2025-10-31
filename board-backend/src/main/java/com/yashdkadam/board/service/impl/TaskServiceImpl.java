package com.yashdkadam.board.service.impl;

import com.yashdkadam.board.dto.TaskDto;
import com.yashdkadam.board.entity.Task;
import com.yashdkadam.board.exception.ResourceNotFoundException;
import com.yashdkadam.board.mapper.TaskMapper;
import com.yashdkadam.board.repository.TasksRepository;
import com.yashdkadam.board.service.ITaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.yashdkadam.board.mapper.TaskMapper.mapToTaskDto;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements ITaskService {
    private TasksRepository tasksRepository;


    @Override
    public void createTask(TaskDto taskDto) {
        Task task = TaskMapper.mapToTask(new Task(), taskDto);

        task.setTaskId(null);
        tasksRepository.save(task);
    }

    @Override
    public List<TaskDto> fetchTasks() {
        List<Task> Tasks = tasksRepository.findAll();
        List<TaskDto> tasks = new ArrayList<>();
        for(Task t : Tasks){
            TaskDto taskDto = new TaskDto();
            taskDto = mapToTaskDto(t, taskDto);
            tasks.add(taskDto);
        }
        return tasks;
    }

    @Override
    public TaskDto fetchTask(Long id) {
        Optional<Task> task = tasksRepository.findById(id);
        TaskDto taskDto = mapToTaskDto(task.orElse(null), new TaskDto());
        return taskDto;
    }

    @Override
    public boolean updateTask(TaskDto taskDto) {
        boolean isUpdated = false;
        if(taskDto != null){
            Task task = tasksRepository.findById(taskDto.getTaskId()).orElseThrow(
                    () -> new ResourceNotFoundException("Task", "TaskId", taskDto.getTaskId().toString())
            );
            task = TaskMapper.mapToTask(task, taskDto);
            tasksRepository.save(task);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteTask(Long id) {
        Task task = tasksRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Task", "id", id.toString())
        );
        tasksRepository.deleteById(id);
        return true;
    }
}

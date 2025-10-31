package com.yashdkadam.board.controller;

import com.yashdkadam.board.constants.TaskConstants;
import com.yashdkadam.board.constants.UserConstants;
import com.yashdkadam.board.dto.ProjectDto;
import com.yashdkadam.board.dto.ResponseDto;
import com.yashdkadam.board.dto.TaskDto;
import com.yashdkadam.board.dto.UserDto;
import com.yashdkadam.board.repository.UsersRepository;
import com.yashdkadam.board.service.IProjectService;
import com.yashdkadam.board.service.ITaskService;
import com.yashdkadam.board.service.IUsersService;
import com.yashdkadam.board.service.impl.UsersServiceImpl;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api", produces={MediaType.APPLICATION_JSON_VALUE})
public class BoardController {

    private final IUsersService iUsersService;
    private final ITaskService iTaskService;
    private final IProjectService iProjectService;

    public BoardController(IUsersService iUsersService, ITaskService iTaskService, IProjectService iProjectService) {
        this.iUsersService = iUsersService;
        this.iTaskService = iTaskService;
        this.iProjectService = iProjectService;
    }

    // For User

    @PostMapping("/user/create")
    public ResponseEntity<ResponseDto> createUser(@RequestBody UserDto userDto){
        iUsersService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(UserConstants.STATUS_201, UserConstants.MESSAGE_201));
    }

    @GetMapping("/user/fetchAll")
    public ResponseEntity<ResponseDto> fetchUsers(){
        List<UserDto> userDto =  iUsersService.fetchUsers();
        ResponseDto response = new ResponseDto("Users fetched successfully", userDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/user/fetch")
    public ResponseEntity<ResponseDto> fetchUser(@RequestParam String email){
        UserDto userDto =  iUsersService.fetchUser(email);
        ResponseDto response = new ResponseDto("User fetched successfully", userDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(response);
    }

    @PatchMapping("/user/update")
    public ResponseEntity<ResponseDto> updateUser(@RequestBody UserDto userDto){
        iUsersService.updateUser(userDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new ResponseDto(UserConstants.STATUS_200, UserConstants.MESSAGE_200));
    }

    @DeleteMapping("/user/delete")
    public ResponseEntity<ResponseDto> deleteUser(@RequestParam String email){
        iUsersService.deleteUser(email);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new ResponseDto(UserConstants.STATUS_200, UserConstants.MESSAGE_200));
    }

    // For Task

    @PostMapping("/task/create")
    public ResponseEntity<ResponseDto> createTask(@RequestBody TaskDto taskDto) {
        iTaskService.createTask(taskDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(TaskConstants.STATUS_201, TaskConstants.MESSAGE_201));
    }

    @GetMapping("/task/fetchAll")
    public ResponseEntity<ResponseDto> fetchTasks(){
        List<TaskDto> taskDto = iTaskService.fetchTasks();
        ResponseDto response = new ResponseDto("Tasks fetched successfully", taskDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(response);
    }

    @GetMapping("/task/fetch")
    public ResponseEntity<ResponseDto> updateTask(@RequestParam Long id){
        TaskDto taskDto = iTaskService.fetchTask(id);
        ResponseDto response = new ResponseDto("Task fetched successfully", taskDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(response);
    }

    @PatchMapping("/task/update")
    public ResponseEntity<ResponseDto> updateTask(@RequestBody TaskDto taskDto){
        iTaskService.updateTask(taskDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new ResponseDto(UserConstants.STATUS_200, UserConstants.MESSAGE_200));
    }

    @DeleteMapping("/task/delete")
    public ResponseEntity<ResponseDto> deleteTask(Long id){
        iTaskService.deleteTask(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new ResponseDto(UserConstants.STATUS_200, UserConstants.MESSAGE_200));
    }

    // For Projects
    @PostMapping("/project/create")
    public ResponseEntity<ResponseDto> createProject(@RequestBody ProjectDto projectDto){
        iProjectService.createProject(projectDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(UserConstants.STATUS_201, UserConstants.MESSAGE_201));
    }

    @GetMapping("/project/fetchAll")
    public ResponseEntity<ResponseDto> fetchProjects(){
        List<ProjectDto> projectDto = iProjectService.fetchProjects();
        ResponseDto response = new ResponseDto("Projects fetched successfully", projectDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/project/fetch")
    public ResponseEntity<ResponseDto> fetchProject(@RequestParam Long id){
        ProjectDto projectDto = iProjectService.fetchProject(id);
        ResponseDto response = new ResponseDto("Project fetched successfully", projectDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(response);
    }

    @PatchMapping("/project/update")
    public ResponseEntity<ResponseDto> updateUser(@RequestBody ProjectDto projectDto){
        iProjectService.updateProject(projectDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new ResponseDto(UserConstants.STATUS_200, UserConstants.MESSAGE_200));
    }

    @DeleteMapping("/project/delete")
    public ResponseEntity<ResponseDto> deleteUser(@RequestParam Long id){
        iProjectService.deleteProject(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new ResponseDto(UserConstants.STATUS_200, UserConstants.MESSAGE_200));
    }
}

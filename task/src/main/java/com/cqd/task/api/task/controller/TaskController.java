package com.cqd.task.api.task.controller;

import com.cqd.task.api.task.dto.in.PatternMatchRequestDto;
import com.cqd.task.api.task.dto.out.TaskResponseCreationDto;
import com.cqd.task.api.task.dto.out.TaskResponseDto;
import com.cqd.task.api.task.service.TaskService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> getTaskById(@PathVariable Long id) {
        TaskResponseDto taskRequestDto = taskService.getTaskById(id);
        return ResponseEntity.ok(taskRequestDto);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDto>> getAllTasks() {
        List<TaskResponseDto> taskDTOs = taskService.getAllTasks();
        return ResponseEntity.ok(taskDTOs);
    }

    @PostMapping("/perform")
    public ResponseEntity<TaskResponseCreationDto> performTask(@RequestBody PatternMatchRequestDto request) throws InterruptedException {
        TaskResponseCreationDto responseDto = taskService.performPatternMatching(request.getPattern(), request.getInput());
        return ResponseEntity.ok(responseDto);
    }
}
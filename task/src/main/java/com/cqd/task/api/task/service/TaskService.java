package com.cqd.task.api.task.service;

import com.cqd.task.api.exception.ResourceNotFoundException;
import com.cqd.task.api.task.dto.TaskStatus;
import com.cqd.task.api.task.dto.out.TaskResponseCreationDto;
import com.cqd.task.api.task.dto.out.TaskResponseDto;
import com.cqd.task.api.task.handler.AsyncOperationCompletionHandler;
import com.cqd.task.api.task.mapper.TaskMapper;
import com.cqd.task.api.task.model.Task;
import com.cqd.task.api.task.repository.TaskRepository;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final PatternMatchingService patternMatchingService;
    private final AsyncOperationCompletionHandler completionHandler;

    public TaskResponseDto getTaskById(final Long id) {
        Task task = getTask(id);
        return taskMapper.taskToTaskResponseDto(task);
    }

    public List<TaskResponseDto> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
            .map(taskMapper::taskToTaskResponseDto)
            .collect(Collectors.toList());
    }

    public TaskResponseCreationDto performPatternMatching(final String pattern, final String input) throws InterruptedException {

        Task task = Task.builder()
            .status(TaskStatus.PENDING)
            .progress(0)
            .pattern(pattern)
            .input(input)
            .build();

        taskRepository.save(task);
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                patternMatchingService.findPatternMatch(input, pattern, task);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        completionHandler.handleCompletion(future);
        return taskMapper.taskToTaskResponseCreationDto(task);
    }

    private Task getTask(final Long taskId) {
        return taskRepository.findById(taskId)
            .orElseThrow(() -> new ResourceNotFoundException(String.format("Task (id: %s) not found", taskId)));
    }
}

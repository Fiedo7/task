package com.cqd.task.api.task.mapper;

import com.cqd.task.api.task.dto.out.TaskResponseCreationDto;
import com.cqd.task.api.task.dto.out.TaskResponseDto;
import com.cqd.task.api.task.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskResponseDto taskToTaskResponseDto(Task savedTask);

    @Mapping(target = "status", constant = "COMPLETED")
    @Mapping(target = "progress", constant = "100")
    @Mapping(target = "resultPosition", source = "resultPosition")
    @Mapping(target = "resultTypos", source = "resultTypos")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pattern", ignore = true)
    @Mapping(target = "input", ignore = true)
    void updateCompleteTask(@MappingTarget Task task, Integer resultPosition, Integer resultTypos);

    TaskResponseCreationDto taskToTaskResponseCreationDto(Task savedTask);

}

package com.cqd.task.api.task.dto.out;

import lombok.Data;

@Data
public class TaskResponseDto {

    private Long id;
    private String pattern;
    private String input;
    private String status;
    private int progress;
    private int resultPosition;
    private int resultTypos;
}

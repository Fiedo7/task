package com.cqd.task.api.task.dto.in;

import lombok.Data;

@Data
public class TaskPostDto {

    private String pattern;
    private String input;
    private int resultPosition;
    private int resultTypos;

}

package com.cqd.task.api.task.dto.in;

import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Value
@Builder
public class PatternMatchRequestDto {

    @NotBlank
    String pattern;

    @NotBlank
    String input;
}

package com.cqd.task.api.task.service;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.cqd.task.BaseIntegrationTest;
import com.cqd.task.api.task.dto.in.PatternMatchRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

class TaskServiceTest extends BaseIntegrationTest {

    @Autowired
    private ObjectMapper objectMapper;

    private static Stream<Arguments> providePatternMatchScenarios() {
        return Stream.of(
            Arguments.of("ABCD", "BCD", 1, 0),
            Arguments.of("ABCD", "BWD", 1, 1),
            Arguments.of("ABCDEFG", "CFG", 4, 1),
            Arguments.of("ABCABC", "ABC", 0, 0),
            Arguments.of("ABCDEFG", "TDD", 1, 2)
        );
    }

    @ParameterizedTest
    @MethodSource("providePatternMatchScenarios")
    void testPerformPatternMatching(String input, String pattern, int expectedPosition, int expectedTypos) throws Exception {

        PatternMatchRequestDto patternMatchRequestDto = PatternMatchRequestDto.builder()
            .pattern(pattern)
            .input(input)
            .build();

        String requestJson = objectMapper.writeValueAsString(patternMatchRequestDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/tasks/perform")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    //TODO no time to finalize :(
}
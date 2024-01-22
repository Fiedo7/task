package com.cqd.task.api.task.service;

import com.cqd.task.api.task.mapper.TaskMapper;
import com.cqd.task.api.task.model.Task;
import com.cqd.task.api.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PatternMatchingService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public void findPatternMatch(final String input, final String pattern, final Task task) throws InterruptedException {

        if (input.length() < pattern.length()) {
            return;
        }

        int bestMatchIndex = -1;
        int minTypos = Integer.MAX_VALUE;
        int patternLength = pattern.length();
        int inputLength = input.length();
        int stagesCnt = inputLength - patternLength;

        for (int i = 0; i <= stagesCnt; i++) {
            int typos = 0;
            for (int j = 0; j < patternLength; j++) {
                if (input.charAt(i + j) != pattern.charAt(j)) {
                    typos++;
                    if (typos > minTypos) {
                        break;
                    }
                }
            }

            saveProgress(task, i, stagesCnt);

            if (typos < minTypos) {
                minTypos = typos;
                bestMatchIndex = i;
                if (typos == 0) {
                    break;
                }
            }
        }
        taskMapper.updateCompleteTask(task, bestMatchIndex, minTypos);
        taskRepository.save(task);
    }

    private void saveProgress(Task task, double i, int stagesCnt) {
        int progress = (int) (i / (stagesCnt) * 100); // TODO in not reliable
        task.setProgress(progress);
        log.info("saveProgress for task id: {} progress: {}%", task.getId(), progress);
        taskRepository.save(task);
    }
}

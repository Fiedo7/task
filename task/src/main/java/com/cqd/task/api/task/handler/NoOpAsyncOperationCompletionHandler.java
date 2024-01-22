package com.cqd.task.api.task.handler;

import java.util.concurrent.CompletableFuture;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("!test")
@Service
public class NoOpAsyncOperationCompletionHandler implements AsyncOperationCompletionHandler {


    @Override
    public void handleCompletion(CompletableFuture<?> future) {
        // Nic nie robi
    }
}
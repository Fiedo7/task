package com.cqd.task.api.task.handler;

import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("test")
@Service
public class TestAsyncOperationCompletionHandler implements AsyncOperationCompletionHandler {

    private static final Logger log = LoggerFactory.getLogger(TestAsyncOperationCompletionHandler.class);

    @Override
    public void handleCompletion(CompletableFuture<?> future) throws InterruptedException {
        while (!future.isDone()) {
            log.info("Usypiam na 1s");
            Thread.sleep(1000);
        }
    }
}
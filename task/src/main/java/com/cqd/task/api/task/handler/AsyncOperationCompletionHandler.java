package com.cqd.task.api.task.handler;

import java.util.concurrent.CompletableFuture;

public interface AsyncOperationCompletionHandler {

    void handleCompletion(CompletableFuture<?> future) throws InterruptedException;

}

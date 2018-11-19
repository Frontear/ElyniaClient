package org.frontear.elynia.helper;

import java.util.concurrent.*;

public class BackgroundTask {
    private static ExecutorService executor = Executors.newCachedThreadPool();
    public static void Run(Runnable task) {
        executor.submit(task);
    }

    public static <T> T Run(Callable<T> task) throws ExecutionException, InterruptedException {
        Future<T> value =  executor.submit(task);
        return value.get();
    }

    public static void Shutdown() {
        executor.shutdown();
    }
}

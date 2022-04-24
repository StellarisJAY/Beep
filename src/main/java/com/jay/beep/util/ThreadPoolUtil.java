package com.jay.beep.util;

import java.util.concurrent.*;

/**
 * <p>
 *
 * </p>
 *
 * @author Jay
 * @date 2022/04/24 15:24
 */
public class ThreadPoolUtil {
    public static ExecutorService newSingleThreadPool(String name){
        return new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MINUTES,
                new LinkedBlockingQueue<>(), new NamedThreadFactory(name, true));
    }

    public static ExecutorService newThreadPool(int core, int max, String name, RejectedExecutionHandler rejectedExecutionHandler){
        return new ThreadPoolExecutor(core, max,
                0, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                new NamedThreadFactory(name, true), rejectedExecutionHandler);
    }

    /**
     * 新建IO密集型任务线程池
     * @param name 线程名
     * @return {@link ExecutorService}
     */
    public static ExecutorService newIoThreadPool(String name){
        return newThreadPool(2 * Runtime.getRuntime().availableProcessors(),
                2 * Runtime.getRuntime().availableProcessors(), name, new ThreadPoolExecutor.CallerRunsPolicy());
    }
}

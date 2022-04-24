package com.jay.beep.common;

/**
 * <p>
 *
 * </p>
 *
 * @author Jay
 * @date 2022/04/24 15:14
 */
public interface LifeCycle {
    void start() throws Exception;

    void stop() throws Exception;

    boolean started();

    void ensureStarted();
}

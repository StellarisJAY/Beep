package com.jay.beep.common;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p>
 *
 * </p>
 *
 * @author Jay
 * @date 2022/04/24 15:14
 */
public abstract class AbstractLifeCycle implements LifeCycle{

    private final AtomicBoolean started = new AtomicBoolean(false);

    @Override
    public void start() throws Exception{
        if(!started.compareAndSet(false, true)){
            throw new IllegalStateException("Component already started");
        }
    }

    @Override
    public void stop() throws Exception{
        if(!started.compareAndSet(true, false)){
            throw new IllegalStateException("Component already stopped");
        }
    }

    @Override
    public boolean started() {
        return started.get();
    }

    @Override
    public void ensureStarted() {
        if(!started.get()){
            throw new RuntimeException("Component not started yet");
        }
    }
}

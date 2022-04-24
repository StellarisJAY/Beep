package com.jay.beep.context;

import com.jay.beep.config.BeepConfig;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;

/**
 * <p>
 *  连接上下文
 * </p>
 *
 * @author Jay
 * @date 2022/04/23 11:34
 */
public class ChannelContext {
    private final AsynchronousSocketChannel socketChannel;
    private ByteBuffer receiveBuffer;
    private final BeepConfig config;

    public ChannelContext(BeepConfig config, AsynchronousSocketChannel socketChannel) {
        this.config = config;
        this.socketChannel = socketChannel;
    }

    public AsynchronousSocketChannel getSocketChannel(){
        return socketChannel;
    }
}

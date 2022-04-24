package com.jay.beep.server;

import com.jay.beep.common.AbstractLifeCycle;
import com.jay.beep.config.BeepServerConfig;
import com.jay.beep.handler.AcceptCompletionHandler;
import com.jay.beep.util.ThreadPoolUtil;

import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>
 *
 * </p>
 *
 * @author Jay
 * @date 2022/04/24 15:10
 */
public class BeepServer extends AbstractLifeCycle {
    private AsynchronousChannelGroup channelGroup;
    private AsynchronousServerSocketChannel serverSocketChannel;
    private int port = 9000;
    private final BeepServerConfig config;

    public BeepServer(BeepServerConfig config){
        this.config = config;
    }

    @Override
    public void start() throws Exception {
        super.start();
        channelGroup = AsynchronousChannelGroup.withThreadPool(ThreadPoolUtil.newThreadPool(2, 2, "Acceptor-", new ThreadPoolExecutor.DiscardPolicy()));
        serverSocketChannel = AsynchronousServerSocketChannel.open(channelGroup);
        serverSocketChannel.bind(new InetSocketAddress(port));
        serverSocketChannel.accept(this, new AcceptCompletionHandler(config));
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        serverSocketChannel.close();
        channelGroup.shutdownNow();
    }

}

package com.jay.beep.handler;

import com.jay.beep.config.BeepServerConfig;
import com.jay.beep.context.ChannelContext;
import com.jay.beep.server.BeepServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.StandardSocketOptions;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * <p>
 *
 * </p>
 *
 * @author Jay
 * @date 2022/04/24 15:09
 */
public class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, BeepServer> {

    private static final Logger log = LoggerFactory.getLogger(AcceptCompletionHandler.class);
    private final BeepServerConfig serverConfig;

    public AcceptCompletionHandler(BeepServerConfig serverConfig) {
        this.serverConfig = serverConfig;
    }

    @Override
    public void completed(AsynchronousSocketChannel socketChannel, BeepServer attachment) {
        try{
            log.info("Accept connection: {}", socketChannel.getRemoteAddress());
            socketChannel.setOption(StandardSocketOptions.SO_KEEPALIVE, true);
            socketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 16 * 1024);
            ChannelContext channelContext = new ChannelContext(serverConfig, socketChannel);

        }catch (IOException e){
            log.warn("Acceptor error ", e);
        }
    }

    @Override
    public void failed(Throwable exc, BeepServer attachment) {

    }
}

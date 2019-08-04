package com.my.netty.first;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TServer {
    public static void main(String[] args) {
        EventLoopGroup bossGrroup = new NioEventLoopGroup();
        EventLoopGroup workerGrroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGrroup, workerGrroup).channel(NioServerSocketChannel.class)
                .childHandler(new TestServerInitializer());

        try {
            ChannelFuture future = serverBootstrap.bind(8899).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {

        } finally {
            bossGrroup.shutdownGracefully();
            workerGrroup.shutdownGracefully();
        }
    }
}

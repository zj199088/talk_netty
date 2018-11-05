package com.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * webSocket的服务类
 * Ctrl + Alt + Space:不全代码
 * Alt + Enter:引入包
 */
public class WSService  {

    public static void main(String[] args) throws  Exception{
        //一对线程组
        EventLoopGroup main = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            //服务启动器
            ServerBootstrap serverBootstrap  = new ServerBootstrap();
            serverBootstrap.group(main,worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new WSServiceInitializer());
            //设置同步服务的端口
            ChannelFuture future = serverBootstrap.bind(8089).sync();
            //客户端对应的管道,监听关闭的chanel,设置位同步方式
            future.channel().closeFuture().sync();
        }finally {
            //优雅关闭
            main.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

}

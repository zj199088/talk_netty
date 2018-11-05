package com.netty.test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by Administrator on 2018/10/20.
 * 客户端发送一个请求，服务端返回helloWorld
 */
public class HelloService {
    public static void main(String[] args) throws  Exception{
        //定义一对线程组
        //主线程组，接受客户端的，不做任务
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //从线程组，boss给任务worker
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //服务器启动类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap
                    .group(bossGroup, workerGroup) //设置线程池
                    .channel(NioServerSocketChannel.class) //设置NIO的双向通道
                    .childHandler(new HelloServiceInitializer()); //处理workerGroup，拦截器...
            //启动server，并设置端口8080，同步启动服务类
            ChannelFuture channelFuture = serverBootstrap.bind(8080).sync();
            //客户端对应的管道,监听关闭的chanel,设置位同步方式
            channelFuture.channel().closeFuture().sync();
        }finally {
            //优雅关闭
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}

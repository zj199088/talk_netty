package com.netty.websocket;

import com.netty.test.CustomHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;


/**
 * webSocket 助手类初始化
 */
public class WSServiceInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {

        //T通过channel获得pipeline添加管道
        ChannelPipeline channelPipeline = channel.pipeline();
        //通过管道添加handler
        //webSocket基于http协议，所以要有http解码器
        channelPipeline.addLast(new HttpServerCodec());
        //写大数据流的支持
        channelPipeline.addLast(new CustomHandler());
        //httpMessage的聚合器
        channelPipeline.addLast(new HttpObjectAggregator(1024*64));
        //====================以上是对Http支持===================
        //webSocket协议，用于给指定客户端访问“/ws”
        //此handler会帮着做一些事情：
        //握手动作：handshaking(close, ping, pong) ping + pang = 心跳
        //对webSocket来说，都是以frames进行传输的，不同的数据类型对于frames也是不同的
        channelPipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        //自定义Handler
        channelPipeline.addLast(null);
    }
}

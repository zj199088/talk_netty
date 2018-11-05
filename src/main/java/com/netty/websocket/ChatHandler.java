package com.netty.websocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDate;

/**
 *
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    //通道的集合组
    private static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        String content = msg.text();
        System.out.println("接受到的数据"+ content);

//        for (Channel client : clients) {
//            client.writeAndFlush(new TextWebSocketFrame(""));
//        }
    //OR
        clients.writeAndFlush(new TextWebSocketFrame("[服务器]在" + LocalDate.now()+"接收数据"+content));
    }

    //将通道添加到group
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        clients.add(ctx.channel());
        System.out.println("客户端连接："+ ctx.channel().id().asLongText());

    }
    //既可以使用super,也可以使用clients.remove(ctx.channel());
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //super.handlerRemoved(ctx);
        System.out.println("客户端断开，channle对应的长id为："
                + ctx.channel().id().asLongText());
        System.out.println("客户端断开，channle对应的短id为："
                + ctx.channel().id().asShortText());
    }
}

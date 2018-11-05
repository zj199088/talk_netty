package com.netty.test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * 助手Handler
 */
public class CustomHandler extends SimpleChannelInboundHandler<HttpObject>{
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg)
            throws Exception {
        Channel channelhannel = ctx.channel();
        if (msg instanceof HttpRequest) {
            System.out.println("remote address:"+channelhannel.remoteAddress());
            //返回客户端的内容
            ByteBuf content = Unpooled.copiedBuffer("Hello Netty ~~", CharsetUtil.UTF_8);
            //构建http的response
            FullHttpResponse response =
                    new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                            HttpResponseStatus.OK,
                            content);
            //设置的格式和长度
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
            //将返回的数据刷到客户端
            ctx.writeAndFlush(response);
        }
    }
}

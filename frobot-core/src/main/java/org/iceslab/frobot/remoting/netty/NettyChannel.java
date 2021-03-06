package org.iceslab.frobot.remoting.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import org.iceslab.frobot.remoting.ChannelHandler;

import java.net.SocketAddress;

public class NettyChannel implements org.iceslab.frobot.remoting.Channel {
    Channel channel;

    public NettyChannel(ChannelHandlerContext ctx) {
        this.channel = ctx.channel();
    }

    public NettyChannel(Channel channel) {
        super();
        this.channel = channel;
    }

    @Override
    public SocketAddress localAddress() {
        return channel.localAddress();
    }

    @Override
    public SocketAddress remoteAddress() {
        return channel.remoteAddress();
    }

    @Override
    public ChannelHandler writeAndFlush(Object msg) {
        ChannelFuture channelFuture = channel.writeAndFlush(msg);
        return new NettyChannelHandler(channelFuture);
    }

    @Override
    public ChannelHandler close() {
        return new NettyChannelHandler(channel.close());
    }

    @Override
    public boolean isConnected() {
        return channel.isActive();
    }

    @Override
    public boolean isOpen() {
        return channel.isOpen();
    }

    @Override
    public boolean isClosed() {
        return !isOpen();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        NettyChannel that = (NettyChannel) o;

        return !(channel != null ? !channel.equals(that.channel) : that.channel != null);

    }

    @Override
    public int hashCode() {
        return channel != null ? channel.hashCode() : 0;
    }

}

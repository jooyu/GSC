package com.gsc.core.net.httpserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

public class HttpServer {
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);

	private NioEventLoopGroup bossGroup = new NioEventLoopGroup();
	private NioEventLoopGroup workerGroup = new NioEventLoopGroup();
	
	private HttpServerHandler handler;
	
	private int port = 8000;
	
	public HttpServer(int port) {
		this.port = port;
	}
	
	public HttpServer setHandler(HttpServerHandler handler) {
		this.handler = handler;
		return this;
	}

	public void run() {
		try {
			if (handler == null) {
				LOGGER.error("http server handler is null.");
				return;
			}
			
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
			.childOption(ChannelOption.TCP_NODELAY, Boolean.TRUE)
			.childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE)
			.option(ChannelOption.SO_BACKLOG, 8000).channel(NioServerSocketChannel.class)
			.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline()
					.addLast(new HttpServerCodec())
					.addLast("aggregator", new HttpObjectAggregator(1024 * 1024))
					.addLast(handler);
				}
			});

			b.bind(port);
			LOGGER.info("Server started: http://127.0.0.1:{}/", port);
		} catch (Exception e) {
			LOGGER.error("{}", e);
		} finally {

		}
	}
	
	public void stop() {
		bossGroup.shutdownGracefully();
		workerGroup.shutdownGracefully();
	}
}

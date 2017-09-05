package com.gsc.core.net.httpserver;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gsc.core.net.httpserver.router.RouteAction;
import com.gsc.core.net.httpserver.router.RouteResult;
import com.gsc.core.net.httpserver.router.Router;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

@ChannelHandler.Sharable
public class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpServerHandler.class);

	HttpRouterContext routerContext;
	
	public HttpServerHandler(String scanController) {
		routerContext = HttpRouterContext.valueOf(scanController);
		routerContext.init();
	}

	@Override
	public void channelRead0(ChannelHandlerContext ctx, FullHttpRequest req) {
		if (req.uri().equals("/favicon.ico")) {
			ctx.close();
			return;
		}
		
		runController(ctx,req);
	}
	
	private void runController(ChannelHandlerContext ctx, FullHttpRequest req) {
		Router<RouteAction> routers = routerContext.routers();
		final RouteResult<RouteAction> routeResult = routers.route(req.method(), req.uri());

		if (routeResult == null) {
			writeHttpStatus(ctx, HttpResponseStatus.BAD_REQUEST);
			return;
		}

		try {
			HttpController instance = routeResult.target().controllerClazz.newInstance();
			instance.init(ctx.channel(), req, routeResult);
			routeResult.target().method.invoke(instance);
		} catch (Exception ex) {

		}
	}
	
	private void writeHttpStatus(ChannelHandlerContext ctx, HttpResponseStatus status) {
		FullHttpResponse rsp = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status);
		rsp.headers().set(CONTENT_TYPE, "text/plain; charset=UTF-8");
		ctx.channel().writeAndFlush(rsp).addListener(ChannelFutureListener.CLOSE);
	}
	

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		if (cause instanceof IOException) {
			LOGGER.error("{}", cause.getMessage());
		} else {
			LOGGER.error("{}", cause);
		}
		ctx.close();
	}
}

package com.gsc.core.net.httpserver;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.gsc.core.net.httpserver.router.RouteAction;
import com.gsc.core.net.httpserver.router.RouteResult;
import com.gsc.core.utils.NumberUtils;
import com.gsc.core.utils.StringUtils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.util.CharsetUtil;

public abstract class HttpController {
	protected Logger LOGGER = LoggerFactory.getLogger(getClass());
	//netty的channel
	protected Channel channel;
	//netty的request
	protected FullHttpRequest request;
	//netty的http server 的路由
	protected RouteResult<RouteAction> routeResult;
	
	public Map<String, String> postMaps = new HashMap<>();
	

	public void init(Channel channel, FullHttpRequest request, RouteResult<RouteAction> routeResult) {
		this.channel = channel;
		this.request = request;
		this.routeResult = routeResult;
		postDecoder();
	}

	public HttpMethod method() {
		return request.method();
	}

	public String get(String name) {
		return get(name, "");
	}

	public String get(String name, String defaultValue) {
		String result = routeResult.param(name);
		if (StringUtils.isNotBlank(result)) {
			return result;
		}
		return defaultValue;
	}

	public int getInt(String params, int defaultValue) {
		String value = get(params);
		if (NumberUtils.isNumber(value)) {
			return Integer.valueOf(value);
		}
		return defaultValue;
	}

	public String post(String name) {
		return post(name, "");
	}

	public String post(String name, String defaultValue) {
		String result = postMaps.get(name);
		if (StringUtils.isNotBlank(result)) {
			return result;
		}
		return defaultValue;
	}
	
	public int postInt(String name, int defaultValue) {
		String value = post(name);
		if (NumberUtils.isNumber(value)) {
			return Integer.valueOf(value);
		}
		return defaultValue;
	}
	
	public Long postLong(String name, long defaultValue) {
		String value = post(name);
		if (NumberUtils.isNumber(value)) {
			return Long.valueOf(value);
		}
		return defaultValue;
	}
	
	private void postDecoder() {
		if (this.method() == HttpMethod.POST) {
			try {
				HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(request);
				decoder.offer(request);
				List<InterfaceHttpData> parmList = decoder.getBodyHttpDatas();
				for (InterfaceHttpData parm : parmList) {
					Attribute data = (Attribute) parm;
					postMaps.put(data.getName(), data.getValue());
				}
			} catch (Exception ex) {
				LOGGER.error("{}", ex);
			}
		}
	}

	public String getRemoteIp() {
		String remoteIp = "";
		SocketAddress add = this.channel.remoteAddress();
		if (add != null) {
			remoteIp = ((InetSocketAddress) add).getAddress().getHostAddress();
		}
		if (StringUtils.isBlank(remoteIp)) {
			remoteIp = ((InetSocketAddress) this.channel.localAddress()).getAddress().getHostAddress();
		}
		return remoteIp;
	}
	
	public void renderJson(Object object) {
		String json = JSON.toJSONString(object, SerializerFeature.IgnoreNonFieldGetter);
		render(json);
	}

	public void render(String text) {
		ByteBuf content = Unpooled.copiedBuffer(text, CharsetUtil.UTF_8);
		HttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
		response.headers().set(CONTENT_TYPE, "text/plain; charset=UTF-8");
		HttpUtil.setContentLength(response, content.readableBytes());
		channel.writeAndFlush(response);
	}
	
}

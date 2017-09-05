package com.gsc.core.net.httpserver.router;

import java.lang.reflect.Method;

import com.gsc.core.net.httpserver.HttpController;

public class RouteAction {

	public Class<HttpController> controllerClazz;

	public Method method;

	public static RouteAction valueOf(Class<HttpController> clazz, Method method) {
		RouteAction action = new RouteAction();
		action.controllerClazz = clazz;
		action.method = method;
		return action;
	}
}

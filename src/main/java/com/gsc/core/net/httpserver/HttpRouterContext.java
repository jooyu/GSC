package com.gsc.core.net.httpserver;

import java.lang.reflect.Method;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gsc.core.net.httpserver.router.RouteAction;
import com.gsc.core.net.httpserver.router.Router;
import com.gsc.core.net.httpserver.router.annotation.UrlRouter;
import com.gsc.core.utils.PackageScanner;

public class HttpRouterContext {
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpRouterContext.class);

	private Router<RouteAction> routers = new Router<>();

	private String packageScan;

	/**
	 * scan controller path
	 * @param packageScan
	 * @return
	 */
	public static HttpRouterContext valueOf(String packageScan) {
		return new HttpRouterContext(packageScan);
	}

	private HttpRouterContext(String packageScan) {
		this.packageScan = packageScan;
	}

	public void init() {
		try {
			Collection<Class<HttpController>> list = PackageScanner.scanEntityClazz(HttpController.class, packageScan);

			list.forEach(ctrl -> {
				Method[] methodList = ctrl.getDeclaredMethods();

				for (Method m : methodList) {
					m.setAccessible(true);
					UrlRouter urlRouter = m.getAnnotation(UrlRouter.class);
					if (urlRouter != null) {
						String[] paths = urlRouter.path();
						RouteAction action = RouteAction.valueOf(ctrl, m);
						for (String p : paths) {
							if (urlRouter.post()) {
								routers.POST(p, action);
							}
							if (urlRouter.get()) {
								routers.GET(p, action);
							}
						}
					}
				}
			});
			
			LOGGER.info("scan controller is completed! size:{}", routers.size());
		} catch (Exception e) {
			LOGGER.error("package scanner error!", e);
			return;
		}
	}

	public Router<RouteAction> routers() {
		return this.routers;
	}
}

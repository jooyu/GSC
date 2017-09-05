package com.gsc.core.net.httpserver.router.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * url路由注解
 * @author 0x737263
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface UrlRouter {

	/**
	 * "/user"
	 * "/user/:id"
	 * @return
	 */
	public String[] path() default "";

	/**
	 * 允许post
	 * @return
	 */
	public boolean post() default true;

	/**
	 * 允许get
	 * @return
	 */
	public boolean get() default true;
}

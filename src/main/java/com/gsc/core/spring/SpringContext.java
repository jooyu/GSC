package com.gsc.core.spring;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring应用程序上下文
 * 
 * @author 0x737263
 * 
 */
public class SpringContext {
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringContext.class);

	private static SpringContext springContext = null;
	
	private ApplicationContext appContext;
	
	private List<GameApplicationEvent> postEventList = new ArrayList<>();

	private SpringContext() {
		appContext = new ClassPathXmlApplicationContext("**/*springContext*.xml");

		postEventList.add(new Init01Event());
		postEventList.add(new Init02Event());
		postEventList.add(new Init03Event());
		postEventList.add(new Init04Event());
	}
	
	public static void run(GameApplicationEvent... appEvent) {
		SpringContext context = get();

		for (GameApplicationEvent e : appEvent) {
			context.postEventList.add(e);
		}

		// 抛出事件
		for (GameApplicationEvent e : context.postEventList) {
			context.appContext.publishEvent(e);
		}
	}
	
	private static SpringContext get() {
		synchronized (SpringContext.class){
			if (springContext == null) {
				springContext = new SpringContext();
			}			
		}

		return springContext;
	}

	public static ApplicationContext getContext() {
		return get().appContext;
	}

	public static Object getBean(String name) {
		return getContext().getBean(name);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> beanClazz) {
		String[] names = getContext().getBeanNamesForType(beanClazz);
		if ((names != null) && (names.length > 0)) {
			if (names.length == 1) {
				return (T) getContext().getBean(names[0]);
			}
			LOGGER.error("[{}] interface class must be only!", beanClazz);
		} else {
			LOGGER.error("[{}] class not found!", beanClazz);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> getBeanList(Class<T> beanClazz) {
		String[] names = getContext().getBeanNamesForType(beanClazz);

		List<T> beanList = new ArrayList<>();
		for (String n : names) {
			beanList.add((T) getContext().getBean(n));
		}
		return beanList;
	}

	public static List<Object> getBeanList() {
		ApplicationContext context = getContext();
		List<Object> list = new ArrayList<>();
		String[] names = context.getBeanDefinitionNames();
		for (String n : names) {
			list.add(context.getBean(n));
		}
		return list;
	}

}
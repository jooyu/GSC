package com.peak.core.spring;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring应用程序上下文
 * 
 * @author 0x737263
 * 
 */
public class SpringContext {
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringContext.class);

	private static ApplicationContext appContext = new ClassPathXmlApplicationContext("**/*springContext*.xml");
	private static ConfigurableApplicationContext configurableContext = (ConfigurableApplicationContext) appContext;
	private static BeanDefinitionRegistry beanDefinitionRegistry = (DefaultListableBeanFactory) configurableContext.getBeanFactory();
	
	private static List<GameApplicationEvent> postEventList = new ArrayList<>();

	static {
		postEventList.add(new Init01Event());
		postEventList.add(new Init02Event());
		postEventList.add(new Init03Event());
		postEventList.add(new Init04Event());
	}
	
	public static void run(GameApplicationEvent... appEvent) {
		for (GameApplicationEvent e : appEvent) {
			postEventList.add(e);
		}

		// 抛出事件
		for (GameApplicationEvent e : postEventList) {
			appContext.publishEvent(e);
		}
	}

	public static ApplicationContext getContext() {
		return appContext;
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
	
	public static void registerBean(String beanId, String className) {
		registerBeanByScope(beanId, className, "");
	}
	
	public static void registerBeanPrototype(String beanId, String className) {
		registerBeanByScope(beanId, className, BeanDefinition.SCOPE_PROTOTYPE);
	}
	
	public static void registerBeanByScope(String beanId, String className, String scope) {
		// get the BeanDefinitionBuilder
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(className);
		// get the BeanDefinition
		BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
		if(scope != null && scope.length() > 0) {
			beanDefinition.setScope(scope);	
		}
		// register the bean
		beanDefinitionRegistry.registerBeanDefinition(beanId, beanDefinition);
	}

    /**
     * 移除bean
     * @param beanId bean的id
     */
	public static void unregisterBean(String beanId) {
		beanDefinitionRegistry.removeBeanDefinition(beanId);
	}
	

}
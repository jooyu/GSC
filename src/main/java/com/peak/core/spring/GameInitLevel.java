package com.peak.core.spring;
//package phantom.core.spring;
//
//import org.springframework.context.ApplicationEvent;
//
///**
// * <pre>
// * 服务器启动事件分层顺序
// * 在服务器启动时，各事件会按照此枚举中的排列顺序依次执行
// * 某些业务逻辑存在相互顺序依赖时，请实现org.springframework.context.ApplicationListener接口，并根据此枚举的顺序进行不同的事件监听
// * 如需增加新的事件请在com.shared.core.event包中添加
// * </pre>
// * @author PhilShen
// *
// */
//public enum GameInitLevel {
//
//	/**
//	 * 游戏基础组件初始化
//	 */
//	GAME_BASE_INIT(new Init01Event("game base initialization...")),
//	/**
//	 * id表初始化
//	 */
//	ID_TABLE_INIT(new Init02Event("id table initialization...")),
//	/**
//	 * 数据库初始化
//	 */
//	DAO_INIT(new Init03Event("dao initialization...")),
//	/**
//	 * 逻辑层初始化
//	 */
//	FACADE_INIT(new Init04Event("facade initialization..."));
//	
//	GameInitLevel(ApplicationEvent event) {
//		this.event = event;
//	}
//	
//	private ApplicationEvent event;
//	
//	public ApplicationEvent getEvent() {
//		return event;
//	}
//}

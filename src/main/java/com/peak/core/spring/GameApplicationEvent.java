package com.peak.core.spring;

import org.springframework.context.ApplicationEvent;

public abstract class GameApplicationEvent extends ApplicationEvent {
	private static final long serialVersionUID = -8225650104569946986L;

	public GameApplicationEvent(Object source) {
		super(source);
	}
}

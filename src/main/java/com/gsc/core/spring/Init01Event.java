package com.gsc.core.spring;

public class Init01Event extends GameApplicationEvent {

	private static final long serialVersionUID = 2500166433166693184L;

	public Init01Event() {
		super("init 01 event");
	}
}

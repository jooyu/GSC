package com.peak.core.extend;

/**
 * 
 * @author 0x737263
 *
 * @param <T>
 */
public class ObjectReference <T> {

	/**
	 * 软引用对象 
	 */
	private T content;

	public T get() {
		return content;
	}

	public void set(T content) {
		this.content = content;
	}

	
}

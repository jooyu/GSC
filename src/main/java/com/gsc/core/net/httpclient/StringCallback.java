package com.gsc.core.net.httpclient;

public interface StringCallback {

	public void completed(String content);

	public void failed(Exception ex);

}

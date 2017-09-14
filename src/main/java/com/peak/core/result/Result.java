package com.peak.core.result;

/**
 * 最简单的结果类,返回一个状态啥的用这个
 * @author 0x737263
 *
 */
public class Result {

	public int code = 0;
	public String desc = "";

	public Result() {
	}

	public Result(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static Result valueOf() {
		return new Result();
	}
	
	public static Result valueOf(int code, String desc) {
		return new Result(code, desc);
	}

}

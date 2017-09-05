package com.gsc.core.net.httpserver;

import com.gsc.core.constant.StatusCode;
import com.gsc.core.result.Result;

/**
 * 抽象基础控制器类
 * @author eaves.zhu
 *
 */
public abstract class BaseController extends HttpController {

	public void renderCode(StatusCode code) {
		renderJson(Result.valueOf(code.getCode(), code.getDesc()));
	}

}

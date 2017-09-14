package com.peak.core.net.httpserver;

import com.peak.core.constant.StatusCode;
import com.peak.core.result.Result;

public abstract class BaseController extends HttpController {

	public void renderCode(StatusCode code) {
		renderJson(Result.valueOf(code.getCode(), code.getDesc()));
	}

}

package nju.software.demo.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import nju.software.demo.service.model.base.ResultModel;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;

/**
 * http请求的相应处理
 */
@Component
public class ResponseBuilder {

	private static final String DEFAULT_CHARSET = "utf-8";

	/**
	 * 返回json请求的数据，传入参数content，必须满足json格式
	 */
	public void writeJsonResponse(HttpServletResponse response, String content) throws IOException {
		response.addHeader("Content-Type", "application/json;charset=" + DEFAULT_CHARSET);
		response.setCharacterEncoding(DEFAULT_CHARSET);

		PrintWriter writer = response.getWriter();
		writer.write(content);
		writer.flush();
		writer.close();
	}

	/**
	 * 返回json请求的数据，传入参数Object
	 */
	public void writeJsonResponse(HttpServletResponse response, Object o) throws IOException {
		Gson gson = new Gson();
		String content = gson.toJson(o);
		writeJsonResponse(response, content);
	}

	/**
	 * 返回json请求的数据，传入参数ResultModel
	 */
	public void writeJsonResponse(HttpServletResponse response, ResultModel result) throws Exception {
		Gson gson = new Gson();
		String strJson = gson.toJson(result);
		writeJsonResponse(response, strJson);
	}

	/**
	 * ajaxupload的json返回参数的设置
	 */
	public void writeJsonResponseForAjaxUpload(HttpServletResponse response, Object o) throws Exception {
		Gson gson = new Gson();
		String strJson = gson.toJson(o);

		response.addHeader("Content-Type", "text/html;charset=" + DEFAULT_CHARSET);
		response.setCharacterEncoding(DEFAULT_CHARSET);

		PrintWriter writer = response.getWriter();
		writer.write(strJson);
		writer.flush();
		writer.close();
	}

}
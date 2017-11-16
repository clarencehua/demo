package nju.software.demo.web.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/**
 * ∑√Œ ¿πΩÿ∆˜
 * 
 */
public class AccessInterceptor extends HandlerInterceptorAdapter {
	private static final Logger log = Logger.getLogger(AccessInterceptor.class);

	private List<String> excludeUrls;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String requestURI = request.getRequestURI();
		String toLogin = request.getRequestURL().toString();
		String doName = request.getServletPath();
		if (doName.contains("admin")) {
				return false;
			} else {
				return true;
			}
	}

	public List<String> getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}

}

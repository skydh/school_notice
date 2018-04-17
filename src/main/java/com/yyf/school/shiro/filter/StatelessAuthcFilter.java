package com.yyf.school.shiro.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.AccessControlFilter;

import com.yyf.school.shiro.realm.StatelessToken;

/**
 * 
 * @author yyf 这里只是拦截处理请求
 *
 * 
 */
public class StatelessAuthcFilter extends AccessControlFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		return false;
	}

	/**
	 * 
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest hReq = (HttpServletRequest) request;
		HttpServletRequest httpRequest = hReq;
		Cookie[] cookies = httpRequest.getCookies();
		StringBuilder userId = new StringBuilder();
		StringBuilder tokenId = new StringBuilder();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("userId")) {
				userId.append(cookie.getValue());
			}
			if (cookie.getName().equals("token")) {
				tokenId.append(cookie.getValue());
			}
		}
		Map<String, String[]> params = new HashMap<String, String[]>(request.getParameterMap());
		StatelessToken token = new StatelessToken(userId.toString(), params, tokenId.toString());
		try {
			getSubject(request, response).login(token);
		} catch (Exception e) {
			e.printStackTrace();
			onLoginFail(response);
			return false;
		}
		return true;
	}

	private void onLoginFail(ServletResponse response) throws IOException {
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		httpResponse.getWriter().write("login error");
	}
}

package com.yyf.school.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 处理跨域请求
 * 
 * @author yyf
 *
 */
@Component
public class AccessControlFilter implements Filter {
	public static Logger logger = LoggerFactory.getLogger(AccessControlFilter.class);
	public static final String CLIENT_CONFIG_FILE = "accessAllow.properties";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;
		String origin = request.getHeader("origin");
		if (origin == null || "".equals(origin)) {
			origin = "*";
		}

		logger.debug("origin---->" + origin);

		response.setHeader("Access-Control-Allow-Origin", origin); // 没部署nginx,线上环境不好使，暂时注释掉
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		// ajax通过header传递到后端

		response.setHeader("Access-Control-Allow-Credentials", "true");

		chain.doFilter(req, res);

	}

	@Override
	public void destroy() {
	}

}

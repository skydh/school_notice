package com.yyf.school.shiro.context;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * 该类用来存放用户上下文信息 这边上下文信息默认直接初始化,有点绕哈，
 * 简单说下，这个map直接被初始化了。每一个线程对这个hashmap的修改不会影响到对方。
 * 
 * @author yyf
 *
 */
@Component
public class TokenApplication {

	private final ThreadLocal<Map<String, Object>> resources = ThreadLocal.withInitial(() -> {
		return new HashMap<>();
	});

	public String getUserId() {
		return (String) resources.get().get("userId");
	}

	public void setUserId(String userId) {
		resources.get().put("userId", userId);
	}

	public String getToken() {
		return (String) resources.get().get("token");
	}

	public void setToken(String token) {
		resources.get().put("token", token);
	}

	public int getSchoolCase() {
		return (int) resources.get().get("caseSchool");
	}

	public void setSchoolCase(int caseSchool) {
		resources.get().put("caseSchool", caseSchool);
	}
	public int getAllow() {
		return (int) resources.get().get("isAllow");
	}

	public void setAllow(int caseSchool) {
		resources.get().put("isAllow", caseSchool);
	}


}

package com.yyf.school.login.vo;

import java.io.Serializable;

/**
 * 
 * @author yyf
 *
 */
public class TokenVO implements Serializable {

	private static final long serialVersionUID = -1982615125419157861L;
	private String token;
	private String userId;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	

}

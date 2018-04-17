package com.yyf.school.shiro.realm;

import org.apache.shiro.authc.AuthenticationToken;

import java.util.Map;

/**
 * <p>
 * User: Zhang Kaitao
 * <p>
 * Date: 14-2-26
 * <p>
 * Version: 1.0
 */
public class StatelessToken implements AuthenticationToken {

	private static final long serialVersionUID = 1L;
	private String userId;
	private Map<String, ?> params;
	private String tokenId;

	public StatelessToken(String userId, Map<String, ?> params, String tokenId) {
		this.userId = userId;
		this.params = params;
		this.tokenId = tokenId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Map<String, ?> getParams() {
		return params;
	}

	public void setParams(Map<String, ?> params) {
		this.params = params;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	@Override
	public Object getPrincipal() {
		return userId;
	}

	@Override
	public Object getCredentials() {
		return tokenId;
	}
}

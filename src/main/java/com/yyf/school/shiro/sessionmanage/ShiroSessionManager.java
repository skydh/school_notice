package com.yyf.school.shiro.sessionmanage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yyf.school.shiro.cache.TokenCache;

/**
 * 
 * 
 * @author yyf
 *
 */
@Component
public class ShiroSessionManager {

	@Autowired
	private TokenCache tokenCache;

	// 注入新的token
	public void registOnlineSession(final String userName, final String token) {

	}

	//
	private void clearOnlineSession(final String key) {

	}

	//
	public boolean validateOnlineSession(final String key, final String hashKey) {

		return false;
	}

	//
	public void delOnlineSession(final String key, final String hashKey) {
		// redisCacheTemplate.hDel(key, hashKey);
	}

	//
	public void deleteUserSession(final String key) {
		// redisCacheTemplate.delete(key);
	}
}
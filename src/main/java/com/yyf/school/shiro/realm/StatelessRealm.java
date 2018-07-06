package com.yyf.school.shiro.realm;

import java.util.Date;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.yyf.school.shiro.cache.TokenCache;
import com.yyf.school.shiro.codec.HmacSHA256Utils;
import com.yyf.school.shiro.context.TokenApplication;
import com.yyf.school.utils.constant.Constants;

/**
 * @author yyf 这里进行判断，1,是否有token，2，计算token是否正确，3判断token是否过期 Version: 1.0
 */
public class StatelessRealm extends AuthorizingRealm {
	@Autowired
	private TokenCache tokenCach;
	@Autowired
	private TokenApplication tokenApplication;
	
	


	@Override
	public boolean supports(AuthenticationToken token) {

		return token instanceof StatelessToken;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.addRole("admin");
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		StatelessToken statelessToken = (StatelessToken) token;
		String userId = statelessToken.getUserId();
		String tokenId = statelessToken.getTokenId();
		if (HmacSHA256Utils.digest(Constants.tokenKey, userId).equals(tokenId)) {
			if (tokenCach.isExistToken(tokenId)) {
				long date = new Date().getTime();
				long datetime = tokenCach.getTokenDate(tokenId);
				if ((date - datetime) < Constants.offerDate) {
					// 二次刷新过期时间
					tokenCach.setAndUpdateTokenDate(tokenId, date);
					tokenApplication.setToken(tokenId);
					tokenApplication.setUserId(userId);
					return new SimpleAuthenticationInfo(userId, tokenId, getName());
				} else {
					throw new AuthenticationException("token已经过期了");
				}
			} else {
				throw new AuthenticationException("token已经过期了111");
			}

		} else {
			throw new AuthenticationException("用户id和token不匹配");
		}
	}
}

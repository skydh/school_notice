package com.yyf.school.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.yyf.school.shiro.codec.HmacSHA256Utils;

/**
 * <p>
 * User: Zhang Kaitao
 * <p>
 * Date: 14-2-26
 * <p>
 * 这里进行判断，1,是否有token，2，计算token是否正确，3判断token是否过期 Version: 1.0
 */
public class StatelessRealm extends AuthorizingRealm {
	@Override
	public boolean supports(AuthenticationToken token) {

		return token instanceof StatelessToken;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		// String username = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.addRole("admin");
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		StatelessToken statelessToken = (StatelessToken) token;
		String username = statelessToken.getUsername();
		String key = getKey(username);
		String serverDigest = HmacSHA256Utils.digest(key, statelessToken.getParams());
		System.out.println(statelessToken.getClientDigest());
		System.out.println(serverDigest);

		return new SimpleAuthenticationInfo(username, serverDigest, getName());
	}

	private String getKey(String username) {
		if ("admin".equals(username)) {
			return "dadadswdewq2ewdwqdwadsadasd";
		}
		return null;
	}
}

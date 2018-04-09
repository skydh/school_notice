package com.yyf.school.login.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yyf.school.login.dao.LoginDao;
import com.yyf.school.login.service.LoginService;
import com.yyf.school.login.vo.AllRoleVO;
import com.yyf.school.login.vo.DoApproveVO;
import com.yyf.school.login.vo.TokenVO;
import com.yyf.school.login.vo.UserVO;
import com.yyf.school.shiro.cache.TokenCache;
import com.yyf.school.shiro.codec.HmacSHA256Utils;
import com.yyf.school.shiro.context.TokenApplication;
import com.yyf.school.utils.constant.Constants;
import com.yyf.school.utils.exception.SchoolException;
import com.yyf.school.utils.idcreate.IdCreateUtils;

/**
 * 登录注册的service 关于注册验证这边就不处理了。
 * 
 * @author yyf
 *
 */
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao loginDao;
	@Autowired
	private TokenCache tokenCache;
	@Autowired
	private TokenApplication tokenApplication;

	@Override
	@Transactional
	public TokenVO register(String username, String password) throws SchoolException {
		try {
			TokenVO tokenVO = new TokenVO();
			String id = IdCreateUtils.getId();
			loginDao.register(id, username, password);
			String token = HmacSHA256Utils.digest(Constants.tokenKey, id);
			// 加入到缓存
			tokenCache.setAndUpdateTokenDate(token, new Date().getTime());
			tokenCache.autoIncrements();
			// 加入到上下文
			tokenApplication.setToken(token);
			tokenApplication.setUserId(id);

			tokenVO.setToken(token);
			tokenVO.setUserId(id);

			return tokenVO;
		} catch (Exception e) {
			throw new SchoolException("注册数据失败");
		}

	}

	@Override
	public TokenVO login(String username, String password) throws SchoolException {
		try {
			TokenVO tokenVO = new TokenVO();
			AllRoleVO allRoleVO = loginDao.IsUser(username, password);
			if (allRoleVO.getId() != null) {
				String token = HmacSHA256Utils.digest(Constants.tokenKey, allRoleVO.getId());
				// 加入到缓存
				tokenCache.setAndUpdateTokenDate(token, new Date().getTime());
				// 这里要判断下是否需要清除过期数据
				int atomicInteger = tokenCache.getAtomicInteger();
				if (atomicInteger % 3 == 0) {
					tokenCache.clearData();
				}
				tokenCache.autoIncrements();
				// 加入到上下文
				tokenApplication.setToken(token);
				tokenApplication.setUserId(allRoleVO.getId());
				tokenApplication.setSchoolCase(allRoleVO.getCaseSchool());
			
				tokenVO.setToken(token);
				tokenVO.setUserId(allRoleVO.getId());
				return tokenVO;
			} else {
				throw new SchoolException("登录失败");
			}

		} catch (Exception e) {
			throw new SchoolException("登录失败");
		}
	}

	// 登出
	@Override
	public void logout(String token) throws SchoolException {
		try {
			tokenCache.logout(token);
		} catch (Exception e) {
			throw new SchoolException("登出异常");
		}

	}

	@Override
	public void allData(AllRoleVO allRoleVO) throws SchoolException {
		if (allRoleVO.getId() == null) {
			allRoleVO.setId(IdCreateUtils.getId());
			allRoleVO.setpId(tokenApplication.getUserId());
			loginDao.insertAllData(allRoleVO);
			tokenApplication.setSchoolCase(allRoleVO.getCaseSchool());
		} else {
			loginDao.updateAllData(allRoleVO);
		}

	}

	@Override
	@Transactional
	public void doApprove(DoApproveVO ids) throws SchoolException {
		loginDao.doApprove(1, ids.getoId());
		loginDao.doApprove(2, ids.getnId());
	}

	@Override
	public UserVO showApprove() throws SchoolException {
		String id = tokenApplication.getUserId();
		int caseSchool = tokenApplication.getSchoolCase();
		// if()
		return null;

	}
}

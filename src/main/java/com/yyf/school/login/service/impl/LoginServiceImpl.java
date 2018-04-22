package com.yyf.school.login.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yyf.school.login.dao.LoginDao;
import com.yyf.school.login.service.LoginService;
import com.yyf.school.login.vo.AllRoleVO;
import com.yyf.school.login.vo.DoApproveVO;
import com.yyf.school.login.vo.TokenVO;
import com.yyf.school.login.vo.TreeDataVO;
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

	public LoginServiceImpl() {
		System.out.println("LoginServiceImpl");
	}

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
	public void allData(List<String> codes) throws SchoolException {

		String pId = tokenApplication.getUserId();
		AllRoleVO allRoleVO = new AllRoleVO();
		int temp = codes.size();
		if (temp > 0) {
			allRoleVO.setSchoolCode(codes.get(0));
		}
		if (temp > 1) {
			allRoleVO.setCollegeCode(codes.get(1));
		}
		if (temp > 2) {
			allRoleVO.setGradeCode(codes.get(2));
		}
		if (temp > 3) {
			allRoleVO.setClassCode(codes.get(3));
		}
		allRoleVO.setCaseSchool(temp + 1);
		String id = loginDao.showId(pId);
		if (id == null) {
			allRoleVO.setId(IdCreateUtils.getId());
			allRoleVO.setpId(tokenApplication.getUserId());
			loginDao.insertAllData(allRoleVO);

		} else {
			allRoleVO.setId(id);
			allRoleVO.setpId(tokenApplication.getUserId());
			loginDao.updateAllData(allRoleVO);
		}

	}

	/**
	 * 这里做批量处理操作，但是无奈前端太菜，语法不熟，只能单个了
	 */
	@Override
	@Transactional
	public void doApprove(DoApproveVO ids) throws SchoolException {
		if (ids.getIsApprove()) {
			loginDao.doApprove(1, ids.getId());
		} else {
			loginDao.doApprove(2, ids.getId());
		}
	}

	// 这里有重写
	@Override
	public List<UserVO> showApprove() throws SchoolException {
		String pId = tokenApplication.getUserId();
		AllRoleVO allRoleVO = loginDao.showCode(pId);
		int caseSchool = allRoleVO.getCaseSchool();
		String id = loginDao.showId(pId);
		return loginDao.showApprove(pId, caseSchool, id);
	}

	@Override
	public List<TreeDataVO> showAll() throws SchoolException {
		List<AllRoleVO> list = loginDao.getAll();
		List<TreeDataVO> backListSchool = new ArrayList<TreeDataVO>();
		List<TreeDataVO> backListCollege = new ArrayList<TreeDataVO>();
		List<TreeDataVO> backListGrade = new ArrayList<TreeDataVO>();
		List<TreeDataVO> backListClass = new ArrayList<TreeDataVO>();
		for (AllRoleVO temp : list) {
			TreeDataVO vo = new TreeDataVO();
			vo.setLabel(temp.getUserName());
			vo.setValue(temp.getId());
			vo.setClassCode(temp.getClassCode());
			vo.setCollegeCode(temp.getCollegeCode());
			vo.setGradeCode(temp.getGradeCode());
			vo.setSchoolCode(temp.getSchoolCode());
			if (temp.getCaseSchool() == 1) {
				backListSchool.add(vo);
			}
			if (temp.getCaseSchool() == 2) {
				backListCollege.add(vo);
			}
			if (temp.getCaseSchool() == 3) {
				backListGrade.add(vo);
			}
			if (temp.getCaseSchool() == 4) {
				backListClass.add(vo);
			}
		}
		for (TreeDataVO temp : backListGrade) {
			List<TreeDataVO> tempList = new ArrayList<TreeDataVO>();
			for (TreeDataVO temp1 : backListClass) {
				if (temp.getValue().equals(temp1.getGradeCode())) {
					tempList.add(temp1);
				}
			}
			temp.setChildren(tempList);
		}
		for (TreeDataVO temp : backListCollege) {
			List<TreeDataVO> tempList = new ArrayList<TreeDataVO>();
			for (TreeDataVO temp1 : backListGrade) {
				if (temp.getValue().equals(temp1.getCollegeCode())) {
					tempList.add(temp1);
				}
			}
			temp.setChildren(tempList);
		}
		for (TreeDataVO temp : backListSchool) {
			List<TreeDataVO> tempList = new ArrayList<TreeDataVO>();
			for (TreeDataVO temp1 : backListCollege) {
				if (temp.getValue().equals(temp1.getSchoolCode())) {
					tempList.add(temp1);
				}
			}
			temp.setChildren(tempList);
		}

		return backListSchool;
	}
}

package com.yyf.school.login.service;

import java.util.List;

import com.yyf.school.login.vo.AllRoleVO;
import com.yyf.school.login.vo.DoApproveVO;
import com.yyf.school.login.vo.TokenVO;
import com.yyf.school.login.vo.UserVO;
import com.yyf.school.utils.exception.SchoolException;

public interface LoginService {

	/**
	 * 注册服务
	 * 
	 * @param username
	 * @param password
	 */
	TokenVO register(String username, String password) throws SchoolException;

	/**
	 * 登录服务
	 * 
	 * @param username
	 * @param password
	 */
	TokenVO login(String username, String password) throws SchoolException;

	/**
	 * 登出服务
	 * 
	 * @param username
	 * @param password
	 */
	void logout(String token) throws SchoolException;

	/**
	 * 资料完善
	 * 
	 * @param username
	 * @param password
	 */
	void allData(AllRoleVO allRoleVO) throws SchoolException;

	/**
	 * 组长审批
	 * 
	 * @param ids
	 * 
	 */
	void doApprove(DoApproveVO ids) throws SchoolException;

	/**
	 * 查看当前审批
	 * 
	 * @param ids
	 * 
	 */
	 List<UserVO> showApprove() throws SchoolException;

}

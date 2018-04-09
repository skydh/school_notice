package com.yyf.school.login.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yyf.school.login.vo.AllRoleVO;

/**
 * 
 * @author yyf 该类用来查询Dao
 *
 */
public interface LoginDao {
	/**
	 * 判断用户是否
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	AllRoleVO IsUser(@Param("username") String username, @Param("password") String password);

	/**
	 * 注册用户
	 * 
	 * @param username
	 * @param password
	 */
	void register(@Param("id") String id, @Param("username") String username, @Param("password") String password);

	/**
	 * 插入资料
	 * 
	 * @param username
	 * @param password
	 */
	void insertAllData(AllRoleVO vo);

	/**
	 * 更新数据资料
	 * 
	 * @param username
	 * @param password
	 */
	void updateAllData(AllRoleVO vo);

	/**
	 * 组长审批
	 * 
	 * @param ids
	 * 
	 */
	void doApprove(int state, List<String> ids);

	/**
	 * 查看组长审批
	 * 
	 * @param ids
	 * 
	 */
	void doApprove(String pId);

}

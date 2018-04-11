package com.yyf.school.login.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yyf.school.login.vo.AllRoleVO;
import com.yyf.school.login.vo.UserVO;

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
	void doApprove(@Param("state") int state, @Param("ids") List<String> ids);

	/**
	 * 查看组长审批
	 * 
	 * @param ids
	 * 
	 */
	List<UserVO> showApprove(@Param("pId") String pId, @Param("caseSchool") int caseSchool, @Param("id") String id);

	/**
	 * 查询当前角色主键
	 * 
	 * @param ids
	 * 
	 */
	String showId(String pId);

	/**
	 * 查询当前角色的校id,学院id
	 * 
	 * @param ids
	 * 
	 */
	AllRoleVO showCode(String id);

	/**
	 * 判断当前用户是否有发布，审批权限
	 * 
	 * @param ids
	 * 
	 */
	String isPower(String id);

	/**
	 * 根据角色id查询用户名
	 * 
	 * @param ids
	 * 
	 */
	String findUserNameById(String id);

}

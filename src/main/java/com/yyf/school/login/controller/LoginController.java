package com.yyf.school.login.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yyf.school.login.service.LoginService;
import com.yyf.school.login.vo.DoApproveVO;
import com.yyf.school.shiro.context.TokenApplication;
import com.yyf.school.utils.backdata.BackDataJson;
import com.yyf.school.utils.exception.SchoolException;

/**
 * 
 * @author yyf 该用来登录和注册以及登出的
 *
 */
@Controller
@RequestMapping("/user")
public class LoginController {
	@Autowired
	private LoginService loginService;
	@Autowired
	private TokenApplication tokenApplication;

	/**
	 * 登录验证 登录的这个url，shiro不做拦截，放过来，我们这里进行验证，判断是否存在这个信息。
	 * 存在则将其生成一个token返回，并且加入到tokencache里面，并且将查到的信息加入到token上下文里面。
	 * 且，判断其原子变量除以100是否余0，若是除的尽，那么删除过期时间的key-value.
	 * 
	 * z这里加user_name唯一校验//由于时间问题不加了
	 * @param courseId
	 * @param password
	 * 
	 * @return
	 */
	
	public LoginController() {
		System.out.println("LoginController");
	}
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@ResponseBody
	public BackDataJson login(@RequestParam("username") String username, @RequestParam("password") String password) {
		BackDataJson backDataJson = new BackDataJson();
		try {
			backDataJson.setBackData(loginService.login(username, password));
			backDataJson.setSuccess(true);
			backDataJson.setBackMsg("登录成功");
		} catch (SchoolException e) {
			backDataJson.setSuccess(false);
			backDataJson.setBackMsg("登录失败" + e.getMessage());

		}
		return backDataJson;

	}

	@RequestMapping(value = "/getTree", method = RequestMethod.GET)
	@ResponseBody
	public BackDataJson getTree() {
		BackDataJson backDataJson = new BackDataJson();
		try {
			backDataJson.setBackData(loginService.showAll());
			backDataJson.setSuccess(true);
			backDataJson.setBackMsg("登录成功");
		} catch (SchoolException e) {
			backDataJson.setSuccess(false);
			backDataJson.setBackMsg("登录失败" + e.getMessage());

		}
		return backDataJson;

	}

	/**
	 * 这里做登出处理,主要是清除tokencache里面的值。
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@ResponseBody
	public BackDataJson logout(HttpServletRequest request) {
		// UODO
		BackDataJson backDataJson = new BackDataJson();
		try {
			loginService.logout(tokenApplication.getToken());
			backDataJson.setSuccess(true);
			backDataJson.setBackMsg("登出成功");
		} catch (SchoolException e) {
			backDataJson.setSuccess(false);
			backDataJson.setBackMsg("登出失败" + e.getMessage());
		}
		return backDataJson;

	}

	/**
	 * 这里做注册信息处理 将信息加入到数据库中，并且返回生成一个token,将其加入到缓存，加入到上下文，但是不清除过期数据
	 * 
	 * @param courseId
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	@ResponseBody
	public BackDataJson register(@RequestParam("username") String username, @RequestParam("password") String password) {
		BackDataJson backDataJson = new BackDataJson();
		try {
			backDataJson.setBackData(loginService.register(username, password));
			backDataJson.setSuccess(true);
			backDataJson.setBackMsg("注册成功");

		} catch (SchoolException e) {
			backDataJson.setSuccess(false);
			backDataJson.setBackMsg("注册失败" + e.getMessage());

		}
		return backDataJson;

	}

	/**
	 * 完善资料
	 * 
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/allData")
	@ResponseBody
	public BackDataJson allData(@RequestParam("codes") List<String> codes) {
		BackDataJson backDataJson = new BackDataJson();
		try {
			loginService.allData(codes);
			backDataJson.setSuccess(true);
			backDataJson.setBackMsg("完善资料成功");

		} catch (SchoolException e) {
			backDataJson.setSuccess(false);
			backDataJson.setBackMsg("完善资料失败" + e.getMessage());

		}
		return backDataJson;

	}

	// 绝望，前端封装的post请求一直有问题，还是get算了
	@RequestMapping(value = "/doApprove")
	@ResponseBody
	public BackDataJson doApprove(@RequestParam("id") List<String> id, @RequestParam("isApprove") Boolean isApprove) {
		BackDataJson backDataJson = new BackDataJson();
		try {
			DoApproveVO ids = new DoApproveVO();
			ids.setId(id);
			ids.setIsApprove(isApprove);
			loginService.doApprove(ids);
			backDataJson.setSuccess(true);
			backDataJson.setBackMsg("完善资料成功");

		} catch (SchoolException e) {
			backDataJson.setSuccess(false);
			backDataJson.setBackMsg("完善资料失败" + e.getMessage());

		}
		return backDataJson;

	}

	@RequestMapping(value = "/showApprove")
	@ResponseBody
	public BackDataJson showApprove() {
		BackDataJson backDataJson = new BackDataJson();
		try {
			backDataJson.setBackData(loginService.showApprove());
			backDataJson.setSuccess(true);
			backDataJson.setBackMsg("完善资料成功");

		} catch (SchoolException e) {
			backDataJson.setSuccess(false);
			backDataJson.setBackMsg("完善资料失败" + e.getMessage());

		}
		return backDataJson;

	}

}

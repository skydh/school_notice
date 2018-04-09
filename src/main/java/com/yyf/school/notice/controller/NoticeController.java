package com.yyf.school.notice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yyf.school.notice.service.NoticeService;
import com.yyf.school.notice.vo.NoticeVO;
import com.yyf.school.utils.backdata.BackDataJson;
import com.yyf.school.utils.exception.SchoolException;

@Controller
@RequestMapping("/notice")
public class NoticeController {

	@Autowired
	private NoticeService loginService;

	/**
	 * 删除数据
	 * 
	 * @param courseId
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@ResponseBody
	public BackDataJson login(@RequestParam("writerId") String writerId, @RequestParam("id") List<String> id) {

		BackDataJson backDataJson = new BackDataJson();
		try {
			// backDataJson.setBackData(loginService.login(username, password));
			backDataJson.setSuccess(true);
			backDataJson.setBackMsg("登录成功");

		} catch (SchoolException e) {
			backDataJson.setSuccess(false);
			backDataJson.setBackMsg("登录失败" + e.getMessage());

		}
		return backDataJson;

	}

	/**
	 * 新增数据 从上下文获取到用户id,根据用户id查角色id和角色名字,作为文章外键和文章作者
	 * 
	 * @param courseId
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/insert")
	@ResponseBody
	public BackDataJson login(@RequestBody NoticeVO noticeVO) {

		BackDataJson backDataJson = new BackDataJson();
		try {
			// backDataJson.setBackData(loginService.login(username, password));
			backDataJson.setSuccess(true);
			backDataJson.setBackMsg("登录成功");

		} catch (SchoolException e) {
			backDataJson.setSuccess(false);
			backDataJson.setBackMsg("登录失败" + e.getMessage());

		}
		return backDataJson;

	}

	/**
	 * 删除数据
	 * 
	 * @param courseId
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@ResponseBody
	public BackDataJson login(@RequestParam("writerId") String writerId, @RequestParam("id") List<String> id) {

		BackDataJson backDataJson = new BackDataJson();
		try {
			// backDataJson.setBackData(loginService.login(username, password));
			backDataJson.setSuccess(true);
			backDataJson.setBackMsg("登录成功");

		} catch (SchoolException e) {
			backDataJson.setSuccess(false);
			backDataJson.setBackMsg("登录失败" + e.getMessage());

		}
		return backDataJson;

	}

	/**
	 * 删除数据
	 * 
	 * @param courseId
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@ResponseBody
	public BackDataJson login(@RequestParam("writerId") String writerId, @RequestParam("id") List<String> id) {

		BackDataJson backDataJson = new BackDataJson();
		try {
			// backDataJson.setBackData(loginService.login(username, password));
			backDataJson.setSuccess(true);
			backDataJson.setBackMsg("登录成功");

		} catch (SchoolException e) {
			backDataJson.setSuccess(false);
			backDataJson.setBackMsg("登录失败" + e.getMessage());

		}
		return backDataJson;

	}

	/**
	 * 删除数据
	 * 
	 * @param courseId
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@ResponseBody
	public BackDataJson login(@RequestParam("writerId") String writerId, @RequestParam("id") List<String> id) {

		BackDataJson backDataJson = new BackDataJson();
		try {
			// backDataJson.setBackData(loginService.login(username, password));
			backDataJson.setSuccess(true);
			backDataJson.setBackMsg("登录成功");

		} catch (SchoolException e) {
			backDataJson.setSuccess(false);
			backDataJson.setBackMsg("登录失败" + e.getMessage());

		}
		return backDataJson;

	}

}

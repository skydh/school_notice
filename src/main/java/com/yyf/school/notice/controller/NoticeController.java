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
	private NoticeService noticeService;

	/**
	 * 查看可看到的公告信息 。审批按钮的可见性，发布管理博客的按钮可见性。
	 * 
	 * @param courseId
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/showListAll", method = RequestMethod.GET)
	@ResponseBody
	public BackDataJson showListAll() {

		BackDataJson backDataJson = new BackDataJson();
		try {
			backDataJson.setBackData(noticeService.showListAll());
			backDataJson.setSuccess(true);
			backDataJson.setBackMsg("查看成功");

		} catch (SchoolException e) {
			backDataJson.setSuccess(false);
			backDataJson.setBackMsg("查看失败" + e.getMessage());

		}
		return backDataJson;

	}

	/**
	 * 新增数据 从上下文获取到用户id,查看公告信息,这边不考虑分页
	 * 
	 * @param courseId
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/showList")
	@ResponseBody
	public BackDataJson showList() {

		BackDataJson backDataJson = new BackDataJson();
		try {
			backDataJson.setBackData(noticeService.showList());
			backDataJson.setSuccess(true);
			backDataJson.setBackMsg("查看成功");

		} catch (SchoolException e) {
			backDataJson.setSuccess(false);
			backDataJson.setBackMsg("查看失败" + e.getMessage());

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
	public BackDataJson delete(@RequestParam("ids") List<String> ids) {

		BackDataJson backDataJson = new BackDataJson();
		try {
			noticeService.delete(ids);
			backDataJson.setBackData(noticeService.showList());
			backDataJson.setSuccess(true);
			backDataJson.setBackMsg("删除成功");

		} catch (SchoolException e) {
			backDataJson.setSuccess(false);
			backDataJson.setBackMsg("删除失败" + e.getMessage());

		}
		return backDataJson;

	}

	/**
	 * 新增数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/insert")
	@ResponseBody
	public BackDataJson insert(@RequestParam("title") String title, @RequestParam("contents") String contents) {

		BackDataJson backDataJson = new BackDataJson();
		try {
			NoticeVO noticeVO = new NoticeVO();
			noticeVO.setTitle(title);
			noticeVO.setContents(contents);
			noticeService.save(noticeVO);
			backDataJson.setBackData(noticeService.showList());
			backDataJson.setSuccess(true);
			backDataJson.setBackMsg("保存成功");

		} catch (SchoolException e) {
			backDataJson.setSuccess(false);
			backDataJson.setBackMsg("保存失败" + e.getMessage());

		}
		return backDataJson;

	}

	/**
	 * 更新数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public BackDataJson update(@RequestParam("title") String title, @RequestParam("contents") String contents,
			@RequestParam("id") String id) {

		BackDataJson backDataJson = new BackDataJson();
		try {
			NoticeVO noticeVO = new NoticeVO();
			noticeVO.setTitle(title);
			noticeVO.setContents(contents);
			noticeVO.setId(id);
			noticeService.update(noticeVO);
			backDataJson.setBackData(noticeService.showList());
			backDataJson.setSuccess(true);
			backDataJson.setBackMsg("更新成功");

		} catch (SchoolException e) {
			backDataJson.setSuccess(false);
			backDataJson.setBackMsg("更新失败" + e.getMessage());

		}
		return backDataJson;

	}

	/**
	 * 更新数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/queryDetail", method = RequestMethod.GET)
	@ResponseBody
	public BackDataJson queryDetail(@RequestParam("id") String id) {

		BackDataJson backDataJson = new BackDataJson();
		try {
			backDataJson.setBackData(noticeService.queryDetail(id));
			backDataJson.setSuccess(true);
			backDataJson.setBackMsg("查询成功");
		} catch (SchoolException e) {
			backDataJson.setSuccess(false);
			backDataJson.setBackMsg("查询失败" + e.getMessage());

		}
		return backDataJson;

	}

}

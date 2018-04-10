package com.yyf.school.notice.dao;

import java.util.List;

import com.yyf.school.notice.vo.NoticeVO;

public interface NoticeDao {

	List<NoticeVO> showListAll(List<String> ids);

	List<NoticeVO> showList(String id);

	void delete(List<String> id);

	void insert(NoticeVO noticeVO);

	void update(NoticeVO noticeVO);

	Integer findHotById(String id);

	NoticeVO findById(String id);

	void insertHot(String id);

	void updateHot(String id);

}

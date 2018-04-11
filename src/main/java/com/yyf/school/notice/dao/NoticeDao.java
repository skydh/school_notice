package com.yyf.school.notice.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yyf.school.notice.vo.NoticeVO;

public interface NoticeDao {

	List<NoticeVO> showListAll(List<String> ids);

	List<NoticeVO> showList(String id);

	void delete(List<String> ids);

	void insert(NoticeVO noticeVO);

	void update(NoticeVO noticeVO);

	Integer findHotById(@Param("id") String id);

	NoticeVO findById(String id);

	void insertHot(@Param("id") String id);

	void updateHot(String id);

}

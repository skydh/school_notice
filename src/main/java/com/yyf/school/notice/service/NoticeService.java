package com.yyf.school.notice.service;

import java.util.List;

import com.yyf.school.notice.vo.AggVO;
import com.yyf.school.notice.vo.NoticeVO;
import com.yyf.school.utils.exception.SchoolException;

public interface NoticeService {

	void save(NoticeVO noticeVO) throws SchoolException;

	void update(NoticeVO noticeVO) throws SchoolException;

	void delete(List<String> list) throws SchoolException;

	List<NoticeVO> showList() throws SchoolException;

	AggVO showListAll() throws SchoolException;

	NoticeVO queryDetail(String id) throws SchoolException;

}

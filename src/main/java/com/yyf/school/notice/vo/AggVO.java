package com.yyf.school.notice.vo;

import java.util.List;

public class AggVO {

	private List<NoticeVO> list;

	private Boolean approveMenu;

	public List<NoticeVO> getList() {
		return list;
	}

	public void setList(List<NoticeVO> list) {
		this.list = list;
	}

	public Boolean getApproveMenu() {
		return approveMenu;
	}

	public void setApproveMenu(Boolean approveMenu) {
		this.approveMenu = approveMenu;
	}

}

package com.yyf.school.notice.vo;

import java.io.Serializable;
import java.util.List;

public class AggVO implements Serializable {

	private static final long serialVersionUID = 1298973198698124734L;

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

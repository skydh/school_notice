package com.yyf.school.login.vo;

import java.io.Serializable;
import java.util.List;

public class DoApproveVO implements Serializable {

	private static final long serialVersionUID = -6837952776539937951L;
	private List<String> id;
	private Boolean isApprove;

	public List<String> getId() {
		return id;
	}

	public void setId(List<String> id) {
		this.id = id;
	}

	public Boolean getIsApprove() {
		return isApprove;
	}

	public void setIsApprove(Boolean isApprove) {
		this.isApprove = isApprove;
	}

}

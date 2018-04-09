package com.yyf.school.login.vo;

import java.io.Serializable;
import java.util.List;

public class DoApproveVO implements Serializable {

	private static final long serialVersionUID = -6837952776539937951L;
	private List<String> oId;
	private List<String> nId;

	public List<String> getoId() {
		return oId;
	}

	public void setoId(List<String> oId) {
		this.oId = oId;
	}

	public List<String> getnId() {
		return nId;
	}

	public void setnId(List<String> nId) {
		this.nId = nId;
	}

}

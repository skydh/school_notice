package com.yyf.school.login.vo;

import java.io.Serializable;

public class UserVO implements Serializable {

	private static final long serialVersionUID = 6117793149203245603L;
	private String id;
	private String username;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}

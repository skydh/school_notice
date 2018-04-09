package com.yyf.school.utils.exception;

/**
 * 这里封装统一的异常处理类
 * 
 * @author yyf
 *
 */
public class SchoolException extends RuntimeException {

	private static final long serialVersionUID = 3613072238231936125L;

	public SchoolException(String msg) {
		super(msg);
	}

	public SchoolException(Throwable e) {
		super(e);
	}

	public SchoolException(String msg, Throwable e) {
		super(msg, e);
	}

}

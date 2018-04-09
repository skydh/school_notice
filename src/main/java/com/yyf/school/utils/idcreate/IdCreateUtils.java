package com.yyf.school.utils.idcreate;

import java.util.Date;

/**
 * 创建生成唯一id
 * 
 * @author yyf
 *
 */
public class IdCreateUtils {

	public static String getId() {
		Date date = new Date();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			sb.append((char) (int) (Math.random() * 26 + 97));
		}
		sb.append(date.getTime());
		return sb.toString();

	}

}

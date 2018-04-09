package com.yyf.school.shiro.codec;

import java.util.Date;
import java.util.HashMap;

import org.junit.Test;

/**
 * 测试token生成器。
 * 
 * @author dh
 *
 */
public class JunitTest {

	@Test
	public void test() {
		HmacSHA256Utils a = new HmacSHA256Utils();
		String key = "dadadswdewq2ewdwqdwadsadasd";
		HashMap<String, String> hashmap = new HashMap<String, String>();
		hashmap.put("username", "dh");
		hashmap.put("case", "1");
		System.out.println(a.digest(key, hashmap));
		Date date=new Date();
	Long loq1=date.getTime();
	Long loq2=loq1+120;
	System.out.println();

	}

}

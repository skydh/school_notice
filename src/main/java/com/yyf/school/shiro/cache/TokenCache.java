package com.yyf.school.shiro.cache;

import java.util.Date;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;

import com.yyf.school.utils.constant.Constants;

/**
 * 这个是存放token值的，token，long（时间戳） 为其键值对 加一个原子变量，每100次清除错误信息
 * 
 * @author yyf
 *
 */
@Component
public class TokenCache {

	private ConcurrentHashMap<String, Long> tokenDate = new ConcurrentHashMap<String, Long>();

	private AtomicInteger atomicInteger = new AtomicInteger(0);

	public Long getTokenDate(String token) {
		return tokenDate.get(token);
	}

	public void setAndUpdateTokenDate(String token, Long Date) {
		tokenDate.put(token, Date);
	}

	public void autoIncrements() {
		atomicInteger.incrementAndGet();

	}

	public int getAtomicInteger() {
		return atomicInteger.get();
	}

	/***
	 * 去除过期时间数据
	 */
	public void clearData() {
		Long date = new Date().getTime();
		for (Entry<String, Long> entry : tokenDate.entrySet()) {
			long value = entry.getValue();
			if ((date - value) > Constants.offerDate) {
				tokenDate.remove(entry.getKey());
			}
		}

	}

	public void logout(String token) {

		tokenDate.remove(token);

	}
}

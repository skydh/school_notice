package com.yyf.school.shiro.cache;

import java.util.Date;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.yyf.school.utils.constant.Constants;

/**
 * 这个是存放token值的，token，long（时间戳） 为其键值对 加一个原子变量，每100次清除错误信息
 * 
 * @author yyf
 *
 */
@Service
public class TokenCache {
	public TokenCache() {
		System.out.println("初始化了111");
	}

	private static ConcurrentHashMap<String, Long> tokenDate = new ConcurrentHashMap<String, Long>();

	private static AtomicInteger atomicInteger = new AtomicInteger(0);

	public Long getTokenDate(String token) {
		return tokenDate.get(token);
	}

	public void setAndUpdateTokenDate(String token, Long Date) {
		tokenDate.put(token, Date);
	}

	public boolean isExistToken(String token) {

		return tokenDate.containsKey(token);
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
		Long date = new Date().getTime();//获取当前时间
		for (Entry<String, Long> entry : tokenDate.entrySet()) {//循环遍历缓存数据
			long value = entry.getValue();//取出缓存数据时间
			if ((date - value) > Constants.offerDate) {//判断当前时间减去缓存时间是否大于一个小时，如果大于，则清除数据
				tokenDate.remove(entry.getKey());
			}
		}

	}

	public void logout(String token) {

		tokenDate.remove(token);

	}
}

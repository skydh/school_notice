package com.yyf.school.utils.backdata;

/**
 * 为了格式统一和数据验证，这里采用统一的数据结构，方便维护
 * 考虑到id生成问题的经常调用，将其加入到spring容器中
 * 
 * @author yyf
 *
 */
public class BackDataJson {
	private boolean success;
	private String backMsg;
	private Object backData;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getBackMsg() {
		return backMsg;
	}

	public void setBackMsg(String backMsg) {
		this.backMsg = backMsg;
	}

	public Object getBackData() {
		return backData;
	}

	public void setBackData(Object backData) {
		this.backData = backData;
	}

}

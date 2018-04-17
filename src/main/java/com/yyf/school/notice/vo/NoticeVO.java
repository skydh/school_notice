package com.yyf.school.notice.vo;

import java.io.Serializable;
import java.util.Date;

public class NoticeVO implements Serializable {
	
	private static final long serialVersionUID = 7786086967119955325L;
	private String id;
	private String contents;
	private Date writeDate;
	private String writer;
	private String writerId;
	private String title;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "NoticeVO [id=" + id + ", contents=" + contents + ", writeDate=" + writeDate + ", writer=" + writer
				+ ", writerId=" + writerId + ", title=" + title + "]";
	}

}

package com.yyf.school.login.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 完善信息
 * 
 * @author yyf
 *
 */
public class AllRoleVO implements Serializable {

	private static final long serialVersionUID = -7795345425484619247L;
	private String id;
	private String pId;
	private String schoolCode;
	private String collegeCode;
	private String gradeCode;
	private String classCode;
	private Integer caseSchool;
	private String contend;
	private String userName;
	private Integer isAllow;
	private List<AllRoleVO> list;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getSchoolCode() {
		return schoolCode;
	}

	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}

	public String getCollegeCode() {
		return collegeCode;
	}

	public void setCollegeCode(String collegeCode) {
		this.collegeCode = collegeCode;
	}

	public String getGradeCode() {
		return gradeCode;
	}

	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	

	public Integer getCaseSchool() {
		return caseSchool;
	}

	public void setCaseSchool(Integer caseSchool) {
		this.caseSchool = caseSchool;
	}

	public String getContend() {
		return contend;
	}

	public void setContend(String contend) {
		this.contend = contend;
	}

	@Override
	public String toString() {
		return "AllRoleVO [id=" + id + ", pId=" + pId + ", schoolCode=" + schoolCode + ", collegeCode=" + collegeCode
				+ ", gradeCode=" + gradeCode + ", classCode=" + classCode + ", caseSchool=" + caseSchool + ", contend="
				+ contend + "]";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<AllRoleVO> getList() {
		return list;
	}

	public void setList(List<AllRoleVO> list) {
		this.list = list;
	}

	public Integer getIsAllow() {
		return isAllow;
	}

	public void setIsAllow(Integer isAllow) {
		this.isAllow = isAllow;
	}



}

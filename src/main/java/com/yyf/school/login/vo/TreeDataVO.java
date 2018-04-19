package com.yyf.school.login.vo;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class TreeDataVO implements Serializable {
	private String label;
	private String value;
	private List<TreeDataVO> children;
	private String schoolCode;
	private String collegeCode;
	private String gradeCode;
	private String classCode;
	private Integer caseSchool;

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

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<TreeDataVO> getChildren() {
		return children;
	}

	public void setChildren(List<TreeDataVO> children) {
		this.children = children;
	}

}

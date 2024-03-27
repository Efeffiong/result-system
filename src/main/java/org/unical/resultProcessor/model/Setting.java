package org.unical.resultProcessor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Setting {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int settingId;
	
	@Column(nullable=false)
	private int noOfFirstElectiveCourses;
	
	@Column(nullable=false)
	private int noOfSecondElectiveCourses;
	
	@Column(nullable=false)
	private String dept;
	
	@Column(nullable=false)
	private String session;
	
	@Column(nullable=false)
	private String courseType;
	
	public Setting() {
		
	}

	public Setting(int noOfFirstElectiveCourses, int noOfSecondElectiveCourses, String dept, String session,
			String courseType) {
		super();
		this.noOfFirstElectiveCourses = noOfFirstElectiveCourses;
		this.noOfSecondElectiveCourses = noOfSecondElectiveCourses;
		this.dept = dept;
		this.session = session;
		this.courseType = courseType;
	}

	public int getSettingId() {
		return settingId;
	}

	public void setSettingId(int settingId) {
		this.settingId = settingId;
	}

	public int getNoOfFirstElectiveCourses() {
		return noOfFirstElectiveCourses;
	}

	public void setNoOfFirstElectiveCourses(int noOfFirstElectiveCourses) {
		this.noOfFirstElectiveCourses = noOfFirstElectiveCourses;
	}

	public int getNoOfSecondElectiveCourses() {
		return noOfSecondElectiveCourses;
	}

	public void setNoOfSecondElectiveCourses(int noOfSecondElectiveCourses) {
		this.noOfSecondElectiveCourses = noOfSecondElectiveCourses;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	@Override
	public String toString() {
		return "Setting [settingId=" + settingId + ", noOfFirstElectiveCourses=" + noOfFirstElectiveCourses
				+ ", noOfSecondElectiveCourses=" + noOfSecondElectiveCourses + ", dept=" + dept + ", session=" + session
				+ ", courseType=" + courseType + "]";
	}

	
}

package org.unical.resultProcessor.model;


public class Identification {

	private String surname;
	private String firstname;
	private String middlename;
	private String sex;
	private String faculty;
	private String dept;
	private String unit;
	private String session;
	private String courseType;
	private String matricNo;
	
	public Identification() {
	
	}

	public Identification(String surname, String firstname, String middlename, String sex, String faculty, String dept,
			String unit, String session, String courseType, String matricNo) {
		super();
		this.surname = surname;
		this.firstname = firstname;
		this.middlename = middlename;
		this.sex = sex;
		this.faculty = faculty;
		this.dept = dept;
		this.unit = unit;
		this.session = session;
		this.courseType = courseType;
		this.matricNo = matricNo;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
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

	public String getMatricNo() {
		return matricNo;
	}

	public void setMatricNo(String matricNo) {
		this.matricNo = matricNo;
	}

	@Override
	public String toString() {
		return "Identification [surname=" + surname + ", firstname=" + firstname + ", middlename=" + middlename
				+ ", sex=" + sex + ", faculty=" + faculty + ", dept=" + dept + ", unit=" + unit + ", session=" + session
				+ ", courseType=" + courseType + ", matricNo=" + matricNo + "]";
	}
	
		
}

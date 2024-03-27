package org.unical.resultProcessor.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Student {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int studentId;
	
	@NotBlank(message="Surname is mandatory")
	private String surname;
	
	@NotBlank(message="Firstname is mandatory")
	private String firstname;
	
	@NotBlank(message="Middlename is mandatory")
	private String middlename;
	
	@NotBlank(message="Matric Number is mandatory")
	private String matricNo;
	
	@NotBlank(message="Sex is mandatory")
	private String sex;
	
	@NotBlank(message="department is mandatory")
	private String department;

	@NotBlank(message="session is mandatory")
	private String session;

	
	private LocalDate registrationDate;
	
	private String courseType;

	public Student() {
		super();
	}

	public Student(String surname, String firstname, String middlename, String matricNo,
			String sex, String department, String session, LocalDate registrationDate, String courseType) {
		super();
		this.surname = surname;
		this.firstname = firstname;
		this.middlename = middlename;
		this.matricNo = matricNo;
		this.sex = sex;
		this.department = department;
		this.session = session;
		this.registrationDate = registrationDate;
		this.courseType = courseType;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
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

	public String getMatricNo() {
		return matricNo;
	}

	public void setMatricNo(String matricNo) {
		this.matricNo = matricNo;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}

	
	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", surname=" + surname + ", firstname=" + firstname + ", middlename="
				+ middlename + ", matricNo=" + matricNo + ", sex=" + sex + ", department=" + department + ", session="
				+ session + ", registrationDate=" + registrationDate + ", courseType=" + courseType + "]";
	}

	
}

package org.unical.resultProcessor.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class User {

	@Id
	private Long userId;
	
	@NotBlank(message="Title is mandatory")
	private String title;
	
	@NotBlank(message="User Type is mandatory")
	private String userType;
	
	@NotBlank(message="surname is mandatory")
	private String surname;
	
	@NotBlank(message="first name is mandatory")
	private String firstname;
	
	@NotBlank(message="Other name is mandatory")
	private String othername;
	
	@NotBlank(message="Position is mandatory")
	private String position;
	
	@NotBlank(message="Session is mandatory")
	private String session;
	
	@NotBlank(message="Identity number is mandatory")
	private String regNum;
	
	@NotBlank(message="Sex is mandatory")
	private String sex;
	
	@NotBlank(message="Username is mandatory")
	private String username;
	
	@NotBlank(message="Password is mandatory")
	private String password;
	
	
	public User() {
		super();
	}


	public User(Long userId, String title, String userType, String surname, String firstname, String othername, String position,
			String session, String regNum, String sex, String username, String password) {
		super();
		this.userId = userId;
		this.title = title;
		this.userType = userType;
		this.surname = surname;
		this.firstname = firstname;
		this.othername = othername;
		this.position = position;
		this.session = session;
		this.regNum = regNum;
		this.sex = sex;
		this.username = username;
		this.password = password;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getUserType() {
		return userType;
	}


	public void setUserType(String userType) {
		this.userType = userType;
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


	public String getOthername() {
		return othername;
	}


	public void setOthername(String othername) {
		this.othername = othername;
	}


	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}


	public String getSession() {
		return session;
	}


	public void setSession(String session) {
		this.session = session;
	}


	public String getRegNum() {
		return regNum;
	}


	public void setRegNum(String regNum) {
		this.regNum = regNum;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "User [userId=" + userId + ", title=" + title + ", userType=" + userType + ", surname=" + surname
				+ ", firstname=" + firstname + ", othername=" + othername + ", position=" + position + ", session="
				+ session + ", regNum=" + regNum + ", sex=" + sex + ", username=" + username + ", password=" + password + "]";
	}
	
	
	
}

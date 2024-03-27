package org.unical.resultProcessor.model;

public class ResultsDisplayer {

	private int serialNumber;
	private String surname;
	private String firstname;
	private String othername;
	private String matricNo;
	private double firstSemester;
	private double secondSemester;
	private double research;
	private double cgpa;
	private String remark;
	private int studentId;
	
	public ResultsDisplayer() {
		
	}

	public ResultsDisplayer(int serialNumber, String surname, String firstname, String othername, String matricNo,
			double firstSemester, double secondSemester, double research, double cgpa, String remark, int studentId) {
		super();
		this.serialNumber = serialNumber;
		this.surname = surname;
		this.firstname = firstname;
		this.othername = othername;
		this.matricNo = matricNo;
		this.firstSemester = firstSemester;
		this.secondSemester = secondSemester;
		this.research = research;
		this.cgpa = cgpa;
		this.remark = remark;
		this.studentId = studentId;
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
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

	public String getMatricNo() {
		return matricNo;
	}

	public void setMatricNo(String matricNo) {
		this.matricNo = matricNo;
	}

	public double getFirstSemester() {
		return firstSemester;
	}

	public void setFirstSemester(double firstSemester) {
		this.firstSemester = firstSemester;
	}

	public double getSecondSemester() {
		return secondSemester;
	}

	public void setSecondSemester(double secondSemester) {
		this.secondSemester = secondSemester;
	}

	public double getResearch() {
		return research;
	}

	public void setResearch(double research) {
		this.research = research;
	}

	public double getCgpa() {
		return cgpa;
	}

	public void setCgpa(double cgpa) {
		this.cgpa = cgpa;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getStudentId() {
		return studentId;
	}
	
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	@Override
	public String toString() {
		return "ResultsDisplayer [serialNumber=" + serialNumber + ", surname=" + surname + ", firstname=" + firstname
				+ ", othername=" + othername + ", matricNo=" + matricNo + ", firstSemester=" + firstSemester
				+ ", secondSemester=" + secondSemester + ", research=" + research + ", cgpa=" + cgpa + ", remark="
				+ remark + ", studentId=" + studentId + "]";
	}
	
}

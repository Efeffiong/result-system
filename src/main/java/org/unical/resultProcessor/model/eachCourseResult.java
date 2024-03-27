package org.unical.resultProcessor.model;

public class eachCourseResult {

	private int serialNo;
	private String courseCode;
	private String courseTitle;
	private double asses;
	private double exam;
	private double total;
	private String grade;
	
	public eachCourseResult() {
		
	}
	public eachCourseResult(int serialNo, String courseCode, String courseTitle, double asses, double exam,
			double total, String grade) {
		super();
		this.serialNo = serialNo;
		this.courseCode = courseCode;
		this.courseTitle = courseTitle;
		this.asses = asses;
		this.exam = exam;
		this.total = total;
		this.grade = grade;
		
	}
	public int getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getCourseTitle() {
		return courseTitle;
	}
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}
	public double getAsses() {
		return asses;
	}
	public void setAsses(double asses) {
		this.asses = asses;
	}
	public double getExam() {
		return exam;
	}
	public void setExam(double exam) {
		this.exam = exam;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	@Override
	public String toString() {
		return "eachCourseResult [serialNo=" + serialNo + ", courseCode=" + courseCode + ", courseTitle=" + courseTitle
				+ ", asses=" + asses + ", exam=" + exam + ", total=" + total + ", grade=" + grade + ", totalCreditUnit="
				+ "]";
	}
	
}

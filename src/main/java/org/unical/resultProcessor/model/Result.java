package org.unical.resultProcessor.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Result {

	@Id
	private Long resultId;
	
	private double asses;
	
	private double exam;
	
	private Long courseId;
	
	private String unit;
	
	private String matricNo;
	
	

	public Result() {}
	
	public Result(Long resultId, double asses, double exam, Long courseId, String unit, String matricNo) {
		super();
		this.resultId = resultId;
		this.asses = asses;
		this.exam = exam;
		this.courseId = courseId;
		this.unit = unit;
		this.matricNo = matricNo;
	}
	public Long getResultId() {
		return resultId;
	}
	public void setResultId(Long resultId) {
		this.resultId = resultId;
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
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getMatricNo() {
		return matricNo;
	}

	public void setMatricNo(String matricNo) {
		this.matricNo = matricNo;
	}

	@Override
	public String toString() {
		return "Result [resultId=" + resultId + ", asses=" + asses + ", exam=" + exam + ", courseId=" + courseId
				+ ", unit=" + unit + ", matricNo=" + matricNo + "]";
	}
	

}

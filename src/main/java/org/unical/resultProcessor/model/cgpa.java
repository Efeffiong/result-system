package org.unical.resultProcessor.model;

public class cgpa {

	private double firstGp;
	private double secondGp;
	private double projectGp;
	private double cgpa;
	private String classed;
	public cgpa() {
		
	}
	public cgpa(double firstGp, double secondGp, double projectGp, double cgpa, String classed) {
		super();
		this.firstGp = firstGp;
		this.secondGp = secondGp;
		this.projectGp = projectGp;
		this.cgpa = cgpa;
		this.classed = classed;
	}
	public double getFirstGp() {
		return firstGp;
	}
	public void setFirstGp(double firstGp) {
		this.firstGp = firstGp;
	}
	public double getSecondGp() {
		return secondGp;
	}
	public void setSecondGp(double secondGp) {
		this.secondGp = secondGp;
	}
	public double getProjectGp() {
		return projectGp;
	}
	public void setProjectGp(double projectGp) {
		this.projectGp = projectGp;
	}
	public double getCgpa() {
		return cgpa;
	}
	public void setCgpa(double cgpa) {
		this.cgpa = cgpa;
	}
	public String getClassed() {
		return classed;
	}
	public void setClassed(String classed) {
		this.classed = classed;
	}
	@Override
	public String toString() {
		return "cgpa [firstGp=" + firstGp + ", secondGp=" + secondGp + ", projectGp=" + projectGp + ", cgpa=" + cgpa
				+ ", classed=" + classed + "]";
	}
	
	
}

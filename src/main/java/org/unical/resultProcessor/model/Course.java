package org.unical.resultProcessor.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Course {

	@Id
	private Long courseId;
	
	@NotBlank(message="Course Title is mandatory")
	private String courseTitle;
	
	@NotBlank(message="Course Code is mandatory")
	private String courseCode;
	
	@NotBlank(message="Semester is mandatory")
	private String semester;
	
	private int level;
	
	private int creditUnit;
	
	@NotBlank(message="Course Session is mandatory")
	private String courseSession;
	
	@NotBlank(message="Course Type is mandatory")
	private String courseType;
	
	@NotBlank(message="Department is mandatory")
	private String department;
	
	@NotBlank(message="Course Allocation is mandatory")
	private String courseAllocation;
	
	public Course() {}

	public Course(Long courseId, String courseTitle, String courseCode, String semester, int level, int creditUnit,
			String courseSession, String courseType, String department, String courseAllocation) {
		super();
		this.courseId = courseId;
		this.courseTitle = courseTitle;
		this.courseCode = courseCode;
		this.semester = semester;
		this.level = level;
		this.creditUnit = creditUnit;
		this.courseSession = courseSession;
		this.courseType = courseType;
		this.department = department;
		this.courseAllocation = courseAllocation;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getCreditUnit() {
		return creditUnit;
	}

	public void setCreditUnit(int creditUnit) {
		this.creditUnit = creditUnit;
	}

	public String getCourseSession() {
		return courseSession;
	}

	public void setCourseSession(String courseSession) {
		this.courseSession = courseSession;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getCourseAllocation() {
		return courseAllocation;
	}

	public void setCourseAllocation(String courseAllocation) {
		this.courseAllocation = courseAllocation;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseTitle=" + courseTitle + ", courseCode=" + courseCode
				+ ", semester=" + semester + ", level=" + level + ", creditUnit=" + creditUnit + ", courseSession="
				+ courseSession + ", courseType=" + courseType + ", department=" + department + ", courseAllocation="
				+ courseAllocation + "]";
	}

	
	
	
}

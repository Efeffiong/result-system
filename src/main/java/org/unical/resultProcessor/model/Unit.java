package org.unical.resultProcessor.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Unit {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int unitId;
	
	@NotBlank(message="faculty is mandatory")
	private String faculty;
	
	@NotBlank(message="department is mandatory")
	private String department;
	
	@NotBlank(message="unit is mandatory")
	private String unitDept;

	public Unit() {}

	public Unit(String faculty, String department, String unitDept) {
		super();
		this.faculty = faculty;
		this.department = department;
		this.unitDept = unitDept;
	}

	public int getUnitId() {
		return unitId;
	}

	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getUnitDept() {
		return unitDept;
	}

	public void setUnitDept(String unitDept) {
		this.unitDept = unitDept;
	}

	@Override
	public String toString() {
		return "unit [unitId=" + unitId + ", faculty=" + faculty + ", department=" + department + ", unitDept=" + unitDept
				+ "]";
	}
	
	
}

package org.unical.resultProcessor.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Classes {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int classId;
	
	@NotBlank(message="Class is mandatory")
	private String classed;
	
	@NotBlank(message="Minimum point is mandatory")
	private double minPoint;
	
	@NotBlank(message="Maximum is mandatory")
	private double maxPoint;
	
	private int unitId;
	
	public Classes() {}

	public Classes(String classed, double minPoint, double maxPoint, int unitId) {
		super();
		this.classed = classed;
		this.minPoint = minPoint;
		this.maxPoint = maxPoint;
		this.unitId = unitId;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getClassed() {
		return classed;
	}

	public void setClassed(String classed) {
		this.classed = classed;
	}

	public double getMinPoint() {
		return minPoint;
	}

	public void setMinPoint(double minPoint) {
		this.minPoint = minPoint;
	}

	public double getMaxPoint() {
		return maxPoint;
	}

	public void setMaxPoint(double maxPoint) {
		this.maxPoint = maxPoint;
	}

	public int getUnitId() {
		return unitId;
	}

	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}

	@Override
	public String toString() {
		return "classes [classId=" + classId + ", classed=" + classed + ", minPoint=" + minPoint + ", maxPoint="
				+ maxPoint + ", unitId=" + unitId + "]";
	}
	
	
}

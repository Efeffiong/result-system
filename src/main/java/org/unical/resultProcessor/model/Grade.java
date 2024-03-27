package org.unical.resultProcessor.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Grade {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int gradeId;
	private String grade;
	private int minScore;
	private int maxScore;
	private int gradePoint;
	
	public Grade() {}

	public Grade(String grade, int minScore, int maxScore, int gradePoint) {
		super();
		this.grade = grade;
		this.minScore = minScore;
		this.maxScore = maxScore;
		this.gradePoint = gradePoint;
	}

	public int getGradeId() {
		return gradeId;
	}

	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public int getMinScore() {
		return minScore;
	}

	public void setMinScore(int minScore) {
		this.minScore = minScore;
	}

	public int getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}

	public int getGradePoint() {
		return gradePoint;
	}

	public void setGradePoint(int gradePoint) {
		this.gradePoint = gradePoint;
	}

	@Override
	public String toString() {
		return "grade [gradeId=" + gradeId + ", grade=" + grade + ", minScore=" + minScore + ", maxScore=" + maxScore
				+ ", gradePoint=" + gradePoint + "]";
	}
	
	
}

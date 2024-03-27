package org.unical.resultProcessor.model;

import java.time.LocalDate;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;

@Entity
public class LecturerResult {

	@Id
	private Long lecturerResultId;
	
	private Long courseId;
	
	@Lob
	private byte[] resultFile;
	
	private String fileName;
	
	private String fileType;
	
	@NotBlank(message="Status is mandatory")
	private String status;
	
	@NotBlank(message="Lecturer Surname is mandatory")
	private String lecturerSurname;
	
	@NotBlank(message="Lecturer Firstname is mandatory")
	private String lecturerFirstname;
	
	@NotBlank(message="Lecturer Middlename is mandatory")
	private String lecturerMiddlename;
	
	private LocalDate resultUploadDate;
	
	private LocalDate accessedDate;

	public LecturerResult() {
		super();
	}

	public LecturerResult(Long lecturerResultId, Long courseId, byte[] resultFile, String fileName, String fileType,
			@NotBlank(message = "Status is mandatory") String status,
			@NotBlank(message = "Lecturer Surname is mandatory") String lecturerSurname,
			@NotBlank(message = "Lecturer Firstname is mandatory") String lecturerFirstname,
			@NotBlank(message = "Lecturer Middlename is mandatory") String lecturerMiddlename,
			LocalDate resultUploadDate, LocalDate accessedDate) {
		super();
		this.lecturerResultId = lecturerResultId;
		this.courseId = courseId;
		this.resultFile = resultFile;
		this.fileName = fileName;
		this.fileType = fileType;
		this.status = status;
		this.lecturerSurname = lecturerSurname;
		this.lecturerFirstname = lecturerFirstname;
		this.lecturerMiddlename = lecturerMiddlename;
		this.resultUploadDate = resultUploadDate;
		this.accessedDate = accessedDate;
	}

	public Long getLecturerResultId() {
		return lecturerResultId;
	}

	public void setLecturerResultId(Long lecturerResultId) {
		this.lecturerResultId = lecturerResultId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public byte[] getResultFile() {
		return resultFile;
	}

	public void setResultFile(byte[] resultFile) {
		this.resultFile = resultFile;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLecturerSurname() {
		return lecturerSurname;
	}

	public void setLecturerSurname(String lecturerSurname) {
		this.lecturerSurname = lecturerSurname;
	}

	public String getLecturerFirstname() {
		return lecturerFirstname;
	}

	public void setLecturerFirstname(String lecturerFirstname) {
		this.lecturerFirstname = lecturerFirstname;
	}

	public String getLecturerMiddlename() {
		return lecturerMiddlename;
	}

	public void setLecturerMiddlename(String lecturerMiddlename) {
		this.lecturerMiddlename = lecturerMiddlename;
	}

	public LocalDate getResultUploadDate() {
		return resultUploadDate;
	}

	public void setResultUploadDate(LocalDate resultUploadDate) {
		this.resultUploadDate = resultUploadDate;
	}

	public LocalDate getAccessedDate() {
		return accessedDate;
	}

	public void setAccessedDate(LocalDate accessedDate) {
		this.accessedDate = accessedDate;
	}

	@Override
	public String toString() {
		return "LecturerResult [lecturerResultId=" + lecturerResultId + ", courseId=" + courseId + ", resultFile="
				+ Arrays.toString(resultFile) + ", fileName=" + fileName + ", fileType=" + fileType + ", status="
				+ status + ", lecturerSurname=" + lecturerSurname + ", lecturerFirstname=" + lecturerFirstname
				+ ", lecturerMiddlename=" + lecturerMiddlename + ", resultUploadDate=" + resultUploadDate
				+ ", accessedDate=" + accessedDate + "]";
	}

	

	
}

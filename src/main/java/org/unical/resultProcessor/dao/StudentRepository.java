package org.unical.resultProcessor.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.unical.resultProcessor.model.Student;

public interface StudentRepository extends CrudRepository<Student, Integer>{

	List<Student> findByDepartmentAndSession(String dept, String session);
	
	List<Student> findByDepartmentAndSessionAndCourseType(String dept, String session, String courseType);
	
	Student findByDepartmentAndSessionAndStudentId(String dept, String session, int studentId);
	
	Student findByDepartmentAndSessionAndMatricNo(String dept, String session, String matricNo);
	
	List <Student> findByDepartmentAndFirstnameAndSurnameAndMiddlenameAndSessionAndCourseTypeAndMatricNo(String dept, String firstname, String surname, String middlename, String session, String courseType, String matricNo);
}

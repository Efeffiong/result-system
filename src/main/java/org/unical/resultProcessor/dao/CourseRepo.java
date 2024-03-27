package org.unical.resultProcessor.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.unical.resultProcessor.model.Course;

public interface CourseRepo extends CrudRepository<Course, Long>{

	List<Course> findByDepartmentAndCourseSession(String department, String courseSession);
	
	List<Course> findByDepartmentAndCourseSessionAndSemesterAndCourseAllocation(String dept, String courseSession, String semester, String courseAllocation);
	
	List<Course> findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(String dept, String courseSession, String semester, String courseAllocation, String courseType);
	
	List<Course> findByCourseAllocationAndCourseCodeAndCourseSessionAndCourseTitleAndCourseTypeAndCreditUnitAndDepartmentAndLevelAndSemester(String courseAllocation, String courseCode, String session, String courseTitle, String courseType, int creditUnit, String dept, int level, String semester);
	
	List<Course> findByCourseAllocationAndCourseCodeAndCourseSessionAndCourseTypeAndDepartmentAndSemester(String courseAllocation, String courseCode, String session, String courseType, String dept, String semester);
}

package org.unical.resultProcessor.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unical.resultProcessor.dao.ClassesRepo;
import org.unical.resultProcessor.dao.CourseRepo;
import org.unical.resultProcessor.dao.GradeRepo;
import org.unical.resultProcessor.dao.ResultRepo;
import org.unical.resultProcessor.dao.SettingRepo;
import org.unical.resultProcessor.dao.StudentRepository;
import org.unical.resultProcessor.model.Classes;
import org.unical.resultProcessor.model.Course;
import org.unical.resultProcessor.model.Grade;
import org.unical.resultProcessor.model.Result;
import org.unical.resultProcessor.model.ResultsDisplayer;
import org.unical.resultProcessor.model.Setting;
import org.unical.resultProcessor.model.Student;
import org.unical.resultProcessor.model.cgpa;
import org.unical.resultProcessor.model.eachCourseResult;

@Service
public class ResultsGenerator {

	@Autowired
	CourseRepo courseRepo;
	
	@Autowired
	ResultRepo resultRepo;
	
	@Autowired
	ClassesRepo classesRepo;
	
	@Autowired
	StudentRepository studentRepo;
	
	@Autowired
	GradeRepo gradeRepo;
	
	@Autowired
	SettingRepo settingRepo;
	
	@Autowired
	ResultsGenerator resultsGenerator;
	
	public List<Course> electiveCourses(int noOfElectiveCourses, String matricNo, String semester, String session, String dept){
		String courseAllocation = "elective";
		int courseCounter = 0;
		List<Course> collectiveElectiveCourses = new ArrayList<>();
		
		List<Course> regElectiveCourses = courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocation(dept, session, semester, courseAllocation);
		for(Course regElectiveCourse : regElectiveCourses) {
			
			List<Result> electiveResults = resultRepo.findByCourseIdAndMatricNoAndUnit(regElectiveCourse.getCourseId(), matricNo, dept);
			
			if(courseCounter < noOfElectiveCourses) {
				
				for(Result result : electiveResults) {
					if(regElectiveCourse.getCourseId() == result.getCourseId()) {
						collectiveElectiveCourses.add(regElectiveCourse);
						courseCounter += 1;
						}
				}
				
			}
		}
		
		if(courseCounter == noOfElectiveCourses) {
			
			return collectiveElectiveCourses;
		} else{
			
			regElectiveCourses.removeAll(collectiveElectiveCourses);
			for(Course regElectiveCourse : regElectiveCourses) {
				if(courseCounter < noOfElectiveCourses) {
					collectiveElectiveCourses.add(regElectiveCourse);
					courseCounter += 1;
				}
			}
			
			return collectiveElectiveCourses;
			
		}
		
	}
	public List<Course> projectCourse(String matricNo, String semester, String session, String dept, String courseAllocation){
		List<Course> project = courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocation(dept, session, semester, courseAllocation);
		return project;
	}
	
	public double nanConfirmation(double a, double b) {
		double c;
		if(a == 0.0 && b == 0.0) {
			return 0.0;
		} else if(b == 0.0 || a == 0.0) {
			return 0.0;
		}else {
			c = a/b;
			return c;
		}
	}
	
	public String remark(List<String> repeatCourses, double cgpa) {
		String remarked = null;
		if(repeatCourses.size() == 0) {
			List<Classes> classes = (List<Classes>) classesRepo.findAll();
			for (Classes classed : classes) {
				if((cgpa >= classed.getMaxPoint()) && (cgpa <= classed.getMinPoint())) {
					remarked = classed.getClassed();
				}
			}
		}else {
				remarked = repeatCourses.toString();
			}
		
		return remarked;
	}
	
	
	public List<ResultsDisplayer> results(String dept, String session, 
			String pgdCourseType, String firstSemester, String coreCourseAllocation, 
			String secondSemester, String projectSemester){
		int serialNumber = 0;
		List<ResultsDisplayer> displayAll = new ArrayList<>();
		
		Setting settings =   settingRepo.findByDeptAndSessionAndCourseType(dept, session, pgdCourseType);
		
		int	noOfFirstElectiveCourses = settings.getNoOfFirstElectiveCourses();
		int	noOfSecondElectiveCourses = settings.getNoOfSecondElectiveCourses();
	
		/////////////////////////////////////////////////////////////////////
		List<Student> PGDStudents = studentRepo.findByDepartmentAndSessionAndCourseType(dept, session, pgdCourseType);
		for (Student student : PGDStudents) {	
			double firstPgdGpa = 0.0;
			double firstPgdSumCreditUnit = 0.0;
			List<String> repeatCourses = new ArrayList<>();
			
			List<Course> firstCoreCourses = courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocation(dept, session, firstSemester, coreCourseAllocation);
			List<Course> firstElectiveCourses = resultsGenerator.electiveCourses(noOfFirstElectiveCourses, student.getMatricNo(), firstSemester, session, dept);
			firstCoreCourses.addAll(firstElectiveCourses);
			for (Course course : firstCoreCourses) {
				List<Result> firstCoreResults = resultRepo.findByCourseIdAndMatricNoAndUnit(course.getCourseId(), student.getMatricNo(), dept);
				for (Result result : firstCoreResults) {
					double addScores = 0.0;
					if(result != null) {
						addScores = result.getAsses() + result.getExam();
					}else {
						addScores = 0.0;
					}
					List<Grade> grades = (List<Grade>) gradeRepo.findAll();
					for (Grade grade : grades) {
						if((addScores >= grade.getMinScore()) && (addScores <= grade.getMaxScore())) {
							double gradePoint = grade.getGradePoint();
							double multiplier = gradePoint * course.getCreditUnit();
							firstPgdSumCreditUnit += course.getCreditUnit();
							firstPgdGpa += multiplier;
							
							if(grade.getGrade().equalsIgnoreCase("F")) {
								
								repeatCourses.add(course.getCourseCode());
							}
						} 
					}
				}
			}
			///////////////////////////////////////////////////////////////////////
			///second semester pgd
			////////////////////////////////////////////////////
			
			
			double secondPgdSumCreditUnit = 0.0;
			double secondPgdGpa = 0.0;
			List<Course> secondCoreCourses = courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocation(dept, session, secondSemester, coreCourseAllocation);
			List<Course> secondElectiveCourses = resultsGenerator.electiveCourses(noOfSecondElectiveCourses, student.getMatricNo(), secondSemester, session, dept);
			secondCoreCourses.addAll(secondElectiveCourses);
			for (Course secondCourse : secondCoreCourses) {
				List<Result> secondCoreResults = resultRepo.findByCourseIdAndMatricNoAndUnit(secondCourse.getCourseId(), student.getMatricNo(), dept);
				for (Result secondResult : secondCoreResults) {
					double addSecondScores = 0.0;
					if(secondResult != null) {
						addSecondScores = secondResult.getAsses() + secondResult.getExam();
					}else {
						addSecondScores = 0.0;
					}
					List<Grade> grades = (List<Grade>) gradeRepo.findAll();
					
					for (Grade grade : grades) {
						if((addSecondScores >= grade.getMinScore()) && (addSecondScores <= grade.getMaxScore())) {
							
							double gradePoint = grade.getGradePoint();
							double multiplier = gradePoint * secondCourse.getCreditUnit();
							secondPgdSumCreditUnit += secondCourse.getCreditUnit();
							secondPgdGpa += multiplier;
							
							if(grade.getGrade().equalsIgnoreCase("F")) {
								
								repeatCourses.add(secondCourse.getCourseCode());
							}
						} 
					}
				}
			}
			///////////////////////////////////////////////////////
			//project
			////////////////////////////////////////////////
			double projectPgdSumCreditUnit = 0.0;
			double projectPgdGpa = 0.0;
			List<Course> projectCourse = courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocation(dept, session, projectSemester, coreCourseAllocation);
			for (Course projectCourses : projectCourse) {
				List<Result> projectResults = resultRepo.findByCourseIdAndMatricNoAndUnit(projectCourses.getCourseId(), student.getMatricNo(), dept);
				for (Result projectResult : projectResults) {
					
					double addProjectScores = 0.0;
					if(projectResult.getCourseId() == projectCourses.getCourseId()) {
						addProjectScores = projectResult.getAsses() + projectResult.getExam();
					}else if(projectResult.getCourseId() != projectCourses.getCourseId()) {
						addProjectScores = 0.0;
					}
					List<Grade> grades = (List<Grade>) gradeRepo.findAll();
					
					for (Grade grade : grades) {
						if((addProjectScores >= grade.getMinScore()) && (addProjectScores <= grade.getMaxScore())) {
							
							double gradePoint = grade.getGradePoint();
							double multiplier = gradePoint * projectCourses.getCreditUnit();
							projectPgdSumCreditUnit += projectCourses.getCreditUnit();
							projectPgdGpa += multiplier;
							
							if(grade.getGrade().equalsIgnoreCase("F")) {
								
								repeatCourses.add(projectCourses.getCourseCode());
							}
						} 
					}
				}
			}
			
			//////////////////////////////////////////////////////
			
			double firstGpa = resultsGenerator.nanConfirmation(firstPgdGpa, firstPgdSumCreditUnit);
			
			double secondGpa = resultsGenerator.nanConfirmation(secondPgdGpa, secondPgdSumCreditUnit);
			
			double projectGpa = resultsGenerator.nanConfirmation(projectPgdGpa, projectPgdSumCreditUnit);
			
			double pgdSumCgpa = firstPgdGpa + secondPgdGpa + projectPgdGpa;
			
			double pgdSumCreditUnit = firstPgdSumCreditUnit + secondPgdSumCreditUnit + projectPgdSumCreditUnit;
			
			double cgpa = resultsGenerator.nanConfirmation(pgdSumCgpa, pgdSumCreditUnit);
			
			double firstGpa1 = resultsGenerator.roundUp(firstGpa);
			
			double secondGpa1 = resultsGenerator.roundUp(secondGpa);
			
			double projectGpa1 = resultsGenerator.roundUp(projectGpa);
			
			double cgpa1 = resultsGenerator.roundUp(cgpa);
			
			serialNumber += 1;
			
			String remark = resultsGenerator.remark(repeatCourses, cgpa1);
			
			displayAll.add(new ResultsDisplayer(serialNumber, student.getSurname(), student.getFirstname(), student.getMiddlename(), student.getMatricNo(),firstGpa1,secondGpa1,projectGpa1,cgpa1,remark, student.getStudentId()));
		}
		
		return displayAll;
	
	}
	
	public int noOfElectiveCourses(String dept, String session, String courseType, String semester) {
		Setting settings =  settingRepo.findByDeptAndSessionAndCourseType(dept, session, courseType);
		
		int electiveCoursesNo = 0;
		
		int	noOfFirstElectiveCourses = settings.getNoOfFirstElectiveCourses();
		int	noOfSecondElectiveCourses = settings.getNoOfSecondElectiveCourses();
		
		if(semester == "first") {
			electiveCoursesNo = noOfFirstElectiveCourses;
			
		} else if(semester == "second"){
			electiveCoursesNo = noOfSecondElectiveCourses;
			
		}
		
		return electiveCoursesNo;
	}
	
	public List<eachCourseResult> eachStudentTranscript(String dept, String session, 
			String pgdCourseType, String firstSemester, String coreCourseAllocation, 
			 String matricNo){
		int serialNumber = 0;
		List<eachCourseResult> theResult = new ArrayList<>();
		
		int noOfFirstElectiveCourses = resultsGenerator.noOfElectiveCourses(dept, session, pgdCourseType, firstSemester);
		/////////////////////////////////////////////////////////////////////
			
			List<Course> firstCoreCourses = courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, firstSemester, coreCourseAllocation, pgdCourseType);
			List<Course> firstElectiveCourses = resultsGenerator.electiveCourses(noOfFirstElectiveCourses, matricNo, firstSemester, session, dept);
			firstCoreCourses.addAll(firstElectiveCourses);
			List<Grade> grades = (List<Grade>) gradeRepo.findAll();
			for (Course course : firstCoreCourses) {
				String courseCode = course.getCourseCode();
				String courseTitle = course.getCourseTitle();
				double asses = 0.0;
				double exam = 0.0;
				double total = 0.0;
				String graded = "F";
				List<Result> firstCoreResults = resultRepo.findByCourseIdAndMatricNoAndUnit(course.getCourseId(), matricNo, dept);
				for (Result result : firstCoreResults) {
					if(result.getMatricNo() != null) {
						asses = result.getAsses();
						exam = result.getExam();
						total = result.getAsses() + result.getExam();
					}else {
						
						asses = 0.0;
						exam = 0.0;
						total = 0.0;
					}
					
					for (Grade grade : grades) {
						if((total >= grade.getMinScore()) && (total <= grade.getMaxScore())) {
							
							graded = grade.getGrade();
							
						} 
					}
				}
				
				
				serialNumber += 1;
				
				theResult.add(new eachCourseResult(serialNumber, courseCode, courseTitle, asses, exam, total, graded));
			}
			
		return theResult;
	
	}
	
	public double roundUp(double value) {
		 BigDecimal bd = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP);
	     double newInput = bd.doubleValue();
		return newInput;
	}
	
	public List<cgpa> calculate(String dept, String session, 
			String pgdCourseType, String firstSemester, String coreCourseAllocation, 
			String secondSemester, String projectSemester, String matricNo){
		
		List<cgpa> displayAll = new ArrayList<>();
		
		Setting settings = settingRepo.findByDeptAndSessionAndCourseType(dept, session, pgdCourseType);
		
		
		int noOfFirstElectiveCourses = settings.getNoOfFirstElectiveCourses();
		int	noOfSecondElectiveCourses = settings.getNoOfSecondElectiveCourses();
		
		/////////////////////////////////////////////////////////////////////
		
			double firstPgdGpa = 0.0;
			double firstPgdSumCreditUnit = 0.0;
			List<String> repeatCourses = new ArrayList<>();
			
			List<Course> firstCoreCourses = courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocation(dept, session, firstSemester, coreCourseAllocation);
			List<Course> firstElectiveCourses = resultsGenerator.electiveCourses(noOfFirstElectiveCourses, matricNo, firstSemester, session, dept);
			firstCoreCourses.addAll(firstElectiveCourses);
			for (Course course : firstCoreCourses) {
				List<Result> firstCoreResults = resultRepo.findByCourseIdAndMatricNoAndUnit(course.getCourseId(), matricNo, dept);
				for (Result result : firstCoreResults) {
					double addScores = 0.0;
					if(result != null) {
						addScores = result.getAsses() + result.getExam();
					}else {
						addScores = 0.0;
					}
					List<Grade> grades = (List<Grade>) gradeRepo.findAll();
					for (Grade grade : grades) {
						if((addScores >= grade.getMinScore()) && (addScores <= grade.getMaxScore())) {
							double gradePoint = grade.getGradePoint();
							double multiplier = gradePoint * course.getCreditUnit();
							firstPgdSumCreditUnit += course.getCreditUnit();
							firstPgdGpa += multiplier;
							
							if(grade.getGrade().equalsIgnoreCase("F")) {
								
								repeatCourses.add(course.getCourseCode());
							}
						} 
					}
				}
			}
			///////////////////////////////////////////////////////////////////////
			///second semester pgd
			////////////////////////////////////////////////////
			
			
			double secondPgdSumCreditUnit = 0.0;
			double secondPgdGpa = 0.0;
			List<Course> secondCoreCourses = courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocation(dept, session, secondSemester, coreCourseAllocation);
			List<Course> secondElectiveCourses = resultsGenerator.electiveCourses(noOfSecondElectiveCourses, matricNo, secondSemester, session, dept);
			secondCoreCourses.addAll(secondElectiveCourses);
			for (Course secondCourse : secondCoreCourses) {
				List<Result> secondCoreResults = resultRepo.findByCourseIdAndMatricNoAndUnit(secondCourse.getCourseId(), matricNo, dept);
				for (Result secondResult : secondCoreResults) {
					double addSecondScores = 0.0;
					if(secondResult != null) {
						addSecondScores = secondResult.getAsses() + secondResult.getExam();
					}else {
						addSecondScores = 0.0;
					}
					List<Grade> grades = (List<Grade>) gradeRepo.findAll();
					
					for (Grade grade : grades) {
						if((addSecondScores >= grade.getMinScore()) && (addSecondScores <= grade.getMaxScore())) {
							
							double gradePoint = grade.getGradePoint();
							double multiplier = gradePoint * secondCourse.getCreditUnit();
							secondPgdSumCreditUnit += secondCourse.getCreditUnit();
							secondPgdGpa += multiplier;
							
							if(grade.getGrade().equalsIgnoreCase("F")) {
								
								repeatCourses.add(secondCourse.getCourseCode());
							}
						} 
					}
				}
			}
			///////////////////////////////////////////////////////
			//project
			////////////////////////////////////////////////
			double projectPgdSumCreditUnit = 0.0;
			double projectPgdGpa = 0.0;
			List<Course> projectCourse = courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocation(dept, session, projectSemester, coreCourseAllocation);
			for (Course projectCourses : projectCourse) {
				List<Result> projectResults = resultRepo.findByCourseIdAndMatricNoAndUnit(projectCourses.getCourseId(), matricNo, dept);
				for (Result projectResult : projectResults) {
					
					double addProjectScores = 0.0;
					if(projectResult.getCourseId() == projectCourses.getCourseId()) {
						addProjectScores = projectResult.getAsses() + projectResult.getExam();
					}else if(projectResult.getCourseId() != projectCourses.getCourseId()) {
						addProjectScores = 0.0;
					}
					List<Grade> grades = (List<Grade>) gradeRepo.findAll();
					
					for (Grade grade : grades) {
						if((addProjectScores >= grade.getMinScore()) && (addProjectScores <= grade.getMaxScore())) {
							
							double gradePoint = grade.getGradePoint();
							double multiplier = gradePoint * projectCourses.getCreditUnit();
							projectPgdSumCreditUnit += projectCourses.getCreditUnit();
							projectPgdGpa += multiplier;
							
							if(grade.getGrade().equalsIgnoreCase("F")) {
								
								repeatCourses.add(projectCourses.getCourseCode());
							}
						} 
					}
				}
			}
			
			//////////////////////////////////////////////////////
			
			double firstGpa = resultsGenerator.nanConfirmation(firstPgdGpa, firstPgdSumCreditUnit);
			
			double secondGpa = resultsGenerator.nanConfirmation(secondPgdGpa, secondPgdSumCreditUnit);
			
			double projectGpa = resultsGenerator.nanConfirmation(projectPgdGpa, projectPgdSumCreditUnit);
			
			double pgdSumCgpa = firstPgdGpa + secondPgdGpa + projectPgdGpa;
			
			double pgdSumCreditUnit = firstPgdSumCreditUnit + secondPgdSumCreditUnit + projectPgdSumCreditUnit;
			
			double cgpa = resultsGenerator.nanConfirmation(pgdSumCgpa, pgdSumCreditUnit);
			
			double firstGpa1 = resultsGenerator.roundUp(firstGpa);
			
			double secondGpa1 = resultsGenerator.roundUp(secondGpa);
			
			double projectGpa1 = resultsGenerator.roundUp(projectGpa);
			
			double cgpa1 = resultsGenerator.roundUp(cgpa);
			
			String remark = resultsGenerator.remark(repeatCourses, cgpa1);
			
			displayAll.add(new cgpa(firstGpa1,secondGpa1,projectGpa1,cgpa1,remark));
		
			
		return displayAll;
	
	}
	
}

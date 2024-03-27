package org.unical.resultProcessor.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.unical.resultProcessor.dao.ClassesRepo;
import org.unical.resultProcessor.dao.CourseRepo;
import org.unical.resultProcessor.dao.GradeRepo;
import org.unical.resultProcessor.dao.ResultRepo;
import org.unical.resultProcessor.dao.SettingRepo;
import org.unical.resultProcessor.dao.StudentRepository;
import org.unical.resultProcessor.dao.UnitRepo;
import org.unical.resultProcessor.model.Identification;
import org.unical.resultProcessor.model.ResultsDisplayer;
import org.unical.resultProcessor.model.Student;
import org.unical.resultProcessor.model.Unit;
import org.unical.resultProcessor.model.User;
import org.unical.resultProcessor.model.cgpa;
import org.unical.resultProcessor.model.eachCourseResult;
import org.unical.resultProcessor.services.ResultsGenerator;

@Controller
public class resultGeneratorController {

	@Autowired
	CourseRepo courseRepo;
	
	@Autowired
	StudentRepository studentRepo;
	
	@Autowired
	ResultRepo resultRepo;
	
	@Autowired
	GradeRepo gradeRepo;
	
	@Autowired
	ClassesRepo classesRepo;
	
	@Autowired
	SettingRepo settingRepo;
	
	@Autowired
	UnitRepo unitRepo;
	
	@Autowired
	ResultsGenerator resultsGenerator;

	
	@GetMapping("/resultGenerator")
	public String resultGenerator(@SessionAttribute("userInfo") User userInfo, HttpServletRequest request) {
		String dept = userInfo.getPosition();
		String session = userInfo.getSession();
		String pgdCourseType = "PGD";
		String firstSemester = "first";
		String coreCourseAllocation = "core";
		String secondSemester = "second";
		String projectSemester = "project";
		
		String mscCourseType = "MSc";
		String phdCourseType = "PhD";
		
		List<ResultsDisplayer> pgdResults = resultsGenerator.results(dept, session, pgdCourseType, firstSemester, coreCourseAllocation, secondSemester, projectSemester);
		request.setAttribute("pgdResults", pgdResults);
		
		List<ResultsDisplayer> mscResults = resultsGenerator.results(dept, session, mscCourseType, firstSemester, coreCourseAllocation, secondSemester, projectSemester);
		request.setAttribute("mscResults", mscResults);
		
		List<ResultsDisplayer> phdResults = resultsGenerator.results(dept, session, phdCourseType, firstSemester, coreCourseAllocation, secondSemester, projectSemester);
		request.setAttribute("phdResults", phdResults);
		
		
		///////////////////////////////////////////////////////////////////
		
		return "viewResults";
	}
	
	@GetMapping("/viewResult/{studentId}")
	public String viewResult(@PathVariable("studentId") int studentId, @SessionAttribute("userInfo") User userInfo, HttpServletRequest request) {
		String dept = userInfo.getPosition();
		String session = userInfo.getSession();
		String firstSemester = "first";
		String secondSemester = "second";
		String projectSemester = "project";
		String courseAllocation = "core";
		
		Student studentCourseType = studentRepo.findByDepartmentAndSessionAndStudentId(dept, session, studentId);
		
		Unit unitDept = unitRepo.findByUnitDept(studentCourseType.getDepartment());
		
		String courseType = studentCourseType.getCourseType();
		
		List<eachCourseResult> eachFirstResult = resultsGenerator.eachStudentTranscript(dept, session, courseType, firstSemester, courseAllocation, studentCourseType.getMatricNo());
		request.setAttribute("firstResults", eachFirstResult);
		
		List<eachCourseResult> eachSecondResult = resultsGenerator.eachStudentTranscript(dept, session, courseType, secondSemester, courseAllocation, studentCourseType.getMatricNo());
		request.setAttribute("secondResults", eachSecondResult);
		
		List<eachCourseResult> projectResult = resultsGenerator.eachStudentTranscript(dept, session, courseType, projectSemester, courseAllocation, studentCourseType.getMatricNo());
		request.setAttribute("projectResults", projectResult);
		
		List<Identification> studentIdentity = new ArrayList<>();
		studentIdentity.add(new Identification(studentCourseType.getSurname(), studentCourseType.getFirstname(), studentCourseType.getMiddlename(), studentCourseType.getSex(), unitDept.getFaculty(), unitDept.getDepartment(), unitDept.getUnitDept(), session, courseType, studentCourseType.getMatricNo()));
		request.setAttribute("identity", studentIdentity);
		
		List<cgpa> displayCgpa = resultsGenerator.calculate(dept, session, courseType, firstSemester, courseAllocation, secondSemester, projectSemester, studentCourseType.getMatricNo());
		request.setAttribute("gps", displayCgpa);
		
		return "eachStudentResult";
	}
	
}

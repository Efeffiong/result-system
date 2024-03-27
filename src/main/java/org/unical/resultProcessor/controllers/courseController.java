package org.unical.resultProcessor.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.unical.resultProcessor.dao.CourseRepo;
import org.unical.resultProcessor.dao.StudentRepository;
import org.unical.resultProcessor.dao.UnitRepo;
import org.unical.resultProcessor.model.Course;
import org.unical.resultProcessor.model.Student;
import org.unical.resultProcessor.model.User;
import org.unical.resultProcessor.services.IdGenerator;

@Controller
public class courseController {

	@Autowired
	CourseRepo courseRepo; 
	
	@Autowired
	UnitRepo unitRepo;
	
	@Autowired
	StudentRepository studentRepo;
	
	@GetMapping("/courseForm")
	public String showCourseForm(Course course) {
		
		
		return "add-Course";
	}
	
	@GetMapping("/viewCourses")
	public String viewCourses(@SessionAttribute("userInfo") User userInfo, HttpServletRequest request, Model model) {
		request.setAttribute("units", unitRepo.findAll());
		String session = userInfo.getSession();
		String dept = userInfo.getPosition();
		
		
		model.addAttribute("pgdCoreFirst", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "first", "core", "PGD"));
		model.addAttribute("pgdElectiveFirst", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "first", "elective", "PGD"));
		
		model.addAttribute("pgdCoreSecond", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "second", "core", "PGD"));
		model.addAttribute("pgdElectiveSecond", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "second", "elective", "PGD"));
		
		model.addAttribute("mscCoreFirst", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "first", "core", "MSc"));
		model.addAttribute("mscElectiveFirst", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "first", "elective", "MSc"));
		
		model.addAttribute("mscCoreSecond", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "second", "core", "MSc"));
		model.addAttribute("mscElectiveSecond", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "second", "elective", "MSc"));
		
		model.addAttribute("phdCoreFirst", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "first", "core", "PhD"));
		model.addAttribute("phdElectiveFirst", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "first", "elective", "PhD"));
		
		model.addAttribute("phdCoreSecond", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "second", "core", "PhD"));
		model.addAttribute("phdElectiveSecond", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "second", "elective", "PhD"));
		return "courses-List";
	}
	
	@PostMapping("/addCourse")
	public String addCourse(@SessionAttribute("userInfo") User userInfo, @Valid Course course, BindingResult result, Model model, IdGenerator idGenerator) {
		course.setCourseId(idGenerator.generateId());
		course.setDepartment(userInfo.getPosition());
		course.setCourseSession(userInfo.getSession());
		List<Course> courseSearcher = courseRepo.findByCourseAllocationAndCourseCodeAndCourseSessionAndCourseTypeAndDepartmentAndSemester(course.getCourseAllocation(), course.getCourseCode(), course.getCourseSession(), course.getCourseType(), course.getDepartment(), course.getSemester());
		if(result.hasErrors()) {
			
			model.addAttribute("errorMessage", "Warning: The form must be completed correctly before submitting");
			return "add-Course";
		} else if(courseSearcher.size() == 1) {
			
			model.addAttribute("errorMessage", "Warning: This course had already been registered!");
			return "add-Course";
		}
		
		else {
		courseRepo.save(course);
		model.addAttribute("courses", courseRepo.findAll());
		
		String session = userInfo.getSession();
		String dept = userInfo.getPosition();
		
		model.addAttribute("pgdCoreFirst", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "first", "core", "PGD"));
		model.addAttribute("pgdElectiveFirst", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "first", "elective", "PGD"));
		
		model.addAttribute("pgdCoreSecond", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "second", "core", "PGD"));
		model.addAttribute("pgdElectiveSecond", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "second", "elective", "PGD"));
		
		model.addAttribute("mscCoreFirst", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "first", "core", "MSc"));
		model.addAttribute("mscElectiveFirst", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "first", "elective", "MSc"));
		
		model.addAttribute("mscCoreSecond", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "second", "core", "MSc"));
		model.addAttribute("mscElectiveSecond", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "second", "elective", "MSc"));
		
		model.addAttribute("phdCoreFirst", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "first", "core", "PhD"));
		model.addAttribute("phdElectiveFirst", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "first", "elective", "PhD"));
		
		model.addAttribute("phdCoreSecond", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "second", "core", "PhD"));
		model.addAttribute("phdElectiveSecond", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "second", "elective", "PhD"));
		
		model.addAttribute("successMessage", "The course has successfully been registered");
		return "courses-List";
		}
	}
	
	@GetMapping("/editCourse/{courseId}")
	public String showUpdatCourseForm(@PathVariable("courseId") Long courseId, Model model) {
		Course course = courseRepo.findById(courseId)
				.orElseThrow(()-> new IllegalArgumentException("invalid id:" + courseId));
		model.addAttribute("course", course);
		return "update-Course";
	}
	
	@PostMapping("/updateCourse/{updatedCourseId}")
	public String updateCourse(@SessionAttribute("userInfo") User userInfo, @PathVariable("updatedCourseId") Long courseId, @Valid Course course, BindingResult result, Model model) {		
		course.setDepartment(userInfo.getPosition());
		course.setCourseSession(userInfo.getSession());
		List<Course> courseSearcher = courseRepo.findByCourseAllocationAndCourseCodeAndCourseSessionAndCourseTitleAndCourseTypeAndCreditUnitAndDepartmentAndLevelAndSemester(course.getCourseAllocation(), course.getCourseCode(), course.getCourseSession(), course.getCourseTitle(), course.getCourseType(), course.getCreditUnit(), course.getDepartment(), course.getLevel(), course.getSemester());
		
		if(result.hasErrors()) {
			model.addAttribute("courses", courseRepo.findAll());
			String session = userInfo.getSession();
			String dept = userInfo.getPosition();
			
			model.addAttribute("pgdCoreFirst", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "first", "core", "PGD"));
			model.addAttribute("pgdElectiveFirst", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "first", "elective", "PGD"));
			
			model.addAttribute("pgdCoreSecond", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "second", "core", "PGD"));
			model.addAttribute("pgdElectiveSecond", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "second", "elective", "PGD"));
			
			model.addAttribute("mscCoreFirst", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "first", "core", "MSc"));
			model.addAttribute("mscElectiveFirst", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "first", "elective", "MSc"));
			
			model.addAttribute("mscCoreSecond", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "second", "core", "MSc"));
			model.addAttribute("mscElectiveSecond", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "second", "elective", "MSc"));
			
			model.addAttribute("phdCoreFirst", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "first", "core", "PhD"));
			model.addAttribute("phdElectiveFirst", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "first", "elective", "PhD"));
			
			model.addAttribute("phdCoreSecond", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "second", "core", "PhD"));
			model.addAttribute("phdElectiveSecond", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "second", "elective", "PhD"));
			
			model.addAttribute("errorMessage", "The form must be completed correctly before submitting");
			return "courses-List";
			
			
		} else if(courseSearcher.size() == 1) {
			model.addAttribute("courses", courseRepo.findAll());
			
			String session = userInfo.getSession();
			String dept = userInfo.getPosition();
			
			model.addAttribute("pgdCoreFirst", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "first", "core", "PGD"));
			model.addAttribute("pgdElectiveFirst", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "first", "elective", "PGD"));
			
			model.addAttribute("pgdCoreSecond", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "second", "core", "PGD"));
			model.addAttribute("pgdElectiveSecond", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "second", "elective", "PGD"));
			
			model.addAttribute("mscCoreFirst", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "first", "core", "MSc"));
			model.addAttribute("mscElectiveFirst", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "first", "elective", "MSc"));
			
			model.addAttribute("mscCoreSecond", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "second", "core", "MSc"));
			model.addAttribute("mscElectiveSecond", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "second", "elective", "MSc"));
			
			model.addAttribute("phdCoreFirst", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "first", "core", "PhD"));
			model.addAttribute("phdElectiveFirst", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "first", "elective", "PhD"));
			
			model.addAttribute("phdCoreSecond", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "second", "core", "PhD"));
			model.addAttribute("phdElectiveSecond", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "second", "elective", "PhD"));
			
			model.addAttribute("errorMessage", "Warning: This course had already been registered!");
			return "courses-List";
			
			
		}
		
		else {
		course.setCourseId(courseId);
		courseRepo.save(course);
		model.addAttribute("courses", courseRepo.findAll());
		String session = userInfo.getSession();
		String dept = userInfo.getPosition();
		
		model.addAttribute("pgdCoreFirst", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "first", "core", "PGD"));
		model.addAttribute("pgdElectiveFirst", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "first", "elective", "PGD"));
		
		model.addAttribute("pgdCoreSecond", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "second", "core", "PGD"));
		model.addAttribute("pgdElectiveSecond", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "second", "elective", "PGD"));
		
		model.addAttribute("mscCoreFirst", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "first", "core", "MSc"));
		model.addAttribute("mscElectiveFirst", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "first", "elective", "MSc"));
		
		model.addAttribute("mscCoreSecond", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "second", "core", "MSc"));
		model.addAttribute("mscElectiveSecond", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "second", "elective", "MSc"));
		
		model.addAttribute("phdCoreFirst", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "first", "core", "PhD"));
		model.addAttribute("phdElectiveFirst", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "first", "elective", "PhD"));
		
		model.addAttribute("phdCoreSecond", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "second", "core", "PhD"));
		model.addAttribute("phdElectiveSecond", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "second", "elective", "PhD"));
		model.addAttribute("successMessage", "Course Infomation has successfully been updated!");
		return "courses-List";
		}
	}
	
	@GetMapping("/deleteCourse/{courseId}")
	public String deleteCourse(@SessionAttribute("userInfo") User userInfo, @PathVariable("courseId") Long courseId, Model model) {
		Course course = courseRepo.findById(courseId)
				.orElseThrow(()-> new IllegalArgumentException("invalid id:" + courseId));
		courseRepo.delete(course);
		model.addAttribute("courses", courseRepo.findAll());
		String session = userInfo.getSession();
		String dept = userInfo.getPosition();
		
		model.addAttribute("pgdCoreFirst", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "first", "core", "PGD"));
		model.addAttribute("pgdElectiveFirst", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "first", "elective", "PGD"));
		
		model.addAttribute("pgdCoreSecond", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "second", "core", "PGD"));
		model.addAttribute("pgdElectiveSecond", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "second", "elective", "PGD"));
		
		model.addAttribute("mscCoreFirst", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "first", "core", "MSc"));
		model.addAttribute("mscElectiveFirst", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "first", "elective", "MSc"));
		
		model.addAttribute("mscCoreSecond", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "second", "core", "MSc"));
		model.addAttribute("mscElectiveSecond", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "second", "elective", "MSc"));
		
		model.addAttribute("phdCoreFirst", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "first", "core", "PhD"));
		model.addAttribute("phdElectiveFirst", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "first", "elective", "PhD"));
		
		model.addAttribute("phdCoreSecond", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "second", "core", "PhD"));
		model.addAttribute("phdElectiveSecond", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "second", "elective", "PhD"));
		
		model.addAttribute("successMessage", "The course has successfully been deleted");
		return "courses-List";
	}
	
	@GetMapping("/studentViewCourses")
	public String viewStudentCourses(@SessionAttribute("userInfo") User userInfo, HttpServletRequest request, Model model) {
		request.setAttribute("units", unitRepo.findAll());
		String session = userInfo.getSession();
		String dept = userInfo.getPosition();
		String matricNo = userInfo.getRegNum();
		
		Student student = studentRepo.findByDepartmentAndSessionAndMatricNo(dept, session, matricNo);
		String studentType = student.getCourseType();
		model.addAttribute("firstSemesterCores", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "first", "core", studentType));
		model.addAttribute("firstSemesterElectives", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "first", "elective", studentType));
		
		model.addAttribute("secondSemesterCores", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "second", "core", studentType));
		model.addAttribute("secondSemesterElectives", courseRepo.findByDepartmentAndCourseSessionAndSemesterAndCourseAllocationAndCourseType(dept, session, "second", "elective", studentType));
		return "studentViewCoursesList";
	}
}

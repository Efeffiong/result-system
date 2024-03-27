package org.unical.resultProcessor.controllers;


import java.time.LocalDate;
import java.time.LocalDateTime;
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
import org.unical.resultProcessor.dao.StudentRepository;
import org.unical.resultProcessor.dao.UnitRepo;
import org.unical.resultProcessor.model.Student;
import org.unical.resultProcessor.model.User;



@Controller
public class studentController {

	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	UnitRepo unitRepo;
	
	
	@GetMapping("/signup")
	public String showSignupForm(Student student) {
		return "add-Student";
	}
	
	@GetMapping("/viewStudents")
	public String viewStudents(@SessionAttribute("userInfo") User userInfo,HttpServletRequest request, Model model) {
		String dept = userInfo.getPosition();
		String session = userInfo.getSession();
		
		model.addAttribute("pgd", studentRepository.findByDepartmentAndSessionAndCourseType(dept, session, "PGD"));
		model.addAttribute("msc", studentRepository.findByDepartmentAndSessionAndCourseType(dept, session, "MSc"));
		model.addAttribute("phd", studentRepository.findByDepartmentAndSessionAndCourseType(dept, session, "PhD"));
		return "index";
	}
	
	@PostMapping("/addstudent")
	public String addStudent(@SessionAttribute("userInfo") User userInfo, @Valid Student student, BindingResult result, Model model) {
		student.setDepartment(userInfo.getPosition());
		student.setSession(userInfo.getSession());
		List <Student> studentSearcher = studentRepository.findByDepartmentAndFirstnameAndSurnameAndMiddlenameAndSessionAndCourseTypeAndMatricNo(student.getDepartment(), student.getFirstname(), student.getSurname(), student.getMiddlename(), student.getSession(), student.getCourseType(), student.getMatricNo());
		
		if(result.hasErrors()) {
			model.addAttribute("errorMessage", "Warning: Student information must be completed correctly before submitting");
			
			return "add-student";
		} else if (studentSearcher.size() == 1) {
			model.addAttribute("errorMessage", "Warning: Student has already been registered");
			return "add-student";
		} else {
		LocalDateTime localDateTime = LocalDateTime.now();
		LocalDate localDate = localDateTime.toLocalDate();
		student.setRegistrationDate(localDate);
		studentRepository.save(student);
		model.addAttribute("students", studentRepository.findAll());
		String dept = userInfo.getPosition();
		String session = userInfo.getSession();
		
		model.addAttribute("pgd", studentRepository.findByDepartmentAndSessionAndCourseType(dept, session, "PGD"));
		model.addAttribute("msc", studentRepository.findByDepartmentAndSessionAndCourseType(dept, session, "MSc"));
		model.addAttribute("phd", studentRepository.findByDepartmentAndSessionAndCourseType(dept, session, "PhD"));
		model.addAttribute("successMessage", "Student has been successfully registered");
		return "index";
		}
	}
	
	@GetMapping("/edit/{id}")
	public String showUpdatForm(@PathVariable("id") Integer id, Model model) {
		Student student = studentRepository.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("invalid id:" + id));
		model.addAttribute("student", student);
		
		return "update-student";
	}
	
	@PostMapping("/update/{id}")
	public String updateStudent(@SessionAttribute("userInfo") User userInfo, @PathVariable("id") Integer id, @Valid Student student, BindingResult result, Model model) {
		List <Student> studentSearcher = studentRepository.findByDepartmentAndFirstnameAndSurnameAndMiddlenameAndSessionAndCourseTypeAndMatricNo(student.getDepartment(), student.getFirstname(), student.getSurname(), student.getMiddlename(), student.getSession(), student.getCourseType(), student.getMatricNo());
		
		if(result.hasErrors()) {
			String dept = userInfo.getPosition();
			String session = userInfo.getSession();
			
			model.addAttribute("pgd", studentRepository.findByDepartmentAndSessionAndCourseType(dept, session, "PGD"));
			model.addAttribute("msc", studentRepository.findByDepartmentAndSessionAndCourseType(dept, session, "MSc"));
			model.addAttribute("phd", studentRepository.findByDepartmentAndSessionAndCourseType(dept, session, "PhD"));
			
			model.addAttribute("errorMessage", "Warning: Student information must be completed correctly before submitting");
			model.addAttribute("units", unitRepo.findAll());
			return "index";
		} else if (studentSearcher.size() == 1) {
			String dept = userInfo.getPosition();
			String session = userInfo.getSession();
			
			model.addAttribute("pgd", studentRepository.findByDepartmentAndSessionAndCourseType(dept, session, "PGD"));
			model.addAttribute("msc", studentRepository.findByDepartmentAndSessionAndCourseType(dept, session, "MSc"));
			model.addAttribute("phd", studentRepository.findByDepartmentAndSessionAndCourseType(dept, session, "PhD"));
			
			model.addAttribute("errorMessage", "Warning: Student has already been registered");
			return "index";
		} else {
		
		student.setStudentId(id);
		student.setDepartment(userInfo.getPosition());
		student.setSession(userInfo.getSession());
		studentRepository.save(student);
		String dept = userInfo.getPosition();
		String session = userInfo.getSession();
		
		model.addAttribute("pgd", studentRepository.findByDepartmentAndSessionAndCourseType(dept, session, "PGD"));
		model.addAttribute("msc", studentRepository.findByDepartmentAndSessionAndCourseType(dept, session, "MSc"));
		model.addAttribute("phd", studentRepository.findByDepartmentAndSessionAndCourseType(dept, session, "PhD"));
		return "index";
		}
	}
	
	@GetMapping("/delete/{id}")
	public String deleteStudent(@SessionAttribute("userInfo") User userInfo, @PathVariable("id") Integer id, Model model) {
		Student student = studentRepository.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("invalid id:" + id));
		studentRepository.delete(student);
		String dept = userInfo.getPosition();
		String session = userInfo.getSession();
		
		model.addAttribute("pgd", studentRepository.findByDepartmentAndSessionAndCourseType(dept, session, "PGD"));
		model.addAttribute("msc", studentRepository.findByDepartmentAndSessionAndCourseType(dept, session, "MSc"));
		model.addAttribute("phd", studentRepository.findByDepartmentAndSessionAndCourseType(dept, session, "PhD"));
		model.addAttribute("successMessage", "Student has been successfully deleted");
		return "index";
	}
}

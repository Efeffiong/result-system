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
import org.unical.resultProcessor.dao.ResultRepo;
import org.unical.resultProcessor.dao.StudentRepository;
import org.unical.resultProcessor.model.Course;
import org.unical.resultProcessor.model.Result;
import org.unical.resultProcessor.model.User;
import org.unical.resultProcessor.services.IdGenerator;

@Controller
public class resultController {
	
	@Autowired
	CourseRepo courseRepo;
	
	@Autowired
	ResultRepo resultRepo;
	
	@Autowired
	StudentRepository studentRepo;
	
	@GetMapping("/resultForm")
	public String addResult(@SessionAttribute("userInfo") User userInfo, Course course, Result result, HttpServletRequest request) {
		String department = userInfo.getPosition();
		String courseSession = userInfo.getSession();
		request.setAttribute("courses", courseRepo.findByDepartmentAndCourseSession(department, courseSession));
		request.setAttribute("students", studentRepo.findByDepartmentAndSession(department, courseSession));
		return "add-result";
	}
	
	
	@PostMapping("/addResult")
	public String newUser(@SessionAttribute("userInfo") User userInfo, @Valid Result result, BindingResult rs, Model model, IdGenerator idGenerator) {
		result.setResultId(idGenerator.generateId());
		String dept = userInfo.getPosition();
		result.setUnit(dept);
		List<Result> resultSearcher = resultRepo.findByCourseIdAndMatricNoAndUnit(result.getCourseId(), result.getMatricNo(), result.getUnit());
		if(rs.hasErrors()) {
			model.addAttribute("courses", courseRepo.findByDepartmentAndCourseSession(userInfo.getPosition(), userInfo.getSession()));
			model.addAttribute("students", studentRepo.findByDepartmentAndSession(userInfo.getPosition(), userInfo.getSession()));
			model.addAttribute("errorMessage", "Warning: You must correctly complete the result form before submitting!");
			return "add-result";
		} else if(resultSearcher.size() == 1) {
			model.addAttribute("courses", courseRepo.findByDepartmentAndCourseSession(userInfo.getPosition(), userInfo.getSession()));
			model.addAttribute("students", studentRepo.findByDepartmentAndSession(userInfo.getPosition(), userInfo.getSession()));
			model.addAttribute("errorMessage", "Warning: Sorry the result you are trying to input already exist!");
			return "add-result";
		}
		else {
			
			resultRepo.save(result);
			model.addAttribute("results", result);
			model.addAttribute("successMessage", "Result has successfully been inputed");
			return "result-List";
		}
		
	}
	
	@GetMapping("/editResult/{resultootsId}")
	public String showUpdatForm(@PathVariable("resultId") Integer resultId, Model model) {
		Result result = resultRepo.findById(resultId)
				.orElseThrow(()-> new IllegalArgumentException("invalid id:" + resultId));
		model.addAttribute("result", result);
		return "update-Result";
	}
	
	@PostMapping("/updateResult/{resultId}")
	public String updateResult(@SessionAttribute("userInfo") User userInfo, @PathVariable("resultId") Long resultId, @Valid Result result, BindingResult rs, Model model) {		
		List<Result> resultSearcher = resultRepo.findByCourseIdAndMatricNoAndUnit(result.getCourseId(), result.getMatricNo(), result.getUnit());
		if(rs.hasErrors()) {
			model.addAttribute("courses", courseRepo.findByDepartmentAndCourseSession(userInfo.getPosition(), userInfo.getSession()));
			model.addAttribute("students", studentRepo.findByDepartmentAndSession(userInfo.getPosition(), userInfo.getSession()));
			model.addAttribute("errorMessage", "Warning: You must correctly complete the result form before submitting!");
			return "add-result";
		} else if(resultSearcher.size() == 1) {
			model.addAttribute("courses", courseRepo.findByDepartmentAndCourseSession(userInfo.getPosition(), userInfo.getSession()));
			model.addAttribute("students", studentRepo.findByDepartmentAndSession(userInfo.getPosition(), userInfo.getSession()));
			model.addAttribute("errorMessage", "Warning: Sorry the result you are trying to input already exist!");
			return "add-result";
		} else {
			
			String dept = userInfo.getPosition();
			result.setUnit(dept);
			result.setResultId(resultId);
			resultRepo.save(result);
			model.addAttribute("results", result);
			model.addAttribute("successMessage", "The result has successfully been inputed");
			return "result-List";
		}
	}
	
	@GetMapping("/deleteResult/{resultId}")
	public String deleteResult(@PathVariable("resultId") Integer resultId, Model model) {
		Result result = resultRepo.findById(resultId)
				.orElseThrow(()-> new IllegalArgumentException("invalid id:" + resultId));
		resultRepo.delete(result);
		model.addAttribute("results", resultRepo.findAll());
		model.addAttribute("successMessage", "The result has successfully been deleted");
		return "results";
	}
}

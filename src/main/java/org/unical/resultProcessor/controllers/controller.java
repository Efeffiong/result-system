package org.unical.resultProcessor.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.unical.resultProcessor.dao.StudentRepository;
import org.unical.resultProcessor.dao.UserRepo;
import org.unical.resultProcessor.model.Student;
import org.unical.resultProcessor.model.User;

@Controller
public class controller {

	
	@Autowired
	StudentRepository studentRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@RequestMapping("/")
	public String home() {
		User defaultUser = userRepo.findByPosition("admin");
		
		if(defaultUser == null) {
		
		Long id = System.nanoTime();
		User userInfo = new User();
		
		userInfo.setUserId(id);
		userInfo.setRegNum("1234");
		userInfo.setFirstname("efeffiong");
		userInfo.setOthername("bassey");
		userInfo.setPassword("admin");
		userInfo.setPosition("admin");
		userInfo.setSession("2017/2018");
		userInfo.setUsername("admin");
		userInfo.setUserType("admin");
		userInfo.setSurname("ndemeno");
		userInfo.setSex("male");
		userInfo.setTitle("mr");
		userRepo.save(userInfo);
		return "home";
		}
		return "home";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/about")
	public String about() {
		return "about";
	}
	
	@GetMapping("/getForm")
	public String StudentForm(Student student) {
		return "form";
	}
	
	@RequestMapping("/addStudent")
	public String addStudent(Student student) {
		studentRepo.save(student);
		return "form";
	}
	
	@RequestMapping("/getStudents")
	public String findAll(Model model){
		model.addAttribute("students", studentRepo.findAll());
		
		return "studentsList";
	}
	
}

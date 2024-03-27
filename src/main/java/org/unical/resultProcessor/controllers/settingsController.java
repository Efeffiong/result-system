package org.unical.resultProcessor.controllers;


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
import org.unical.resultProcessor.dao.ClassesRepo;
import org.unical.resultProcessor.dao.GradeRepo;
import org.unical.resultProcessor.dao.SettingRepo;
import org.unical.resultProcessor.dao.StudentRepository;
import org.unical.resultProcessor.model.Setting;
import org.unical.resultProcessor.model.Student;
import org.unical.resultProcessor.model.User;

@Controller
public class settingsController {

	@Autowired
	ClassesRepo classesRepo;
	
	@Autowired
	GradeRepo gradeRepo;
	
	@Autowired
	SettingRepo settingRepo;
	
	@Autowired
	StudentRepository studentRepo;
	
	@GetMapping("/settings")
	public String settings(@SessionAttribute("userInfo") User userInfo, HttpServletRequest request) {
		request.setAttribute("classes", classesRepo.findAll());
		request.setAttribute("grades", gradeRepo.findAll());
		String dept = userInfo.getPosition();
		String session = userInfo.getSession();
		Setting pgdSetting = settingRepo.findByDeptAndSessionAndCourseType(dept, session, "PGD");
		Setting mscSetting = settingRepo.findByDeptAndSessionAndCourseType(dept, session, "MSc");
		Setting phdSetting = settingRepo.findByDeptAndSessionAndCourseType(dept, session, "PhD");
		request.setAttribute("pgdSetting", pgdSetting);
		request.setAttribute("mscSetting", mscSetting);
		request.setAttribute("phdSetting", phdSetting);
		return "settings";
	}
	
	@GetMapping("/studentSettings")
	public String studentSettings(@SessionAttribute("userInfo") User userInfo, HttpServletRequest request) {
		request.setAttribute("classes", classesRepo.findAll());
		request.setAttribute("grades", gradeRepo.findAll());
		String dept = userInfo.getPosition();
		String session = userInfo.getSession();
		String matricNo = userInfo.getRegNum();
		Student student = studentRepo.findByDepartmentAndSessionAndMatricNo(dept, session, matricNo);
		String studentType = student.getCourseType();
		Setting studentSetting = settingRepo.findByDeptAndSessionAndCourseType(dept, session, studentType);
		request.setAttribute("studentSettings", studentSetting);
		return "studentSetting";
	}
	
	@PostMapping("/inputSetting")
	public String inputSetting(@SessionAttribute("userInfo") User userInfo, @Valid Setting setting) {
		setting.setDept(userInfo.getPosition());
		setting.setSession(userInfo.getSession());
		
		settingRepo.save(setting);
		return "sign-in";
	}
	
	@GetMapping("/editSetting/{settingId}")
	public String showUpdatForm(@PathVariable("settingId") Integer settingId, Model model) {
		Setting setting = settingRepo.findById(settingId)
				.orElseThrow(()-> new IllegalArgumentException("invalid id:" + settingId));
		model.addAttribute("setting", setting);
		return "update-setting";
	}
	
	@PostMapping("/updateSetting/{setId}")
	public String updateUnit(@SessionAttribute("userInfo") User userInfo, HttpServletRequest request, @PathVariable("setId") Integer setId, Setting setting, BindingResult result, Model model) {		
		setting.setSettingId(setId);
		setting.setDept(userInfo.getPosition());
		setting.setSession(userInfo.getSession());
		setting.setNoOfFirstElectiveCourses(setting.getNoOfFirstElectiveCourses());
		setting.setNoOfSecondElectiveCourses(setting.getNoOfSecondElectiveCourses());
		setting.setCourseType(setting.getCourseType());
		settingRepo.save(setting);
		request.setAttribute("classes", classesRepo.findAll());
		request.setAttribute("grades", gradeRepo.findAll());
		String dept = userInfo.getPosition();
		String session = userInfo.getSession();
		Setting pgdSetting = settingRepo.findByDeptAndSessionAndCourseType(dept, session, "PGD");
		Setting mscSetting = settingRepo.findByDeptAndSessionAndCourseType(dept, session, "MSc");
		Setting phdSetting = settingRepo.findByDeptAndSessionAndCourseType(dept, session, "PhD");
		request.setAttribute("pgdSetting", pgdSetting);
		request.setAttribute("mscSetting", mscSetting);
		request.setAttribute("phdSetting", phdSetting);
		return "settings";
	}
}

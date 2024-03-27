package org.unical.resultProcessor.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.unical.resultProcessor.dao.SettingRepo;
import org.unical.resultProcessor.dao.UnitRepo;
import org.unical.resultProcessor.dao.UserRepo;
import org.unical.resultProcessor.model.Setting;
import org.unical.resultProcessor.model.Unit;
import org.unical.resultProcessor.model.User;
import org.unical.resultProcessor.services.IdGenerator;

@Controller
@SessionAttributes("userInfo")
public class signInController {

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	UnitRepo unitRepo;
	
	@Autowired
	SettingRepo settingRepo;
	
	@ModelAttribute("userInfo")
	public User setUpUserForm() {
		return new User();
	}
	
	@GetMapping("/sign-in")
	public String signInUser() {
		return "sign-in";
	}
	
	@GetMapping("/back")
	public String back(@SessionAttribute("userInfo") User userInfo) {
		if(userInfo.getUserType().equalsIgnoreCase("pg-coordinator")) {
			return "pgCoordinatorHomePage";
		}else if (userInfo.getUserType().equalsIgnoreCase("hod")) {
			return "hodHomePage";
		}else if (userInfo.getUserType().equalsIgnoreCase("student")) {
			return "studentHomePage";
		}else if (userInfo.getUserType().equalsIgnoreCase("admin")) {
			return "adminHomePage";
		}else if (userInfo.getUserType().equalsIgnoreCase("lecturer")) {
			return "lecturerHomePage";
		}else {
			return "sign-in";
		}
	}
	
	@PostMapping("/confirmLogin")
	public String confirmLogin(Model model, HttpSession httpSession, @ModelAttribute("username") String username,
    @ModelAttribute("password") String password, @ModelAttribute("userInfo") User userInfo, HttpServletRequest request) {
		if((username.isEmpty()) || (password.isEmpty())) {
			model.addAttribute("errorMessage", "Warning: You must input correct Username and Password before you csn login");
			return "sign-in";
		}
		User user = userRepo.findByUsernameAndPassword(username, password);
		if(user == null) {
			model.addAttribute("errorMessage", "Warning: Ooops! Wrong Username or Password!!!");
			return "sign-in";
		}
		
			if((!user.getUserType().equals(null)) && (user.getUserType().equalsIgnoreCase("pg-coordinator"))) {
				request.setAttribute("pgcs", userRepo.findByUsername(username));
				
				userInfo.setUserId(user.getUserId());
				userInfo.setRegNum(user.getRegNum());
				userInfo.setFirstname(user.getFirstname());
				userInfo.setOthername(user.getOthername());
				userInfo.setPassword(user.getPassword());
				userInfo.setPosition(user.getPosition());
				userInfo.setSession(user.getSession());
				userInfo.setUsername(user.getUsername());
				userInfo.setUserType(user.getUserType());
				userInfo.setSurname(user.getSurname());
				userInfo.setSex(user.getSex());
				userInfo.setTitle(user.getTitle());
				
				return "pgCoordinatorHomePage";
			}else if ((!user.getUserType().equals(null)) && (user.getUserType().equalsIgnoreCase("hod"))) {
				request.setAttribute("pgcs", userRepo.findByUsername(username));
				
				userInfo.setUserId(user.getUserId());
				userInfo.setRegNum(user.getRegNum());
				userInfo.setFirstname(user.getFirstname());
				userInfo.setOthername(user.getOthername());
				userInfo.setPassword(user.getPassword());
				userInfo.setPosition(user.getPosition());
				userInfo.setSession(user.getSession());
				userInfo.setUsername(user.getUsername());
				userInfo.setUserType(user.getUserType());
				userInfo.setSurname(user.getSurname());
				userInfo.setSex(user.getSex());
				userInfo.setTitle(user.getTitle());
				
				return "hodHomePage";
			}else if ((!user.getUserType().equals(null)) && (user.getUserType().equalsIgnoreCase("student"))) {
				request.setAttribute("pgcs", userRepo.findByUsername(username));
				
				userInfo.setUserId(user.getUserId());
				userInfo.setRegNum(user.getRegNum());
				userInfo.setFirstname(user.getFirstname());
				userInfo.setOthername(user.getOthername());
				userInfo.setPassword(user.getPassword());
				userInfo.setPosition(user.getPosition());
				userInfo.setSession(user.getSession());
				userInfo.setUsername(user.getUsername());
				userInfo.setUserType(user.getUserType());
				userInfo.setSurname(user.getSurname());
				userInfo.setSex(user.getSex());
				userInfo.setTitle(user.getTitle());
				
				return "studentHomePage";
			}else if ((!user.getUserType().equals(null)) && (user.getUserType().equalsIgnoreCase("admin"))) {
				request.setAttribute("pgcs", userRepo.findByUsername(username));
				
				userInfo.setUserId(user.getUserId());
				userInfo.setRegNum(user.getRegNum());
				userInfo.setFirstname(user.getFirstname());
				userInfo.setOthername(user.getOthername());
				userInfo.setPassword(user.getPassword());
				userInfo.setPosition(user.getPosition());
				userInfo.setSession(user.getSession());
				userInfo.setUsername(user.getUsername());
				userInfo.setUserType(user.getUserType());
				userInfo.setSurname(user.getSurname());
				userInfo.setSex(user.getSex());
				userInfo.setTitle(user.getTitle());
				
				
				return "adminHomePage";
			}else if ((!user.getUserType().equals(null)) && (user.getUserType().equalsIgnoreCase("lecturer"))) {
				request.setAttribute("pgcs", userRepo.findByUsername(username));
				
				userInfo.setUserId(user.getUserId());
				userInfo.setRegNum(user.getRegNum());
				userInfo.setFirstname(user.getFirstname());
				userInfo.setOthername(user.getOthername());
				userInfo.setPassword(user.getPassword());
				userInfo.setPosition(user.getPosition());
				userInfo.setSession(user.getSession());
				userInfo.setUsername(user.getUsername());
				userInfo.setUserType(user.getUserType());
				userInfo.setSurname(user.getSurname());
				userInfo.setSex(user.getSex());
				userInfo.setTitle(user.getTitle());
				
				
				return "lecturerHomePage";
			}else if (user.equals("null")) {
				return "sign-in";
			}
			else {
				return "sign-in";
			}
						
		

		
	}
	
	@GetMapping("/example")
	public String example() {
		return "example";
	}
	
	@GetMapping("/newUserForm")
	public String showSignupForm(User user, Unit unit, HttpServletRequest request) {
		request.setAttribute("users", userRepo.findAll());
		request.setAttribute("units", unitRepo.findAll());
		return "newUserForm";
	}
	
	@GetMapping("/studentNewUser")
	public String studentNewUser(@SessionAttribute("userInfo") User userInfo, User user, Unit unit, HttpServletRequest request) {
		request.setAttribute("users", userRepo.findAll());
		String studentUnit = userInfo.getPosition();
		request.setAttribute("units", unitRepo.findByUnitDept(studentUnit));
		return "studentNewUserForm";
	}
	
	@PostMapping("/saveStudentNewUser")
	public String studentNewUser(@ModelAttribute("password") String password, 
			@ModelAttribute("password2") String password2, IdGenerator idGenerator,
			@Valid User user, BindingResult result, Model model, 
			HttpServletRequest request, @SessionAttribute("userInfo") User userInfo) {
			List<User> userSearcher = userRepo.findByFirstnameAndSurnameAndOthernameAndSessionAndPositionAndUserType(user.getFirstname(), user.getSurname(), user.getOthername(), userInfo.getSession(), userInfo.getPosition(), user.getUserType());
		if(result.hasErrors()) {
			String studentUnit = userInfo.getPosition();
			request.setAttribute("units", unitRepo.findByUnitDept(studentUnit));
			model.addAttribute("errorMessage", "Warning: You must correctly complete user form before submitting!");
			return "studentNewUserForm";
		} else if(!(password.equals(password2))) {
			String studentUnit = userInfo.getPosition();
			model.addAttribute("errorMessage", "Warning: Password and re-typed password must be the same!");
			request.setAttribute("units", unitRepo.findByUnitDept(studentUnit));
			return "studentNewUserForm";
		} else if(userSearcher.size() == 1) {
			request.setAttribute("users", userRepo.findAll());
			String studentUnit = userInfo.getPosition();
			model.addAttribute("errorMessage", "Warning: User has already been registered!");
			request.setAttribute("units", unitRepo.findByUnitDept(studentUnit));
			return "studentNewUserForm";
			
		}
		else {
			user.setUserId(idGenerator.generateId());
			user.setPosition(userInfo.getPosition());
			user.setSession(userInfo.getSession());
			String session = userInfo.getSession();
			String dept = userInfo.getPosition();
			String student = "student";
			String lecturer = "lecturer";
			userRepo.save(user);
			model.addAttribute("studentUsers", userRepo.findBySessionAndPositionAndUserType(session, dept, student));
			model.addAttribute("lecturerUsers", userRepo.findBySessionAndPositionAndUserType(session, dept, lecturer));
			model.addAttribute("successMessage", "User has successfully been registered");
			return "lecturer-studentList";
			
		}
	}
	
	@GetMapping("/studenNewUserList")
	public String studentNewUserList(Model model, @SessionAttribute("userInfo") User userInfo) {
		String session = userInfo.getSession();
		String dept = userInfo.getPosition();
		String student = "student";
		String lecturer = "lecturer";
		model.addAttribute("studentUsers", userRepo.findBySessionAndPositionAndUserType(session, dept, student));
		model.addAttribute("lecturerUsers", userRepo.findBySessionAndPositionAndUserType(session, dept, lecturer));
		return "lecturer-studentList";
	}
	
	@PostMapping("/newUser")
	public String newUser(@ModelAttribute("password") String password, 
			@ModelAttribute("password2") String password2, @Valid User user, 
			BindingResult result, Model model, HttpServletRequest request,
			@ModelAttribute("position") String department, 
			@ModelAttribute("session") String session) {
		List<User> userSearcher = userRepo.findByFirstnameAndSurnameAndOthernameAndSessionAndPositionAndUserType(user.getFirstname(), user.getSurname(), user.getOthername(), session, department, user.getUserType());
		if(result.hasErrors()) {
			request.setAttribute("users", userRepo.findAll());
			request.setAttribute("units", unitRepo.findAll());
			model.addAttribute("errorMessage", "Warning: You must correctly complete the form before submitting!");
			return "newUserForm";
		} else if(!(password.equals(password2))) {
			request.setAttribute("users", userRepo.findAll());
			request.setAttribute("units", unitRepo.findAll());
			model.addAttribute("errorMessage", "Warning: password and re-typed password must be the same");
			return "newUserForm";
		} else if(userSearcher.size() == 1) {
			request.setAttribute("users", userRepo.findAll());
			request.setAttribute("units", unitRepo.findAll());
			model.addAttribute("errorMessage", "Warning: User had already been registered!");
			return "newUserForm";
		}
		else {
			Long id = System.nanoTime();
			
			user.setUserId(id);
			userRepo.save(user);
			Setting pgdSetting = new Setting();
			pgdSetting.setDept(department);
			pgdSetting.setSession(session);
			pgdSetting.setNoOfFirstElectiveCourses(0);
			pgdSetting.setNoOfSecondElectiveCourses(0);
			pgdSetting.setCourseType("PGD");
			
			Setting mscSetting = new Setting();
			mscSetting.setDept(department);
			mscSetting.setSession(session);
			mscSetting.setNoOfFirstElectiveCourses(0);
			mscSetting.setNoOfSecondElectiveCourses(0);
			mscSetting.setCourseType("MSc");
			
			Setting phdSetting = new Setting();
			phdSetting.setDept(department);
			phdSetting.setSession(session);
			phdSetting.setNoOfFirstElectiveCourses(0);
			phdSetting.setNoOfSecondElectiveCourses(0);
			phdSetting.setCourseType("PhD");
			
			Setting autoSettings = settingRepo.findByDeptAndSessionAndCourseType(department, session, "PGD");
			if(autoSettings == null) {
				settingRepo.save(pgdSetting);
				settingRepo.save(mscSetting);
				settingRepo.save(phdSetting);
			}
			model.addAttribute("successMessage", "User has successfully been registered");
			model.addAttribute("users", userRepo.findAll());
			return "userList";
		}
	}
	
	@GetMapping("/viewUser")
	public String viewUser(Model model) {
		model.addAttribute("users", userRepo.findAll());
		return "userList";
		
	}
	
	@GetMapping("/editUser/{userId}")
	public String showUpdatForm(@PathVariable("userId") Long id, Model model, HttpServletRequest request) {
		User user = userRepo.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("invalid id:" + id));
		model.addAttribute("user", user);
		request.setAttribute("units", unitRepo.findAll());
		request.setAttribute("users", userRepo.findAll());
		return "update-user";
	}
	
	@PostMapping("/updateUser/{userId}")
	public String updateUser(@SessionAttribute("userInfo") User userInfo, @PathVariable("userId") Long id, @Valid User user, BindingResult result, Model model) {
		if(!(result.hasErrors())) {
			user.setUserId(id);
			String session = userInfo.getSession();
			String dept = userInfo.getPosition();
			userRepo.save(user);
			model.addAttribute("successMessage", "User has successfully been updated");
			model.addAttribute("users", userRepo.findBySessionAndPositionAndUserType(session, dept, "student"));
			return "home";
		} else {
		user = userRepo.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("invalid id:" + id));
		model.addAttribute("user", user);
		model.addAttribute("units", unitRepo.findAll());
		model.addAttribute("users", userRepo.findAll());
		model.addAttribute("errorMessage", "Warning: You must correctly complete the form before submitting!");
		return "update-user";
		}
	}
	
	@GetMapping("/deleteUser/{userId}")
	public String deleteUser(@PathVariable("userId") Long id, Model model) {
		User user = userRepo.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("invalid id:" + id));
		userRepo.delete(user);
		model.addAttribute("users", userRepo.findAll());
		return "userList";
	}
	
	@GetMapping("/userProfile")
	public String userProfile(@SessionAttribute("userInfo") User userInfo, Model model, HttpServletRequest request) {
		User user = userRepo.findByUserId(userInfo.getUserId());
		model.addAttribute("user", user);
		request.setAttribute("units", userInfo.getPosition());
		request.setAttribute("users", userRepo.findAll());
		return "updateUserProfile";
	}
	
	@PostMapping("/updateUserProfile/{userId}")
	public String updateUserProfile(@SessionAttribute("userInfo") User userInfo, @PathVariable("userId") Long id, @Valid User user, BindingResult result, Model model) {
		if(!(result.hasErrors())) {
			user.setUserId(id);
			String session = userInfo.getSession();
			String dept = userInfo.getPosition();
			model.addAttribute("successMessage", "User has successfully been updated");
			userRepo.save(user);
			model.addAttribute("users", userRepo.findBySessionAndPositionAndUserType(session, dept, "student"));
			return "home";
		} else {
		user = userRepo.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("invalid id:" + id));
		model.addAttribute("user", user);
		model.addAttribute("units", unitRepo.findAll());
		model.addAttribute("users", userRepo.findAll());
		model.addAttribute("errorMessage", "Warning: You must correctly complete the form before submitting!");
		return "update-user";
		}
	}
}

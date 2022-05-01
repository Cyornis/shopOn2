package com.cdac.controller;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cdac.entity.User;
import com.cdac.service.UserService;

@Controller
public class UserController {
	
	@Autowired
    private UserService service;
	
	@GetMapping("registeruser")
	private String showRegistrationForrm(Model model) {
		
		model.addAttribute("student",new User());
		return "registeruser";
	}
	
	@PostMapping("userregistersubmit")
	public String processRegistration(@ModelAttribute("user") User user) {
		
		//BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		//String encodeString = encoder.encode(student.getPassword());
		//student.setPassword(encodeString);
		service.save(user);
		
		return "student_registration";
	}
	
	@GetMapping("/admin/viewstudent")
	public String viewHomePage(Model model, @Param("name") String name) {
	    List<User> listStudent = service.listAll(name);
	    model.addAttribute("listStudent", listStudent);
	     
	    return "ViewStudent";
	}
	
//	@RequestMapping("/admin/delete/{Id}")
//	public String deleteProduct(@PathVariable(name = "Id") int id) {
//	    service.delete(id);
//	    return "ViewStudent";       
//	}
//	
//	@PostMapping("/admin/save")
//	public String updateStudent(@ModelAttribute("student") Student student) {
//	    service.save(student);
//	    return "ViewStudent";
//	}
//	
//	@RequestMapping("/admin/edit/{Id}")
//	public ModelAndView showEditProductPage(@PathVariable(name = "Id") int id) {
//	    ModelAndView mav = new ModelAndView("EditStudent");
//	    Student student = service.get(id);
//	    mav.addObject("student", student);
//	     
//	    return mav;
//	}
	
	@GetMapping("/userlogin")
	public String ulogin(Model model) {
	model.addAttribute("student", new User());
	return "userlogin";
	}
	
	@PostMapping("/userlogin")
	public String loginUser(@RequestParam(value = "email") String email, @RequestParam("password") String password,
			HttpServletRequest request, Model model) {
		//String page = null;
		HttpSession session = request.getSession();
		User user = service.findByEmailAndPassword(email, password);
		model.addAttribute("user", user);
		if(Objects.nonNull(user))  {
			session.setAttribute("currentUser", user);
			//page = "redirect:/student_dashboard";
			return"redirect:/user_dashboard";
		} else {
			
			
			return"redirect:/userlogin";
		}
		//return page;
	}
	
	@GetMapping("/user_dashboard")
	public String getDashboard(@ModelAttribute("student")User user, Model model, 
														HttpServletRequest request) 
	{
		HttpSession session = request.getSession();
		user = (User) session.getAttribute("currentUser");
		model.addAttribute("student", user);
		if (user == null)
			return "redirect:/studentlogin";
		else {
			return "student_dashboard";
	    }
		
	}

	@RequestMapping("/user_profile")
	public String profile(@ModelAttribute("user") User user, Model model,
							HttpServletRequest request) 
	{
		HttpSession session = request.getSession();
		user = (User) session.getAttribute("currentUser");
		model.addAttribute("user", user);
		return "user_profile";
	}
}



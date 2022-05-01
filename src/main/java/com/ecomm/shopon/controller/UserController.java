package com.ecomm.shopon.controller;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ecomm.shopon.entity.User;


@Controller
public class UserController {
	
	@Autowired
    private UserService service;
	
	
	
	@PostMapping("/owner/registeruser")
	public String processRegistration(@ModelAttribute("student") User user) {
		
		service.save(user);
		
		return "student_registration";
	}
	
	@GetMapping("/owner/showuser")
	public String viewHomePage(Model model, @Param("name") String name) {
	    List<User> listStudent = service.listAll(name);
	    model.addAttribute("listStudent", listStudent);
	     
	    return "ViewStudent";
	}
	
	@RequestMapping("/owner/delete/{Id}")
	public String deleteProduct(@PathVariable(name = "Id") int id) {
	    service.delete(id);
	    return "ViewStudent";       
	}
	
	@PostMapping("/owner/save")
	public String updateStudent(@ModelAttribute("student") User user) {
	    service.save(user);
	    return "ViewStudent";
	}
	
	@RequestMapping("/owner/edit/{Id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "Id") int id) {
	    ModelAndView mav = new ModelAndView("EditStudent");
	    User user = service.get(id);
	    mav.addObject("student", user);
	     
	    return mav;
	}
	
	@GetMapping("/userlogin")
	public String slogin(Model model) {
	model.addAttribute("student", new User());
	return "userlogin";
	}
	
	@PostMapping("/userloginadd")
	public String loginUser(@RequestParam(value = "email") String email, @RequestParam("password") String password,
			HttpServletRequest request, Model model) {

		HttpSession session = request.getSession();
		User user = service.findByEmailAndPassword(email, password);
		model.addAttribute("student", user);
		if(Objects.nonNull(user))  {
			session.setAttribute("currentUser", user);
			//page = "redirect:/student_dashboard";
			return"redirect:/user_main";
			
		} else {
		
			
			return"redirect:/userlogin";
		}
		//return page;
	}
	
	@GetMapping("/user_main")
	public String getDashboard(@ModelAttribute("user")User user, Model model, 
														HttpServletRequest request) 
	{
		HttpSession session = request.getSession();
		student = (User) session.getAttribute("currentUser");
		model.addAttribute("user", user);
		if (user == null)
			return "redirect:/userlogin";
		else {
			return "user_main";
	    }
		
	}

	@RequestMapping("/student_profile")
	public String profile(@ModelAttribute("student") Student student, Model model,
							HttpServletRequest request) 
	{
		HttpSession session = request.getSession();
		student = (Student) session.getAttribute("currentUser");
		model.addAttribute("student", student);
		return "student_profile";
	}
}



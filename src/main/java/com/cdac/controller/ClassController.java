package com.cdac.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cdac.entity.Class;
import com.cdac.service.ClassService;


@Controller
public class ClassController {
	
	@Autowired
    private ClassService service;
	
	@GetMapping("/admin/add_class")
	private String showClassForm(Model model) {
		//System.out.println("inside register student");
		model.addAttribute("class", new Class());
		return "AddClass";
	}

	@PostMapping("/admin/add_class")
	public String processRegistration(@ModelAttribute("class") Class classess) {
		service.save(classess);
		return "AddClass";
	}
	@GetMapping("/admin/viewclass")
	public String viewHomePage(Model model) {
	    List<Class> listClass = service.listAll();
	    model.addAttribute("listClass", listClass);
	     
	    return "ViewClass";
	}
	
	@RequestMapping("/admin/class_delete/{class_id}")
	public String deleteClass(@PathVariable(name = "class_id") int id) {
	    service.delete(id);
	    return "admin_dashboard";       
	}
	
	@PostMapping("/admin/class_save")
	public String updateClass(@ModelAttribute("class") Class classess) {
		
	    service.save(classess);
	    return "ViewClass";
	}
	
	@RequestMapping("/admin/class_edit/{class_id}")
	public ModelAndView showEditClassPage(@PathVariable(name = "class_id") int id) {
	    ModelAndView mav = new ModelAndView("EditClass");
	    Class classess = service.get(id);
	    mav.addObject("class", classess);
	     
	    return mav;
	}
}

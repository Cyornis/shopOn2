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
import com.cdac.entity.Subject;
import com.cdac.service.SubjectService;


@Controller
public class SubjectController {
	
	@Autowired
    private SubjectService service;
	
	@GetMapping("/admin/add_subject")
	private String showSubjectForm(Model model) {
		//System.out.println("inside register student");
		model.addAttribute("subject", new Subject());
		return "AddSubject";
	}
	
	@PostMapping("/admin/subject")
	public String processRegistration(@ModelAttribute("subject") Subject subject) {
		service.save(subject);
		return "admin_portal";
	}
	
	@GetMapping("/admin/viewsubject")
	public String viewHomePage(Model model) {
	    List<Subject> listSubject = service.listAll();
	    model.addAttribute("listSubject", listSubject);
	     
	    return "ViewSubject";
	}
	
	@RequestMapping("/admin/subject_delete/{subject_id}")
	public String deleteSubject(@PathVariable(name = "subject_id") int id) {
	    service.delete(id);
	    return "admin_portal";       
	}
	
	@PostMapping("/admin/subject_save")
	public String updateSubject(@ModelAttribute("subject") Subject subject) {
	    service.save(subject);
	     
	    return "admin_portal";
	}
	
	@RequestMapping("/admin/subject_edit/{subject_id}")
	public ModelAndView showEditClassPage(@PathVariable(name = "subject_id") int id) {
	    ModelAndView mav = new ModelAndView("EditSubject");
	    Subject subject = service.get(id);
	    mav.addObject("subject", subject);
	     
	    return mav;
	}
	
	
}

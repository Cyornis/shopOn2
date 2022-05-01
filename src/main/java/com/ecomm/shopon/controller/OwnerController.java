package com.ecomm.shopon.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecomm.shopon.repository.OwnerRepository;

	@Controller
public class OwnerController {

	@Autowired
	private OwnerRepository ownerRepo;
	
	@GetMapping("/home")
	public String home() {
		return "index";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}
	

	@GetMapping("/register")
	private String showSignUp(Model model) {
		model.addAttribute("owner",new Admin());
		return "signupPageOwner";
	}
	@PostMapping("/register_credential")
	public String processRegistration(Admin admin) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodeString = encoder.encode(admin.getPassword());
		admin.setPassword(encodeString);
		ownerRepo.save(admin);
		return "next_register";
	}
	@RequestMapping("/owner/home")
	public String viewDashboard(Model model, Principal principal) {
		String username=principal.getName();
		Admin owner=ownerRepo.findByEmail(username);
		model.addAttribute("owner",owner);
		return "ownerMain";
		
	}
	@RequestMapping("/owner/profile")
	public String profile(Model model, Principal principal) {
		
		String username=principal.getName();
		Admin owner=ownerRepo.findByEmail(username);
		model.addAttribute("owner",owner);
		
		return "OwnerProfile";
	}
	
	@GetMapping("/forgotPassword")
	public String forgotpassword(Model model) {
		return "forgotPasswordPage";
	}
	
	
}

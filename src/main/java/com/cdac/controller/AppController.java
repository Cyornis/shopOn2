package com.cdac.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cdac.entity.Merchant;
import com.cdac.repository.*;


@Controller
public class AppController {
	

	@Autowired
	private MerchantRepository mRepository;
	
	@GetMapping("/home")
	public String home() {
		return "index";
	}
	
	@GetMapping("/merchantlogin")
	public String login(Model model) {
		return "merchantlogin";
	}
	

	@GetMapping("/registermerchant")
	private String showSignUp(Model model) {
		model.addAttribute("merchant",new Merchant());

		return "registermerchant";
	}
	@PostMapping("/merchantregistersubmit")
	public String processRegistration(Merchant merchant) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodeString = encoder.encode(merchant.getPassword());
		merchant.setPassword(encodeString);
		mRepository.save(merchant);
		return "register_success";
	}
	@RequestMapping("/merchant/home")
	public String viewDashboard(Model model, Principal principal) {
		String username=principal.getName();
		Merchant merchant=mRepository.findByEmail(username);

		model.addAttribute("merchant",merchant);
		return "admin_dashboard";
		
	}
	@RequestMapping("/merchant/profile")
	public String profile(Model model, Principal principal) {
		String username=principal.getName();
		Merchant merchant=mRepository.findByEmail(username);

		model.addAttribute("merchant",merchant);
		
		return "adminProfile";
	}
	
	@GetMapping("/forgotPassword")
	public String forgotpassword(Model model) {
		return "forgotPassword";
	}
	
}

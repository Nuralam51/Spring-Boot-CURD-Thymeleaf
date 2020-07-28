package com.example.StudentDiary.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.StudentDiary.entity.Users;
import com.example.StudentDiary.service.UsersService;

@Controller
public class RegistrationController {
	
	@Autowired
	private UsersService usersService;

	public RegistrationController(UsersService theUsersService) {
		usersService = theUsersService;
	}

	@GetMapping("/register")
	public String register(Model theModel) {
		Users theUsers = new Users();
		theModel.addAttribute("users", theUsers);
		return "registration";
	}

	@PostMapping("/register")
	public String saveUser(@ModelAttribute("users") @Valid Users theUsers, BindingResult result, Model theModel) {
		if(result.hasErrors()) {
			return "registration";
		}
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		theUsers.setPassword(encoder.encode(theUsers.getPassword()));
		usersService.save(theUsers);
		
		return "redirect:/login";
	}

}

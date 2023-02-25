package com.myproject.planetland.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myproject.planetland.dto.UserDto;

@Controller
@RequestMapping("/")
public class MainController {

	@GetMapping
	public String home() {
		return "main";
	}

	@GetMapping("login")
	public String loginForm() {
		return "loginForm";
	}

	@GetMapping("signup")
	public String signUpForm(Model model) {
		model.addAttribute("userDto", new UserDto());
		return "signupForm";
	}

	// @PostMapping("signup")
	// public String signUpForm2(@ModelAttribute UserDto userDto) {
	//
	// }
}

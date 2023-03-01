package com.myproject.planetland.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myproject.planetland.domain.User;
import com.myproject.planetland.dto.UserJoinDto;
import com.myproject.planetland.mapper.UserMapper;
import com.myproject.planetland.repository.UserRepository;
import com.myproject.planetland.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
@Slf4j
public class MainController {

	@Autowired UserMapper mapper;
	@Autowired UserRepository userRepository;
	@Autowired UserService userService;

	@GetMapping
	public String home() {
		return "main";
	}

	@GetMapping("login")
	public String loginForm() {
		return "loginForm";
	}

	@GetMapping("join")
	public String joinForm(Model model) {
		model.addAttribute("userJoinDto", new UserJoinDto());
		return "joinForm";
	}

	@PostMapping("join")
	public String joinForm(@ModelAttribute @Valid UserJoinDto userJoinDto, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			log.info("errors = {}", bindingResult);
			return "joinForm";
		}

		try {
			UserJoinDto res = userService.createUser(userJoinDto);
			User user = mapper.userJoinDtoToModel(res);
			userRepository.save(user);
		} catch (IllegalArgumentException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "joinForm";
		}
		return "redirect:/";
	}
}

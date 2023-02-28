package com.myproject.planetland.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myproject.planetland.dto.PlanetDto;
import com.myproject.planetland.service.PlanetService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("adminPage")
@RequiredArgsConstructor
public class PlanetController {

	private final PlanetService planetService;

	@GetMapping("/add")
	public String createPlanetForm(Model model) {
		model.addAttribute("planetDto", new PlanetDto());
		return "adminPageForm";
	}

	@PostMapping("/add")
	public String createPlanet(@ModelAttribute PlanetDto planetDto) {
		planetService.createPlanet(planetDto);

		return "redirect:/adminPage/add";
	}
}

package com.myproject.planetland.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myproject.planetland.dto.PlanetListDto;
import com.myproject.planetland.service.PlanetService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MainController {

	private final PlanetService planetService;

	@GetMapping(value = {"/", "/sort"})
	public String home(@RequestParam(required = false, defaultValue = "기본 순") String keyword, Model model) {
		List<PlanetListDto> planetListDto = planetService.getAllPlanet(keyword);
		model.addAttribute("planetListDto", planetListDto);
		model.addAttribute("keyword", keyword);
		return "main";
	}
}

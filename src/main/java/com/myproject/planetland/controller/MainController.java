package com.myproject.planetland.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myproject.planetland.domain.Planet;
import com.myproject.planetland.dto.PlanetListDto;
import com.myproject.planetland.mapper.PlanetMapper;
import com.myproject.planetland.service.PlanetService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
public class MainController {

	@Autowired PlanetService planetService;
	@Autowired PlanetMapper mapper;

	@GetMapping
	public String home(Model model) {
		List<Planet> planetList = planetService.getAllPlanet();
		List<PlanetListDto> planetListDto = mapper.ModelToDtoList(planetList);

		model.addAttribute("planetListDto", planetListDto);

		return "main";
	}
}

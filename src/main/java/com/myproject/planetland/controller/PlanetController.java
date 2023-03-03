package com.myproject.planetland.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myproject.planetland.constants.PlanetStatus;
import com.myproject.planetland.domain.Planet;
import com.myproject.planetland.dto.AddPlanetDto;
import com.myproject.planetland.dto.PlanetDto;
import com.myproject.planetland.mapper.PlanetMapper;
import com.myproject.planetland.repository.PlanetRepository;
import com.myproject.planetland.service.PlanetService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/planets")
@RequiredArgsConstructor
@Slf4j
public class PlanetController {

	private final PlanetService planetService;
	private final PlanetRepository planetRepository;
	private final PlanetMapper mapper;

	@ModelAttribute("planetStatuses")
	public PlanetStatus[] planetStatuses() {
		return PlanetStatus.values();
	}

	@GetMapping("/add")
	public String createPlanetForm(Model model) {
		model.addAttribute("addPlanetDto", new AddPlanetDto());
		return "addForm";
	}

	@PostMapping("/add")
	public String createPlanet(@ModelAttribute @Valid AddPlanetDto addPlanetDto, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			log.info("errorMessage = {}", bindingResult);
			return "addForm";
		}

		try {
			AddPlanetDto res = planetService.createPlanet(addPlanetDto);
			Planet planet = mapper.addPlanetDtoToModel(addPlanetDto);
			Planet savedPlanet = planetRepository.save(planet);
			redirectAttributes.addAttribute("planetName", savedPlanet.getPlanetName());
		} catch (IllegalArgumentException e) {
			log.info("errorMessage = {}", e.getMessage());
			model.addAttribute("errorMessage", e.getMessage());

			return "addForm";
		}

		return "redirect:/planets/{planetName}/detail";
	}
	@GetMapping("/{planetName}/detail")
	public String getPlanet(@PathVariable String planetName, Model model) {
		Planet planet = planetService.getPlanet(planetName);
		PlanetDto planetDto = mapper.ModelToDto(planet);

		if (planet.getUser() == null) {
			planetDto.setOwner("없음");
		} else {
			planetDto.setOwner(planet.getUser().getUserName());
		}
		model.addAttribute("planetDto", planetDto);

		return "planet";
	}
}

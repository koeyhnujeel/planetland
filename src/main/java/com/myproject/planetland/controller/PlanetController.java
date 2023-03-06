package com.myproject.planetland.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myproject.planetland.auth.CustomUserDetails;
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

	@GetMapping("/{planetId}/detail")
	public String getPlanet(@PathVariable Long planetId, @AuthenticationPrincipal CustomUserDetails user, Model model) {
		Planet planet = planetService.getPlanet(planetId);
		PlanetDto planetDto = mapper.ModelToDto(planet);

		if (planet.getUser() != null) {
			planetDto.setOwner(planet.getUser().getUserName());
		}
		if (user != null) {
			model.addAttribute("userAsset", user.getUser().getAsset());
		}
		model.addAttribute("planetDto", planetDto);
		return "planet";
	}

	@GetMapping("/add")
	public String addForm(Model model) {
		model.addAttribute("addPlanetDto", new AddPlanetDto());
		return "addForm";
	}

	@PostMapping("/add")
	public String addPlanet(@ModelAttribute @Valid AddPlanetDto addPlanetDto, BindingResult bindingResult,
		Model model, RedirectAttributes redirectAttributes, MultipartFile imgFile) throws IOException {

		if (bindingResult.hasErrors()) {
			log.info("errorMessage = {}", bindingResult);
			return "addForm";
		}

		try {
			AddPlanetDto res = planetService.createPlanet(addPlanetDto, imgFile);
			redirectAttributes.addAttribute("planetName", res.getPlanetId());
		} catch (IllegalArgumentException e) {
			log.info("errorMessage = {}", e.getMessage());
			model.addAttribute("errorMessage", e.getMessage());
			return "addForm";
		}
		return "redirect:/planets/{planeId}/detail";
	}

	@GetMapping("/{planetId}/edit")
	public String editForm(@PathVariable Long planetId, Model model) {
		Planet planet = planetService.getPlanet(planetId);
		AddPlanetDto addPlanetDto = mapper.ModelToAddPlanetDto(planet);
		model.addAttribute("addPlanetDto", addPlanetDto);
		return "editForm";
	}

	@PostMapping("/{planetId}/edit")
	public String editPlanet(@PathVariable Long planetId, @ModelAttribute @Valid AddPlanetDto addPlanetDto,
		BindingResult bindingResult,
		Model model, RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			log.info("errorMessage = {}", bindingResult);
			return "editForm";
		}

		try {
			AddPlanetDto updatePlanetDto = planetService.updatePlanet(planetId, addPlanetDto);
			redirectAttributes.addAttribute("planetId", updatePlanetDto.getPlanetId());
		} catch (IllegalArgumentException e) {
			log.info("errorMessage = {}", e.getMessage());
			model.addAttribute("errorMessage", e.getMessage());
			return "editForm";
		}
		return "redirect:/planets/{planetId}/detail";
	}

	@GetMapping("/{planetId}/delete")
	public String deletePlanet(@PathVariable Long planetId) throws IOException {
		planetService.deletePlanet(planetId);
		return "redirect:/";
	}

	@GetMapping("/{planetId}/buy")
	public String buyPlanet(@PathVariable Long planetId, @AuthenticationPrincipal CustomUserDetails user, Model model,
		RedirectAttributes redirectAttributes) {

		planetService.buyPlanet(user.getUsername(), planetId);
		redirectAttributes.addAttribute("planetId", planetId);
		return "redirect:/planets/{planetId}/detail";
	}
}

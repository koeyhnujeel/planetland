package com.myproject.planetland.controller;

import java.io.IOException;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myproject.planetland.auth.CustomUserDetails;
import com.myproject.planetland.constants.PlanetStatus;
import com.myproject.planetland.domain.User;
import com.myproject.planetland.dto.AddPlanetDto;
import com.myproject.planetland.dto.PlanetDto;
import com.myproject.planetland.dto.PriceHisDto;
import com.myproject.planetland.service.PlanetService;
import com.myproject.planetland.service.PriceHisService;
import com.myproject.planetland.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/planets")
@RequiredArgsConstructor
@Slf4j
public class PlanetController {

	private final PlanetService planetService;
	private final PriceHisService priceHisService;
	private final UserService userService;

	@ModelAttribute("planetStatuses")
	public PlanetStatus[] planetStatuses() {
		return PlanetStatus.values();
	}

	@GetMapping("/{planetId}/detail")
	public String getPlanet(@PathVariable Long planetId, @AuthenticationPrincipal CustomUserDetails user, Model model) {
		PlanetDto planetDto = planetService.getPlanetDetail(planetId);
		if (user != null) {
			User resUser = userService.getUserByUserName(user.getUsername());
			model.addAttribute("userName", resUser.getUserName());
			model.addAttribute("userAsset", resUser.getAsset());
		}
		List<PriceHisDto> priceHisList = priceHisService.getPriceHisList(planetId);
		model.addAttribute("priceHisList",priceHisList);
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
			redirectAttributes.addAttribute("planetId", res.getPlanetId());
		} catch (IllegalArgumentException e) {
			log.info("errorMessage = {}", e.getMessage());
			model.addAttribute("errorMessage", e.getMessage());
			return "addForm";
		}
		return "redirect:/planets/{planetId}/detail";
	}

	@GetMapping("/{planetId}/edit")
	public String editForm(@PathVariable Long planetId, Model model) {
		AddPlanetDto addPlanetDto = planetService.getEditPlanet(planetId);
		model.addAttribute("addPlanetDto", addPlanetDto);
		return "editForm";
	}

	@PostMapping("/{planetId}/edit")
	public String editPlanet(@PathVariable Long planetId, @ModelAttribute @Valid AddPlanetDto addPlanetDto,
		BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, MultipartFile imgFile) throws
		IOException {

		if (bindingResult.hasErrors()) {
			log.info("errorMessage = {}", bindingResult);
			return "editForm";
		}

		try {
			AddPlanetDto updatePlanetDto = planetService.updatePlanet(planetId, addPlanetDto, imgFile);
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
	public String buyPlanet(@PathVariable Long planetId, @AuthenticationPrincipal CustomUserDetails user, RedirectAttributes redirectAttributes) {
		planetService.buyPlanet(user.getUsername(), planetId);
		redirectAttributes.addAttribute("planetId", planetId);
		return "redirect:/planets/{planetId}/detail";
	}

	@PostMapping("/{planetId}/sell")
	public String sellPlanet(@PathVariable Long planetId, @RequestParam int price, RedirectAttributes redirectAttributes) {
		planetService.sellPlanet(planetId, price);
		redirectAttributes.addAttribute("planetId", planetId);
		return "redirect:/planets/{planetId}/detail";
	}

	@GetMapping("/{planetId}/cancelSell")
	public String cancelSellPlanet(@PathVariable Long planetId, RedirectAttributes redirectAttributes) {
		planetService.cancelSellPlanet(planetId);
		redirectAttributes.addAttribute("planetId", planetId);
		return "redirect:/planets/{planetId}/detail";
	}
}

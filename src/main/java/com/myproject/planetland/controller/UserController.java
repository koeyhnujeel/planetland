package com.myproject.planetland.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myproject.planetland.auth.CustomUserDetails;
import com.myproject.planetland.domain.OrderHis;
import com.myproject.planetland.domain.Planet;
import com.myproject.planetland.domain.User;
import com.myproject.planetland.dto.MyPlanetsDto;
import com.myproject.planetland.dto.OrderHisDto;
import com.myproject.planetland.dto.SellPlanetDto;
import com.myproject.planetland.dto.UpgradePlanetDto;
import com.myproject.planetland.dto.UserJoinDto;
import com.myproject.planetland.mapper.UserMapper;
import com.myproject.planetland.repository.UserRepository;
import com.myproject.planetland.service.OrderHisService;
import com.myproject.planetland.service.PlanetService;
import com.myproject.planetland.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
@Slf4j
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	private final OrderHisService orderHisService;
	private final PlanetService planetService;

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
			userService.createUser(userJoinDto);
		} catch (IllegalArgumentException e) {
			model.addAttribute("errorMessage", e.getMessage());
			log.info("errorMessage = {}", e.getMessage());
			return "joinForm";
		}
		return "redirect:/";
	}

	@GetMapping("mypage/myPlanets")
	public String getMyPlanets(@AuthenticationPrincipal CustomUserDetails user, Model model) {
		List<MyPlanetsDto> myPlanets = planetService.getMyPlanets(user.getUser().getUserId());
		model.addAttribute("myPlanets", myPlanets);
		return "myPlanets";
	}

	@GetMapping("mypage/history")
	public String getHistory(@AuthenticationPrincipal CustomUserDetails user, Model model, @PageableDefault(size = 20)
		Pageable pageable) {
		Page<OrderHis> history = orderHisService.getHistory(user, pageable);
		int totalPages = history.getTotalPages() - 1;
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("history", history);

		return "orderHis";
	}

	@GetMapping("mypage/sellPlanet")
	public String getSellPlanet(@AuthenticationPrincipal CustomUserDetails user, Model model,
		RedirectAttributes redirectAttributes) {

		User resUser = userService.getUserByUserName(user.getUsername());
		List<SellPlanetDto> sellPlanet = planetService.getSellPlanet(resUser.getUserId());

		model.addAttribute("sellPlanet", sellPlanet);

		return "sellPlanet";
	}

	@GetMapping("mypage/{planetId}/sellPlanet")
	public String cancelSellPlanet(@PathVariable Long planetId) {
		planetService.cancelSellPlanet(planetId);
		return "redirect:/mypage/sellPlanet";
	}

	@GetMapping("mypage/myPlanets/{planetId}/upgrade")
	public String upgradeForm(@PathVariable Long planetId, Model model) {
		model.addAttribute("planetId", planetId);
		model.addAttribute("upgradePlanetDto", new UpgradePlanetDto());
		return "upgradeForm";
	}

	@PostMapping("mypage/myPlanets/{planetId}/upgrade")
	public String upgradePlanet(@ModelAttribute @Valid UpgradePlanetDto upgradePlanetDto,BindingResult bindingResult, @PathVariable Long planetId,
		Model model) {
		if (bindingResult.hasErrors()) {
			return "upgradeForm";
		}

		try {
			planetService.upgradePlanet(planetId, upgradePlanetDto);
		} catch (IllegalArgumentException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "upgradeForm";
		}
		return "redirect:/mypage/myPlanets";
	}
}

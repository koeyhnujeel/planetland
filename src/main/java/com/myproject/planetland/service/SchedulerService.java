package com.myproject.planetland.service;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.myproject.planetland.domain.Planet;
import com.myproject.planetland.domain.User;
import com.myproject.planetland.repository.PlanetRepository;
import com.myproject.planetland.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchedulerService {

	private final PlanetRepository planetRepository;
	private final UserRepository userRepository;

	@Scheduled(fixedDelay = 7000)
	public void run() {
		List<User> allUser = userRepository.findAll();
		for (User user : allUser) {
			List<Planet> userPlanets = planetRepository.findByUser_userId(user.getUserId());
			int plusAsset = 0;
			for (Planet userPlanet : userPlanets) {
				int satellite = userPlanet.getSatellite();
				int population = userPlanet.getPopulation() / 100;
				int total = (satellite * 50) + (population * 30); // 위성은 일정시간 당 50원 (1개 단위), 인구는 일정시간 당 30원 (100명 단위)
				plusAsset += total;
			}
			user.setAsset(user.getAsset() + plusAsset);
			userRepository.save(user);
			log.info("{}님, 잔고 +{}원", user.getUserName(), plusAsset);
		}
	}
}

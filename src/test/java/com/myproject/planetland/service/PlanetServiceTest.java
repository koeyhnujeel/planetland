package com.myproject.planetland.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.myproject.planetland.domain.Planet;
import com.myproject.planetland.domain.User;
import com.myproject.planetland.repository.PlanetRepository;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class PlanetServiceTest {

	@Autowired
	PlanetRepository planetRepository;

	@Autowired
	PlanetService planetService;

	@Test
	@DisplayName("유저 아이디로 소유 행성")
	void getPlanetByUserId() {
		Planet planet1 = new Planet();
		Planet planet2 = new Planet();

		planet1.setPlanetName("sun");
		planet2.setPlanetName("earth");
		Planet saved1 = planetRepository.save(planet1);
		Planet saved2 = planetRepository.save(planet2);

		User user = new User();
		user.setEmail("test@email.com");
		user.setPassword("1234");
		user.setUserName("zunza");
		List<Planet> planets = user.getPlanets();
		planets.add(saved1);
		planets.add(saved2);
		user.setPlanets(planets);

		List<Planet> planetList = planetService.getPlanetByUserId(user.getUserId());
		for (Planet planet : planetList) {
			System.out.println(planet);
		}
	}
}

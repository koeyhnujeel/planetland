package com.myproject.planetland.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.myproject.planetland.domain.Planet;
import com.myproject.planetland.dto.AddPlanetDto;
import com.myproject.planetland.repository.PlanetRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlanetService {

	private final PlanetRepository planetRepository;

	public Planet getPlanet(Long planetId) {
		Optional<Planet> res = planetRepository.findById(planetId);
		if (res.isPresent()) {
			return res.get();
		} else {
			throw new EntityNotFoundException(String.format("아이디 %d로 조회되는 행성이 없습니다.", planetId));
		}
	}

	public Planet getPlanet(String planetName) {
		Optional<Planet> res = planetRepository.findByPlanetName(planetName);
		if (res.isPresent()) {
			return res.get();
		} else {
			throw new EntityNotFoundException(String.format("%s는 존재하지 않는 행성입니다.", planetName));
		}
	}

	public List<Planet> getPlanetByUserId(Long userId) {
		List<Planet> res = planetRepository.findByUser_userId(userId);
		if (res.isEmpty()) {
			throw new EntityNotFoundException("소유한 행성이 없습니다.");
		} else {
			return res;
		}
	}

	public List<Planet> getAllPlanet() {
		List<Planet> planetList = planetRepository.findAll();

		return planetList;
	}

	public AddPlanetDto createPlanet(AddPlanetDto addPlanetDto) {
		Optional<Planet> res = planetRepository.findByPlanetName(addPlanetDto.getPlanetName());
		if (res.isPresent()) {
			throw new IllegalArgumentException("이미 존재하는 행성입니다.");
		} else {
			return addPlanetDto;
		}
	}
}

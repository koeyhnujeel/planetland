package com.myproject.planetland.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.myproject.planetland.domain.Planet;
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

	public List<Planet> getPlanetByUserId(Long userId) {
		List<Planet> res = planetRepository.findByUser_userId(userId);
		if (res.isEmpty()) {
			throw new EntityNotFoundException("소유한 행성이 없습니다.");
		} else {
			return res;
		}
	}
}

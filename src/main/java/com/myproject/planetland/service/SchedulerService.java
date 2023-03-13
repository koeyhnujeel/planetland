package com.myproject.planetland.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.myproject.planetland.repository.PlanetRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SchedulerService {

	private final PlanetRepository planetRepository;

	// @Scheduled(fixedDelay = 30000)
	public void run() {
		long count = planetRepository.count();
		int random = (int)(Math.random() * (count + 1));


	}
}

package com.myproject.planetland.mapper;


import com.myproject.planetland.domain.Planet;
import com.myproject.planetland.dto.PlanetDto;


public class PlanetMapper {

	public static PlanetDto convertToDto(Planet planet) {
		PlanetDto planetDto = new PlanetDto();
		planetDto.setPlanetId(planet.getPlanetId());
		planetDto.setPlanetName(planet.getPlanetName());
		planetDto.setPopulation(planetDto.getPopulation());
		planetDto.setValue(planet.getValue());
		planetDto.setPlanetStatus(planet.getPlanetStatus());
		return planetDto;
	}

	public static Planet convertToModel(PlanetDto planetDto) {
		Planet planet = new Planet();
		planet.setPlanetId(planetDto.getPlanetId());
		planet.setPlanetName(planetDto.getPlanetName());
		planet.setPopulation(planetDto.getPopulation());
		planet.setValue(planetDto.getValue());
		planet.setPlanetStatus(planetDto.getPlanetStatus());
		return planet;
	}
}

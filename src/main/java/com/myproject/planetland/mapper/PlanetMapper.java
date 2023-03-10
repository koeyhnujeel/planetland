package com.myproject.planetland.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.myproject.planetland.domain.Planet;
import com.myproject.planetland.dto.AddPlanetDto;
import com.myproject.planetland.dto.MyPlanetsDto;
import com.myproject.planetland.dto.PlanetDto;
import com.myproject.planetland.dto.PlanetListDto;
import com.myproject.planetland.dto.SellPlanetDto;

@Mapper(componentModel = "spring")
public interface PlanetMapper {
	Planet addPlanetDtoToModel(AddPlanetDto addPlanetDto);
	PlanetDto modelToDto(Planet planet);

	AddPlanetDto modelToAddPlanetDto(Planet planet);
	List<PlanetListDto> modelToDtoList(List<Planet> planets);

	List<MyPlanetsDto> modelToMyPlanetsDto(List<Planet> planets);

	List<SellPlanetDto> modelToSellPlanetDto(List<Planet> planets);
}

package com.myproject.planetland.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.myproject.planetland.domain.Planet;
import com.myproject.planetland.dto.AddPlanetDto;
import com.myproject.planetland.dto.PlanetDto;
import com.myproject.planetland.dto.PlanetListDto;

@Mapper(componentModel = "spring")
public interface PlanetMapper {
	Planet addPlanetDtoToModel(AddPlanetDto addPlanetDto);
	PlanetDto ModelToDto(Planet planet);
	List<PlanetListDto> ModelToDtoList(List<Planet> planets);
}

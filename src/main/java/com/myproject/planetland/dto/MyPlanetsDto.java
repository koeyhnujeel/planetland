package com.myproject.planetland.dto;

import com.myproject.planetland.constants.PlanetStatus;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MyPlanetsDto {
	private Long planetId;
	private String planetName;
	private int population;
	private int lastPrice;
	private PlanetStatus planetStatus;
}

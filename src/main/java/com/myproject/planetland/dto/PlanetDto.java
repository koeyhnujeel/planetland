package com.myproject.planetland.dto;

import com.myproject.planetland.constants.PlanetStatus;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PlanetDto {

	private Long planetId;
	private String planetName;
	private int population;
	private int value;
	private String imgPath;
	private PlanetStatus planetStatus;
	private String owner;
}

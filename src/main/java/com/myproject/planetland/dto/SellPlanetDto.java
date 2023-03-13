package com.myproject.planetland.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SellPlanetDto {

	private Long planetId;
	private String planetName;
	private int price;
}

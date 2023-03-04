package com.myproject.planetland.dto;

import javax.validation.constraints.NotEmpty;

import com.myproject.planetland.constants.PlanetStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class PlanetListDto {

	private Long planetId;
	private String planetName;
	private PlanetStatus planetStatus;
	private String imgPath;
	private String imgName;
}

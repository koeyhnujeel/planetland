package com.myproject.planetland.dto;

import javax.validation.constraints.Min;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpgradePlanetDto {
	@Min(value = 0, message = "음수는 입력할 수 없습니다.")
	private int population;

	@Min(value = 0, message = "음수는 입력할 수 없습니다.")
	private int satellite;
}

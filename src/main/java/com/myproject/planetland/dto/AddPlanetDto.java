package com.myproject.planetland.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.myproject.planetland.constants.PlanetStatus;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AddPlanetDto {
	@NotEmpty(message = "행성 이름을 입력해주세요.")
	private String planetName;

	@Min(value = 1, message = "최소 인구수는 1입니다.")  //@NotEmpty는 String 타입에 사용하는 것
	private int population;

	private int value;
	private PlanetStatus planetStatus;
}

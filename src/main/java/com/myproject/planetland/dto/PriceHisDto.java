package com.myproject.planetland.dto;

import java.time.LocalDate;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PriceHisDto {

	private int price;
	private LocalDate date;
}

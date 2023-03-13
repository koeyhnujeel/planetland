package com.myproject.planetland.dto;

import java.time.LocalDateTime;
import java.util.Date;

import com.myproject.planetland.constants.Type;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderHisDto {

	private Type type;
	private LocalDateTime date;
	private String planetName;
	private int price;
}

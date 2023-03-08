package com.myproject.planetland.dto;

import java.util.Date;

import com.myproject.planetland.constants.Type;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderHisDto {

	private Type type;
	private Date date;
	private String planetName;
	private int value;
}

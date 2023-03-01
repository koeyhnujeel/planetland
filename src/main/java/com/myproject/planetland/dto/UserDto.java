package com.myproject.planetland.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDto {
	private Long userId;

	private String userName;
	private String email;
	private String password;
	private int asset;

}

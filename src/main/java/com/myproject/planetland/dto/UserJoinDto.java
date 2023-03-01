package com.myproject.planetland.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.myproject.planetland.constants.Role;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserJoinDto {

	@NotBlank(message = "이름(닉네임)을 입력해주세요.")
	private String userName;

	@Email
	@NotBlank(message = "이메일을 입력해주세요.")
	private String email;

	@NotEmpty(message = "비밀번호를 입력해주세요")
	@Length(min = 8, max = 16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요")
	private String password;

	private int asset;
	private Role role;
}

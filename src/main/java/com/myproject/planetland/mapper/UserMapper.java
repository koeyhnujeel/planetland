package com.myproject.planetland.mapper;

import org.mapstruct.Mapper;

import com.myproject.planetland.domain.User;
import com.myproject.planetland.dto.UserDto;
import com.myproject.planetland.dto.UserJoinDto;

@Mapper(componentModel = "spring")
public interface UserMapper {
	User userJoinDtoToModel(UserJoinDto userJoinDto);

	UserDto userToDto(User user);
}

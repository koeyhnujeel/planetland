package com.myproject.planetland.mapper;

import com.myproject.planetland.domain.User;
import com.myproject.planetland.dto.UserDto;
import com.myproject.planetland.dto.UserJoinDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-13T23:23:15+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User userJoinDtoToModel(UserJoinDto userJoinDto) {
        if ( userJoinDto == null ) {
            return null;
        }

        User user = new User();

        user.setUserName( userJoinDto.getUserName() );
        user.setEmail( userJoinDto.getEmail() );
        user.setPassword( userJoinDto.getPassword() );

        return user;
    }

    @Override
    public UserDto userToDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setUserId( user.getUserId() );
        userDto.setUserName( user.getUserName() );
        userDto.setEmail( user.getEmail() );
        userDto.setPassword( user.getPassword() );
        userDto.setAsset( user.getAsset() );

        return userDto;
    }
}

package com.myproject.planetland.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.myproject.planetland.constants.Money;
import com.myproject.planetland.constants.Role;
import com.myproject.planetland.domain.User;
import com.myproject.planetland.dto.UserJoinDto;
import com.myproject.planetland.mapper.UserMapper;
import com.myproject.planetland.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private UserMapper mapper;

	public User getUser(Long userId) {
		Optional<User> res = userRepository.findById(userId);
		if (res.isPresent()) {
			return res.get();
		} else {
			throw new EntityNotFoundException(String.format("%d로 조회되는 회원이 없습니다.", userId));
		}
	}

	public User getUserByEmail(String email) {
		Optional<User> res = userRepository.findByEmail(email);
		if (res.isPresent()) {
			return res.get();
		} else {
			throw new EntityNotFoundException(String.format("%s로 조회되는 회원이 없습니다.", email));
		}
	}

	public UserJoinDto createUser(UserJoinDto userJoinDto) {

		Optional<User> res = userRepository.findByUserNameOrEmail(userJoinDto.getUserName(), userJoinDto.getEmail());
		if (res.isPresent()) {
			throw new IllegalArgumentException("이미 가입된 회원입니다.");
		} else {
			userJoinDto.setAsset(Money.INITIAL_FUNDING);
			userJoinDto.setRole(Role.ROLE_USER);
			return userJoinDto;
		}
	}
}

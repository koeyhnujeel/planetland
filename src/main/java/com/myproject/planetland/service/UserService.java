package com.myproject.planetland.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
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

	private final PasswordEncoder passwordEncoder;
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

	public User getUserByUserName(String userName) {
		Optional<User> res = userRepository.findByUserName(userName);
		if (res.isPresent()) {
			return res.get();
		} else {
			throw new EntityNotFoundException(String.format("%s로 조회되는 회원이 없습니다.", userName));
		}
	}

	public UserJoinDto createUser(UserJoinDto userJoinDto) {
		String[] s = userJoinDto.getUserName().split("");
		for (int i = 0; i < s.length; i++) {
			if (s[i].isBlank()) {
				throw new IllegalArgumentException("이름(닉네임)에 공백은 사용할 수 없습니다.");
			}
		}

		Optional<User> res = userRepository.findByUserNameOrEmail(userJoinDto.getUserName(), userJoinDto.getEmail());
		if (res.isPresent()) {
			throw new IllegalArgumentException("이미 가입된 회원입니다.");
		} else {
			String encodePassword = passwordEncoder.encode(userJoinDto.getPassword());
			userJoinDto.setPassword(encodePassword);
			userJoinDto.setAsset(Money.INITIAL_FUNDING);
			userJoinDto.setRole(Role.ROLE_USER);
			return userJoinDto;
		}
	}
}

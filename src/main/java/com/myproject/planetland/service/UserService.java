package com.myproject.planetland.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.myproject.planetland.domain.User;
import com.myproject.planetland.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

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
}

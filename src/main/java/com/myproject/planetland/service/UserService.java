package com.myproject.planetland.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myproject.planetland.constants.Money;
import com.myproject.planetland.constants.Role;
import com.myproject.planetland.domain.Planet;
import com.myproject.planetland.domain.User;
import com.myproject.planetland.dto.MyAssetDto;
import com.myproject.planetland.dto.UserJoinDto;
import com.myproject.planetland.mapper.UserMapper;
import com.myproject.planetland.repository.PlanetRepository;
import com.myproject.planetland.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final PlanetRepository planetRepository;
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

	public void createUser(UserJoinDto userJoinDto) {
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
			User user = new User();
			user.setUserName(userJoinDto.getUserName());
			user.setPassword(encodePassword);
			user.setEmail(userJoinDto.getEmail());
			user.setAsset(Money.INITIAL_FUNDING);
			user.setRole(Role.ROLE_USER);
			userRepository.save(user);
		}
	}

	public MyAssetDto getMyAsset(String userName) {
		Optional<User> res = userRepository.findByUserName(userName);
		if (res.isEmpty()) {
			throw new IllegalArgumentException("잘못된 경로입니다.");
		}

		User user = res.get();
		MyAssetDto myAssetDto = new MyAssetDto();
		myAssetDto.setAsset(user.getAsset());

		int total = 0;
		List<Planet> planetList = planetRepository.findByUser_userId(user.getUserId());
		for (Planet planet : planetList) {
			total += planet.getLastPrice();
		}
		myAssetDto.setTotal(user.getAsset() + total);

		return myAssetDto;
	}
}

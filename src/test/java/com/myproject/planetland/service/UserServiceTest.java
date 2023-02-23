package com.myproject.planetland.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.myproject.planetland.domain.User;
import com.myproject.planetland.repository.UserRepository;

@SpringBootTest
class UserServiceTest {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;

	@Test
	@DisplayName("회원 찾기")
	void findUserByEmail() {
		User user = new User();
		user.setEmail("test@mail.com");
		user.setPassword("1234");
		user.setNickname("zunza");
		User savedUser = userRepository.save(user);
		User res = userService.getUserByEmail("test@mail.com");

		assertThat(res.getUserId()).isEqualTo(savedUser.getUserId());
	}

	@Test
	@DisplayName("유저 찾기 오류")
	void findByIdError() {
		Throwable e = assertThrows(EntityNotFoundException.class,
			() -> {
				userService.getUser(2L);
			});

		assertThat("2로 조회되는 회원이 없습니다.").isEqualTo(e.getMessage());
	}
}


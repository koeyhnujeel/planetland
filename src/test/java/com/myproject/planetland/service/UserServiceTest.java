package com.myproject.planetland.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.myproject.planetland.domain.User;
import com.myproject.planetland.repository.UserRepository;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class UserServiceTest {
	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;

	@Test
	@DisplayName("유저 조회")
	void getUserTest() {
		User user = new User();
		user.setNickname("test");
		User savedUser = userRepository.save(user);
		User res = userService.getUser(savedUser.getUserId());

		assertThat("test").isEqualTo(res.getNickname());
	}

	@Test
	@DisplayName("유저 조회 Error")
	void getUserErrorTest() {
		Throwable e = assertThrows(EntityNotFoundException.class,
		() -> {userService.getUser(1L);});

		assertThat("1로 조회되는 회원이 없습니다.").isEqualTo(e.getMessage());
	}
}

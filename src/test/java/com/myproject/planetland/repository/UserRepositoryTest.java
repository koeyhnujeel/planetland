package com.myproject.planetland.repository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.myproject.planetland.domain.User;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;

	@Test
	@DisplayName("회원 저장 테스트")
	void createUserTest() {
		User user = new User();
		user.setEmail("test@email.com");
		user.setPassword("1234");
		user.setUserName("test");
		User savedUser = userRepository.save(user);
		System.out.println(savedUser.toString());
	}

	@Test
	@DisplayName("회원 찾기 테스트")
	void findByEmailTest() {
		User user = new User();
		user.setEmail("test@email.com");
		user.setPassword("1234");
		user.setUserName("test");
		User savedUser = userRepository.save(user);
		Optional<User> res = userRepository.findByEmail("test@email.com");

		assertThat(res.get().getUserId()).isEqualTo(savedUser.getUserId());
	}
}

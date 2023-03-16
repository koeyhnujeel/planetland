package com.myproject.planetland.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.myproject.planetland.domain.User;
import com.myproject.planetland.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName){
		Optional<User> res = userRepository.findByUserName(userName);
		if (res.isEmpty()) {
			throw new UsernameNotFoundException("존재하지 않는 계정입니다.");
		} else {
			return new CustomUserDetails(res.get());
		}
	}
}

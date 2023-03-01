package com.myproject.planetland.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.myproject.planetland.auth.CustomUserDetails;
import com.myproject.planetland.domain.User;
import com.myproject.planetland.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) {
		Optional<User> res = userRepository.findByUserName(userName);
		if (res.isPresent()) {
			return new CustomUserDetails(res.get());
		}
		return null;
	}
}

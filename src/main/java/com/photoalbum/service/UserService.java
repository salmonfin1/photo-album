package com.photoalbum.service;

import com.photoalbum.model.user.Authority;
import com.photoalbum.model.user.User;
import com.photoalbum.repository.AuthorityRepository;
import com.photoalbum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class UserService implements UserDetailsService {

	private UserRepository userRepository;

	private AuthorityRepository authorityRepository;

	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserService(final UserRepository userRepository,
	                   final AuthorityRepository authorityRepository,
	                   final PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.authorityRepository = authorityRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		return userRepository.findByUsername(s).orElseGet(this::createUser);

	}

	private User createUser() {
		User user = new User();
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		Authority authority = new Authority();
		authority.setAuthority("Admin");
		user.setAuthorities(Collections.singletonList(authority));
		user.setCredentialsNonExpired(true);
		user.setEnabled(true);
		user.setUsername("sam");
		user.setPassword(passwordEncoder.encode("password"));
		userRepository.save(user);

		return user;
	}
}

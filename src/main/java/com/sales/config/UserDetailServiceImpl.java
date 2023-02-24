package com.sales.config;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sales.domain.UserModel;
import com.sales.repository.UserRepository;


@Service
@Transactional
public class UserDetailServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserModel user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found! " + username));
		return new User(user.getUsername(), user.getPassword(), true, true, true, true, user.getAuthorities());
	}

}

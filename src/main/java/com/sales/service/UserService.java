package com.sales.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sales.domain.Role;
import com.sales.domain.UserModel;
import com.sales.repository.RoleRepository;
import com.sales.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public Page<UserModel> buscarTodos(Pageable pageable){
		return userRepository.findAll(pageable);
	}

	public Optional<UserModel> buscarPorId(Long id) {
		return userRepository.findById(id);
	}

	public UserModel salvar(UserModel user) {
		Optional<Role> role = roleRepository.findById(2L);
		user.getRoles().add(role.get());
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	public void deletar(UserModel user) {
		userRepository.delete(user);
	}

}

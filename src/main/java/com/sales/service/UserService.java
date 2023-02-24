package com.sales.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public List<UserModel> buscarTodos(){
		return userRepository.findAll();
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

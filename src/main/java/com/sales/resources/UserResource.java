package com.sales.resources;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sales.domain.UserModel;
import com.sales.resources.exception.StandardError;
import com.sales.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<UserModel>> buscarTodos(){
		return ResponseEntity.status(HttpStatus.OK).body(userService.buscarTodos());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> buscarPorId(@PathVariable Long id){
		Optional<UserModel> userOptional = userService.buscarPorId(id);
		if(userOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(userService.buscarPorId(id));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
				new StandardError(404, "Usuário não encontrado! " + id, LocalDate.now()));
	}
	
	@Transactional
	@PostMapping
	public ResponseEntity<Object> salvar(@Valid @RequestBody UserModel user){
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.salvar(user));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Object> atualizar(@PathVariable Long id, @Valid @RequestBody UserModel user){
		Optional<UserModel> userOptional = userService.buscarPorId(id);
		if(!userOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StandardError(404, "Usuario não encontrado! " + id, LocalDate.now()));
		}
		
		user.setId(userOptional.get().getId());
		
		return ResponseEntity.status(HttpStatus.OK).body(userService.salvar(user));
	}
	
	@Transactional
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deletar(@PathVariable Long id){
		Optional<UserModel> userOptional = userService.buscarPorId(id);
		
		if(!userOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StandardError(404, "Usuario não encontrado! ID: " + id, LocalDate.now()));
		}
		
		userService.deletar(userOptional.get());
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}

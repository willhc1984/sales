package com.sales.resources;

import java.time.LocalDate;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

import com.sales.domain.Cliente;
import com.sales.resources.exception.StandardError;
import com.sales.service.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public ResponseEntity<Page<Cliente>> buscarTodos(@PageableDefault(page = 0, size = 10,
			sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(clienteService.buscarTodos(pageable));
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> buscarPorId(@PathVariable Long id){
		Optional<Cliente> clienteOptional = clienteService.buscarPorId(id);
		if(clienteOptional.isEmpty()) {
			StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), "Cliente não encontrado! " + id, LocalDate.now());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
		}
		return ResponseEntity.status(HttpStatus.OK).body(clienteOptional.get());
	}
	
	@Transactional
	@PostMapping
	public ResponseEntity<Object> salvar(@Valid @RequestBody Cliente cliente){
		return ResponseEntity.status(HttpStatus.OK).body(clienteService.salvar(cliente));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Object> atualizar(@PathVariable Long id, @Valid @RequestBody Cliente cliente){
		Optional<Cliente> clienteOptional = clienteService.buscarPorId(id);
		if(!clienteOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StandardError(404, "Cliente não encontrado! " + id, LocalDate.now()));
		}
		
		cliente.setId(clienteOptional.get().getId());
		
		return ResponseEntity.status(HttpStatus.OK).body(clienteService.salvar(cliente));
	}
	
	@Transactional
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deletar(@PathVariable Long id){
		Optional<Cliente> clienteOptional = clienteService.buscarPorId(id);
		if(!clienteOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StandardError(404,
					"Cliente não encontrado! "+id, LocalDate.now()));
		}
		
		clienteService.deletar(clienteOptional.get());
		return ResponseEntity.status(HttpStatus.OK).build();
	}


}

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

import com.sales.domain.Endereco;
import com.sales.resources.exception.StandardError;
import com.sales.service.EnderecoService;

@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoResource {
	
	@Autowired
	private EnderecoService enderecoService;
	
	@GetMapping
	public ResponseEntity<Page<Endereco>> buscarTodos(@PageableDefault(page = 0, size = 10,
			sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(enderecoService.buscarTodos(pageable));
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> buscarPorId(@PathVariable Long id){
		Optional<Endereco> enderecoOptional = enderecoService.buscarPorId(id);
		if(enderecoOptional.isEmpty()) {
			StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), "Endereço não encontrado! " + id, LocalDate.now());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
		}
		return ResponseEntity.status(HttpStatus.OK).body(enderecoOptional.get());
	}
	
	@Transactional
	@PostMapping
	public ResponseEntity<Object> salvar(@Valid @RequestBody Endereco endereco){
		return ResponseEntity.status(HttpStatus.OK).body(enderecoService.salvar(endereco));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Object> atualizar(@PathVariable Long id, @Valid @RequestBody Endereco endereco){
		Optional<Endereco> enderecoOptional = enderecoService.buscarPorId(id);
		if(!enderecoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StandardError(404, "Endereço não encontrado! " + id, LocalDate.now()));
		}
		
		endereco.setId(enderecoOptional.get().getId());
		
		return ResponseEntity.status(HttpStatus.OK).body(enderecoService.salvar(endereco));
	}
	
	@Transactional
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deletar(@PathVariable Long id){
		Optional<Endereco> enderecoOptional = enderecoService.buscarPorId(id);
		if(!enderecoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StandardError(404,
					"Endereço não encontrado! "+id, LocalDate.now()));
		}
		
		enderecoService.deletar(enderecoOptional.get());
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}

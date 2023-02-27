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

import com.sales.domain.Categoria;
import com.sales.domain.Estado;
import com.sales.resources.exception.StandardError;
import com.sales.service.EstadoService;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {
	
	@Autowired
	private EstadoService estadoService;
	
	@GetMapping
	public ResponseEntity<List<Estado>> buscarTodos() {
		return ResponseEntity.status(HttpStatus.OK).body(estadoService.buscarTodos());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> buscarPorId(@PathVariable Long id){
		Optional<Estado> estadoOptional = estadoService.buscarPorId(id);
		if(estadoOptional.isEmpty()) {
			StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), "Estado não encontrado! " + id, LocalDate.now());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
		}
		return ResponseEntity.status(HttpStatus.OK).body(estadoOptional.get());
	}
	
	@Transactional
	@PostMapping
	public ResponseEntity<Estado> salvar(@Valid @RequestBody Estado estado) {
		return ResponseEntity.status(HttpStatus.CREATED).body(estadoService.salvar(estado));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Object> atualizar(@PathVariable Long id, @Valid @RequestBody Estado estado){
		Optional<Estado> estadoOptional = estadoService.buscarPorId(id);
		if(!estadoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StandardError(404, "Estado não encontrado! " + id, LocalDate.now()));
		}
		
		estado.setId(estadoOptional.get().getId());
		
		return ResponseEntity.status(HttpStatus.OK).body(estadoService.salvar(estado));
	}
	
	@Transactional
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deletar(@PathVariable Long id){
		Optional<Estado> estadoOptional = estadoService.buscarPorId(id);
		if(!estadoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StandardError(404,
					"Esatdo não encontrado! "+id, LocalDate.now()));
		}
		
		estadoService.deletar(estadoOptional.get());
		return ResponseEntity.status(HttpStatus.OK).build();
	}


}

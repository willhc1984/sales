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

import com.sales.domain.Categoria;
import com.sales.resources.exception.StandardError;
import com.sales.service.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	public ResponseEntity<Page<Categoria>> buscarTodos(@PageableDefault(page = 0, size = 10,
			sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
		return ResponseEntity.status(HttpStatus.OK).body(categoriaService.buscarTodos(pageable));
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> buscarPorId(@PathVariable Long id){
		Optional<Categoria> categoriaOptional = categoriaService.buscarPorId(id);
		if(categoriaOptional.isEmpty()) {
			StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), "Categoria não encontrada! " + id, LocalDate.now());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
		}
		return ResponseEntity.status(HttpStatus.OK).body(categoriaOptional.get());
	}
	
	@Transactional
	@PostMapping
	public ResponseEntity<Object> salvar(@Valid @RequestBody Categoria categoria){
		return ResponseEntity.status(HttpStatus.OK).body(categoriaService.salvar(categoria));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Object> atualizar(@PathVariable Long id, @Valid @RequestBody Categoria categoria){
		Optional<Categoria> categoriaOptional = categoriaService.buscarPorId(id);
		if(!categoriaOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StandardError(404, "Categoria não encontrada! " + id, LocalDate.now()));
		}
		
		categoria.setId(categoriaOptional.get().getId());
		
		return ResponseEntity.status(HttpStatus.OK).body(categoriaService.salvar(categoria));
	}
	
	@Transactional
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deletar(@PathVariable Long id){
		Optional<Categoria> categoriaOptional = categoriaService.buscarPorId(id);
		if(!categoriaOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StandardError(404,
					"Categoria não encontrada! "+id, LocalDate.now()));
		}
		
		categoriaService.deletar(categoriaOptional.get());
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}

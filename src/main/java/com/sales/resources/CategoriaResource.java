package com.sales.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sales.domain.Categoria;
import com.sales.service.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> buscarTodos() {
		return ResponseEntity.status(HttpStatus.OK).body(categoriaService.buscarTodos());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> buscarPorId(@PathVariable Long id){
		Optional<Categoria> categoriaOptional = categoriaService.buscarPorId(id);
		if(!categoriaOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria não encontrada!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(categoriaOptional.get());
	}

}

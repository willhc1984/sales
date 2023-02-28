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

import com.sales.domain.Produto;
import com.sales.resources.exception.StandardError;
import com.sales.service.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping
	public ResponseEntity<List<Produto>> buscarTodos() {
		return ResponseEntity.status(HttpStatus.OK).body(produtoService.buscarTodos());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> buscarPorId(@PathVariable Long id){
		Optional<Produto> produtoOptional = produtoService.buscarPorId(id);
		if(produtoOptional.isEmpty()) {
			StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), "Produto não encontrado! " + id, LocalDate.now());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
		}
		return ResponseEntity.status(HttpStatus.OK).body(produtoOptional.get());
	}
	
	@Transactional
	@PostMapping
	public ResponseEntity<Object> salvar(@Valid @RequestBody Produto produto){
		return ResponseEntity.status(HttpStatus.OK).body(produtoService.salvar(produto));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Object> atualizar(@PathVariable Long id, @Valid @RequestBody Produto produto){
		Optional<Produto> produtoOptional = produtoService.buscarPorId(id);
		if(!produtoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StandardError(404, "Produto não encontrado! " + id, LocalDate.now()));
		}
		
		produto.setId(produtoOptional.get().getId());
		
		return ResponseEntity.status(HttpStatus.OK).body(produtoService.salvar(produto));
	}
	
	@Transactional
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deletar(@PathVariable Long id){
		Optional<Produto> produtoOptional = produtoService.buscarPorId(id);
		if(!produtoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StandardError(404,
					"Produto não encontrado! "+id, LocalDate.now()));
		}
		
		produtoService.deletar(produtoOptional.get());
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}

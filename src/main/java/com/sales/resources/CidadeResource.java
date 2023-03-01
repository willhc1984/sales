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

import com.sales.domain.Cidade;
import com.sales.resources.exception.StandardError;
import com.sales.service.CidadeService;


@RestController
@RequestMapping(value = "/cidades")
public class CidadeResource {
	
	@Autowired
	private CidadeService cidadeService;
	
	@GetMapping
	public ResponseEntity<Page<Cidade>> buscarTodos(@PageableDefault(page = 0, size = 10,
			sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(cidadeService.buscarTodos(pageable));
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> buscarPorId(@PathVariable Long id){
		Optional<Cidade> cidadeOptional = cidadeService.buscarPorId(id);
		if(cidadeOptional.isEmpty()) {
			StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), "Cidade não encontrada! " + id, LocalDate.now());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
		}
		return ResponseEntity.status(HttpStatus.OK).body(cidadeOptional.get());
	}
	
	@Transactional
	@PostMapping
	public ResponseEntity<Cidade> salvar(@Valid @RequestBody Cidade cidade) {
		return ResponseEntity.status(HttpStatus.CREATED).body(cidadeService.salvar(cidade));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Object> atualizar(@PathVariable Long id, @Valid @RequestBody Cidade cidade){
		Optional<Cidade> cidadeOptional = cidadeService.buscarPorId(id);
		if(!cidadeOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StandardError(404, "Cidade não encontrada! " + id, LocalDate.now()));
		}
		
		cidade.setId(cidadeOptional.get().getId());
		
		return ResponseEntity.status(HttpStatus.OK).body(cidadeService.salvar(cidade));
	}
	
	@Transactional
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deletar(@PathVariable Long id){
		Optional<Cidade> cidadeOptional = cidadeService.buscarPorId(id);
		if(!cidadeOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StandardError(404,
					"Cidade não encontrada! "+id, LocalDate.now()));
		}
		
		cidadeService.deletar(cidadeOptional.get());
		return ResponseEntity.status(HttpStatus.OK).build();
	}


}

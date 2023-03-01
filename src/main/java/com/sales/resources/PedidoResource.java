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

import com.sales.domain.Pedido;
import com.sales.resources.exception.StandardError;
import com.sales.service.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping
	public ResponseEntity<Page<Pedido>> buscarTodos(@PageableDefault(page = 0, size = 10,
			sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(pedidoService.buscarTodos(pageable));
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> buscarPorId(@PathVariable Long id){
		Optional<Pedido> pedidoOptional = pedidoService.buscarPorId(id);
		if(pedidoOptional.isEmpty()) {
			StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), "Pedido não encontrado! " + id, LocalDate.now());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
		}
		return ResponseEntity.status(HttpStatus.OK).body(pedidoOptional.get());
	}
	
	@Transactional
	@PostMapping
	public ResponseEntity<Object> salvar(@Valid @RequestBody Pedido pedido){
		return ResponseEntity.status(HttpStatus.OK).body(pedidoService.salvar(pedido));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Object> atualizar(@PathVariable Long id, @Valid @RequestBody Pedido pedido){
		Optional<Pedido> pedidoOptional = pedidoService.buscarPorId(id);
		if(!pedidoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StandardError(404, "Pedido não encontrado! " + id, LocalDate.now()));
		}
		
		pedido.setId(pedidoOptional.get().getId());
		
		return ResponseEntity.status(HttpStatus.OK).body(pedidoService.salvar(pedido));
	}
	
	@Transactional
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deletar(@PathVariable Long id){
		Optional<Pedido> pedidoOptional = pedidoService.buscarPorId(id);
		if(!pedidoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StandardError(404,
					"Pedido não encontrado! "+id, LocalDate.now()));
		}
		
		pedidoService.deletar(pedidoOptional.get());
		return ResponseEntity.status(HttpStatus.OK).build();
	}


}

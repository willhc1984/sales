package com.sales.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public ResponseEntity<List<Pedido>> buscarTodos() {
		return ResponseEntity.status(HttpStatus.OK).body(pedidoService.buscarTodos());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> buscarPorId(@PathVariable Long id){
		Optional<Pedido> pedidoOptional = pedidoService.buscarPorId(id);
		if(pedidoOptional.isEmpty()) {
			StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), "Pedido não encontrado! " + id, System.currentTimeMillis());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
		}
		return ResponseEntity.status(HttpStatus.OK).body(pedidoOptional.get());
	}

}

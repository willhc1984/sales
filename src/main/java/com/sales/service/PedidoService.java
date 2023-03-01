package com.sales.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sales.domain.Pedido;
import com.sales.repository.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired 
	private PedidoRepository pedidoRepository;
	
	public Optional<Pedido> buscarPorId(Long id) {
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
		return pedidoOptional;
	}
	
	public Page<Pedido> buscarTodos(Pageable pageable) {
		return pedidoRepository.findAll(pageable);
	}
	
	public Pedido salvar(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}

	public void deletar(Pedido pedido) {
		pedidoRepository.delete(pedido);
		
	}

}

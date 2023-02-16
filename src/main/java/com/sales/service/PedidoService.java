package com.sales.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public List<Pedido> buscarTodos() {
		return pedidoRepository.findAll();
	}

}

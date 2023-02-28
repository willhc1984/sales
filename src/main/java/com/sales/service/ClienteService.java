package com.sales.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sales.domain.Cliente;
import com.sales.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired 
	private ClienteRepository clienteRepository;
	
	public Optional<Cliente> buscarPorId(Long id) {
		Optional<Cliente> clienteOptional = clienteRepository.findById(id);
		return clienteOptional;
	}
	
	public List<Cliente> buscarTodos() {
		return clienteRepository.findAll();
	}

	public Cliente salvar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public void deletar(Cliente cliente) {
		clienteRepository.delete(cliente);
	}

}

package com.sales.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sales.domain.Estado;
import com.sales.repository.EstadoRepository;

@Service
public class EstadoService {
	
	@Autowired
	private EstadoRepository estadoRepository;

	public Page<Estado> buscarTodos(Pageable pageable) {
		return estadoRepository.findAll(pageable);
	}
	
	public Optional<Estado> buscarPorId(Long id){
		return estadoRepository.findById(id);
	}

	public Estado salvar(Estado estado) {
		return estadoRepository.save(estado);
	}

	public void deletar(Estado estado) {
		estadoRepository.delete(estado);
	}

}

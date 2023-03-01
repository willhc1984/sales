package com.sales.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sales.domain.Categoria;
import com.sales.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired 
	private CategoriaRepository categoriaRepository;
	
	public Optional<Categoria> buscarPorId(Long id) {
		Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
		return categoriaOptional;
	}
	
	public Page<Categoria> buscarTodos(Pageable pageable) {
		return categoriaRepository.findAll(pageable);
	}

	public Categoria salvar(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	public void deletar(Categoria categoria) {
		categoriaRepository.delete(categoria);
		
	}

}

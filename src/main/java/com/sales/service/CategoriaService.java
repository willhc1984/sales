package com.sales.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public List<Categoria> buscarTodos() {
		return categoriaRepository.findAll();
	}

	public Categoria salvar(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	public void deletar(Categoria categoria) {
		categoriaRepository.delete(categoria);
		
	}

}

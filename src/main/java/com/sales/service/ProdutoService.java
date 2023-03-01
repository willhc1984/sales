package com.sales.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sales.domain.Produto;
import com.sales.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired 
	private ProdutoRepository produtoRepository;
	
	public Optional<Produto> buscarPorId(Long id) {
		Optional<Produto> produtoOptional = produtoRepository.findById(id);
		return produtoOptional;
	}
	
	public Page<Produto> buscarTodos(Pageable pageable) {
		return produtoRepository.findAll(pageable);
	}

	public Produto salvar(Produto produto) {
		return produtoRepository.save(produto);
	}

	public void deletar(Produto produto) {
		produtoRepository.delete(produto);
		
	}

}

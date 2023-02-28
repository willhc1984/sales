package com.sales.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public List<Produto> buscarTodos() {
		return produtoRepository.findAll();
	}

	public Produto salvar(Produto produto) {
		return produtoRepository.save(produto);
	}

	public void deletar(Produto produto) {
		produtoRepository.delete(produto);
		
	}

}

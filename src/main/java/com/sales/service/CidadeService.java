package com.sales.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sales.domain.Cidade;
import com.sales.repository.CidadeRepository;

@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;

	public Page<Cidade> buscarTodos(Pageable pageable) {
		return cidadeRepository.findAll(pageable);
	}
	
	public Optional<Cidade> buscarPorId(Long id){
		return cidadeRepository.findById(id);
	}

	public Cidade salvar(Cidade cidade) {
		return cidadeRepository.save(cidade);
	}

	public void deletar(Cidade cidade) {
		cidadeRepository.delete(cidade);
	}

}

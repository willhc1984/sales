package com.sales.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sales.domain.Cidade;
import com.sales.domain.Estado;
import com.sales.repository.CidadeRepository;

@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;

	public List<Cidade> buscarTodos() {
		return cidadeRepository.findAll();
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

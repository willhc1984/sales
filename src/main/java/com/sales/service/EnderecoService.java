package com.sales.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sales.domain.Endereco;
import com.sales.repository.EnderecoRepository;

@Service
public class EnderecoService {
	
	@Autowired 
	private EnderecoRepository enderecoRepository;
	
	public Optional<Endereco> buscarPorId(Long id) {
		Optional<Endereco> enderecoOptional = enderecoRepository.findById(id);
		return enderecoOptional;
	}
	
	public Page<Endereco> buscarTodos(Pageable pageable) {
		return enderecoRepository.findAll(pageable);
	}

	public Endereco salvar(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}

	public void deletar(Endereco endereco) {
		enderecoRepository.delete(endereco);
	}

}

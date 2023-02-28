package com.sales.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sales.domain.Categoria;
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
	
	public List<Endereco> buscarTodos() {
		return enderecoRepository.findAll();
	}

	public Endereco salvar(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}

	public void deletar(Endereco endereco) {
		enderecoRepository.delete(endereco);
	}

}

package com.sales;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sales.domain.Categoria;
import com.sales.domain.Cidade;
import com.sales.domain.Estado;
import com.sales.domain.Produto;
import com.sales.repository.CategoriaRepository;
import com.sales.repository.CidadeRepository;
import com.sales.repository.EstadoRepository;
import com.sales.repository.ProdutoRepository;

@SpringBootApplication
public class SalesApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SalesApplication.class, args);
	}
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Eletronico");
		Categoria cat3 = new Categoria(null, "Eletrodomestico");
		Categoria cat4 = new Categoria(null, "Livro");
		
		Produto p1 = new Produto(null, "CPU", 2000.0);
		Produto p2 = new Produto(null, "Mouse", 35.0);
		Produto p3 = new Produto(null, "Fogão", 2800.0);
		Produto p4 = new Produto(null, "Senhor dos Anéis", 22.0);
		Produto p5 = new Produto(null, "Monitor", 154.0);
		
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		
		p1.getCategorias().addAll(Arrays.asList(cat4));
		p2.getCategorias().addAll(Arrays.asList(cat4));
		p3.getCategorias().addAll(Arrays.asList(cat4));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		Estado e1 = new Estado(null, "Minas Gerais");
		Estado e2 = new Estado(null, "São Paulo");
		Cidade c1 = new Cidade(null, "Sorocaba", e2);
		Cidade c2 = new Cidade(null, "Belo Horizonte", e1);
		Cidade c3 = new Cidade(null, "São Paulo", e2);
		
		e1.getCidades().addAll(Arrays.asList(c2));
		e2.getCidades().addAll(Arrays.asList(c1, c3));
				
		estadoRepository.saveAll(Arrays.asList(e1, e2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
	}

}

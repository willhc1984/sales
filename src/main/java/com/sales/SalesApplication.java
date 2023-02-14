package com.sales;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sales.domain.Categoria;
import com.sales.repository.CategoriaRepository;

@SpringBootApplication
public class SalesApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SalesApplication.class, args);
	}
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(1L, "Informática");
		Categoria cat2 = new Categoria(2L, "Almoxarifado");
		Categoria cat3 = new Categoria(3L, "Jurídico");
		Categoria cat4 = new Categoria(4L, "Comunicação");
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4));
		
	}

}

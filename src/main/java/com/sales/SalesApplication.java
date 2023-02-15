package com.sales;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sales.domain.Categoria;
import com.sales.domain.Cidade;
import com.sales.domain.Cliente;
import com.sales.domain.Endereco;
import com.sales.domain.Estado;
import com.sales.domain.Pagamento;
import com.sales.domain.PagamentoComBoleto;
import com.sales.domain.PagamentoComCartao;
import com.sales.domain.Pedido;
import com.sales.domain.Produto;
import com.sales.domain.enums.EstadoPagamento;
import com.sales.domain.enums.TipoCliente;
import com.sales.repository.CategoriaRepository;
import com.sales.repository.CidadeRepository;
import com.sales.repository.ClienteRepository;
import com.sales.repository.EnderecoRepository;
import com.sales.repository.EstadoRepository;
import com.sales.repository.PagamentoRepository;
import com.sales.repository.PedidoRepository;
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
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;

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
		
		Cliente cli1 = new Cliente(null, "Maria", "maria@maria", "85785478", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("74580087", "789596478"));
		
		Cliente cli2 = new Cliente(null, "José", "jose@jose", "1585471236", TipoCliente.PESSOAJURIDICA);
		cli2.getTelefones().addAll(Arrays.asList("14785696", "1547856321", "1574589632"));
		
		Endereco end1 = new Endereco(null, "rua 666", "358", "apto 305", "Jardim", "18070410", cli1, c2);
		Endereco end2 = new Endereco(null, "rua blabla", "358", "apto 67", "Jardim", "18070410", cli1, c1);
		Endereco end3 = new Endereco(null, "rua lalala", "123", "apto 34", "Jardim Icatu", "18070410", cli2, c1);
		
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
		cli2.getEnderecos().add(end3);
		
		clienteRepository.save(cli1);
		clienteRepository.save(cli2);
		enderecoRepository.saveAll(Arrays.asList(end1, end2, end3));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/10/2017 10:32"), cli1, end3);
		Pedido ped2 = new Pedido(null, sdf.parse("30/10/2017 11:32"), cli2, end1);
		
		Pagamento pag1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pag1);
		Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("30/05/2017 00:00"), null);
		ped2.setPagamento(pag2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pag1, pag2));
		
	}

}

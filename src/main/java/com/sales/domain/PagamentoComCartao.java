package com.sales.domain;

import com.sales.domain.enums.EstadoPagamento;

import javax.persistence.*;

@Entity
public class PagamentoComCartao extends Pagamento{
	
	private static final long serialVersionUID = 1L;
	
	private Integer numeroParcelas;
	
	public PagamentoComCartao() {
		
	}

	public PagamentoComCartao(Long id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
		super(id, estado, pedido);
		this.numeroParcelas = numeroDeParcelas;
	}

	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}	

}

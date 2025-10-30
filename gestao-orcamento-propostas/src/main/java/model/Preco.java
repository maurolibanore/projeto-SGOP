package model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Preco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private BigDecimal valor;
	private LocalDate dataVigencia;
	private boolean ativo;
	
	@ManyToOne(fetch = FetchType.LAZY)  // Parte 1: A Relação e a Performance
	@JoinColumn(name = "servico_id")   // Parte 2: O Mapeamento no Banco de Dados
	private Servico servico;              // Parte 3: O Atributo na Classe

	public Preco(Integer id, BigDecimal valor, LocalDate dataVigencia, boolean ativo, Servico servico) {
		this.id = id;
		this.valor = valor;
		this.dataVigencia = dataVigencia;
		this.ativo = ativo;
		this.servico = servico;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDate getDataVigencia() {
		return dataVigencia;
	}

	public void setDataVigencia(LocalDate dataVigencia) {
		this.dataVigencia = dataVigencia;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}
	
	
	
	
	
}

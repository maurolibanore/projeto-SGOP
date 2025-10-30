package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name  = "cliente_pj")
public class ClientePessoaJuridica extends Pessoa {
	@Column(unique = true)
	
	private String cnpj;
	private String razao_social;
	
	
	public ClientePessoaJuridica(Integer id, String nome, String email, String telefone, String cnpj,
			String razao_social) {
		super(id, nome, email, telefone);
		this.cnpj = cnpj;
		this.razao_social = razao_social;
	}


	public String getCnpj() {
		return cnpj;
	}


	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}


	public String getRazao_social() {
		return razao_social;
	}


	public void setRazao_social(String razao_social) {
		this.razao_social = razao_social;
	}
	
	
	
	
}

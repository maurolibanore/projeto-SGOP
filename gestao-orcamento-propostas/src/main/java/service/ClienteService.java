package service;

import dao.ClientePessoaFisicaDAO;
import dao.ClientePessoaJuridicaDAO;
import model.ClientePessoaFisica;
import model.ClientePessoaJuridica;

public class ClienteService {
	
	private ClientePessoaFisicaDAO pfDAO;
	private ClientePessoaJuridicaDAO pjDAO;
	
	public ClienteService () {
		this.pfDAO = new ClientePessoaFisicaDAO();  // economiza memoria em nao ficar repetindo toda vez
		this.pjDAO = new ClientePessoaJuridicaDAO();
	
	}
	
	// RN : nao permite cpf duplicado
	public void cadastrarPessoaFisica(ClientePessoaFisica cliente) {
		ClientePessoaFisica existente = pfDAO.buscarPorCpf(cliente.getCpf());
		
		if(existente != null) {
			throw new IllegalArgumentException("CPF já cadastrado");
		}
		// caso nao exista cadastra
		pfDAO.cadastrar(cliente);
	}
	
	
	// cnpj duplicado
	public void cadastrarPessoaJuridica(ClientePessoaJuridica cliente) {
		ClientePessoaJuridica existente = pjDAO.buscarPorCnpj(cliente.getCnpj());
		
		if(existente != null) {
			throw new IllegalArgumentException("CNPJ já cadastrado");
		}
		pjDAO.cadastrar(cliente);
	}
	

}

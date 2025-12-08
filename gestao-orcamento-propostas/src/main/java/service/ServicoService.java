package service;

import java.time.LocalDate;

import dao.ServicoDAO;
import model.Preco;
import model.Servico;

public class ServicoService {
	
	private ServicoDAO dao;
	
	public ServicoService() {
		this.dao = new ServicoDAO();
	}
	
	public void cadastrarServico(String descricao, Double valorInicial) {
		//valida descricao unica
		if(dao.buscarPorDescricao(descricao)!= null) {
			throw new IllegalArgumentException("Já existe um serviço com essa descricao");	
		}
		
		Servico servico = new Servico(descricao);
		
		// RN preco ativo primeiro
		Preco preco = new Preco(valorInicial, LocalDate.now(), true, servico);
		servico.getHistoricoPrecos().add(preco);
		
		dao.cadastrar(servico);
	}
	
	//RN REAJUSTE EM MASSA

	

}







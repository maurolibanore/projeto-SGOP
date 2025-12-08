package service;

import java.time.LocalDate;

import dao.PropostaDAO;
import model.Proposta;
import model.StatusProposta;

public class PropostaService {

	private PropostaDAO dao;
	
	public PropostaService() {
		this.dao = new PropostaDAO();
	}
	
	public void criarProposta(Proposta proposta) {
		proposta.setStatus(StatusProposta.EM_ELABORACAO);
		
		if(proposta.getDataCriacao() == null) {
			proposta.setDataCriacao(LocalDate.now());
		}
		
		proposta.calcularValorTotal();
		
		dao.cadastrar(proposta);
	}
	
	public void enviarProposta(Long idProposta) {
		Proposta proposta = dao.buscarPorId(idProposta);
		
		if(proposta == null) {
			throw new IllegalArgumentException("Proposta não encontrada");
		}
		if(proposta.getItens().isEmpty()) {
			throw new IllegalArgumentException("Não é possível enviar uma proposta sem ítens");
		}
		
		proposta.setStatus(StatusProposta.ENVIADA);
		
		if(proposta.getDataValidade() == null) {
			proposta.setDataValidade(LocalDate.now().plusDays(15));
		}
		
		dao.atualizar(proposta);
	}
	
	
	public void aceitarProposta(Long idProposta) {
		Proposta proposta = dao.buscarPorId(idProposta);
	
		if(proposta.getStatus() != StatusProposta.ENVIADA) {
			throw new IllegalArgumentException("Proposta não enviada");
		}
		if(LocalDate.now().isAfter(proposta.getDataValidade())) {
			proposta.setStatus(StatusProposta.EXPIRADA);
			dao.atualizar(proposta);
			throw new IllegalArgumentException("Proposta vencida em"+ proposta.getDataValidade());
		}
		
		proposta.setStatus(StatusProposta.ACEITA);
		dao.atualizar(proposta);
		
	}
	
	
	
	
	
}

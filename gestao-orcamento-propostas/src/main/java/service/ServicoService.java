package service;

import java.time.LocalDate;
import java.util.List;

import dao.ServicoDAO;
import model.Preco;
import model.Servico;

public class ServicoService {
	
	private ServicoDAO dao;
	
	public ServicoService() {
		this.dao = new ServicoDAO();
	}
	
	public Servico cadastrarServico(String descricao, Double valorInicial) {
		//valida descricao unica
		if(dao.buscarPorDescricao(descricao)!= null) {
			throw new IllegalArgumentException("Já existe um serviço com essa descricao");	
		}
		
		Servico servico = new Servico(descricao);
		
		// RN preco ativo primeiro
		Preco preco = new Preco(valorInicial, LocalDate.now(), true, servico);
		servico.getHistoricoPrecos().add(preco);
		
		dao.cadastrar(servico);
		return servico;
	}
	
	public void atualizarPreco(Long idServico, Double novoValor) {
        Servico servico = dao.buscarPorId(idServico);
        
        if (servico == null) {
            throw new IllegalArgumentException("Serviço não encontrado com ID: " + idServico);
        }

        //Desativa o preço atual antigo
        for (Preco p : servico.getHistoricoPrecos()) {
            if (p.isAtivo()) {
                p.setAtivo(false);
                break; // Achou o ativo, desativa e para o loop
            }
        }

        Preco novoPreco = new Preco(novoValor, LocalDate.now(), true, servico);
        
        //Adiciona na lista de histórico
        servico.getHistoricoPrecos().add(novoPreco);

        dao.atualizar(servico);
    }
	
	//RN REAJUSTE EM MASSA
	public void reajustarTodosServicos(Double percentual) {
        List<Servico> todosServicos = dao.listarTodos();
        
        System.out.println("Iniciando reajuste de " + percentual + "% para " + todosServicos.size() + " serviços.");

        for (Servico servico : todosServicos) {
            atualizarPrecoIndividual(servico, percentual);
            dao.atualizar(servico); // Cascade salva os preços novos
        }
    }
	
	private void atualizarPrecoIndividual(Servico servico, Double percentual) {
        // Achar o preço atual
        Preco precoAtual = null;
        for (Preco p : servico.getHistoricoPrecos()) {
            if (p.isAtivo()) {
                precoAtual = p;
                break;
            }
        }

        if (precoAtual != null) {
            // Desativar o antigo
            precoAtual.setAtivo(false);

            // Calcular novo valor
            Double novoValor = precoAtual.getValor() * (1 + (percentual / 100));

            // Criar novo preço ativo
            Preco novoPreco = new Preco(novoValor, LocalDate.now(), true, servico);
            
            // Adicionar na lista
            servico.getHistoricoPrecos().add(novoPreco);
        }
    }
	

}







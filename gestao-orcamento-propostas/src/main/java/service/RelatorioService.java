package service;

import java.time.LocalDate;
import java.util.List;

import dao.PropostaDAO;
import model.Proposta;
import model.StatusProposta;

public class RelatorioService {
	private PropostaDAO dao;

    public RelatorioService() {
        this.dao = new PropostaDAO();
    }

    public void gerarRelatorioVendasPorPeriodo(LocalDate inicio, LocalDate fim) {
        List<Proposta> propostas = dao.buscarPorPeriodo(inicio, fim);
        
        System.out.println("-- Relatório de Propostas (" + inicio + " a " + fim + ") --");
        
        double totalFaturado = 0.0;
        
        for (Proposta p : propostas) {
            System.out.println("ID: " + p.getId() + " | Cliente: " + p.getCliente().getNome() + 
                               " | Valor: R$ " + p.getValorTotal() + " | Status: " + p.getStatus());
            
            if (p.getStatus() == StatusProposta.ACEITA) {
                totalFaturado += p.getValorTotal();
            }
        }
        
        System.out.println("------------------------------------------------");
        System.out.println("Total Faturado (ACEITAS): R$ " + totalFaturado);
    }
    
    public void gerarRelatorioPorCliente(Long idCliente) {
        List<Proposta> propostas = dao.buscarPorCliente(idCliente);
        
        System.out.println("--- Histórico do Cliente ID: " + idCliente + " ---");
        for (Proposta p : propostas) {
            System.out.println("Data: " + p.getDataCriacao() + " | Valor: " + p.getValorTotal() + " - " + p.getStatus());
        }
    }
}

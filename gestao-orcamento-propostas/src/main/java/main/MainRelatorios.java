package main;

import service.RelatorioService;
import util.JPAUTIL;

import java.time.LocalDate;

public class MainRelatorios {

    public static void main(String[] args) {
        System.out.println("--- RELATÓRIOS ---");
       
        RelatorioService relatorioService = new RelatorioService();

        try {
            // do início do ano até hoje 
            LocalDate hoje = LocalDate.now();
            LocalDate inicioAno = hoje.minusYears(1);
            
            System.out.println("\n>>> Relatório de Vendas (" + inicioAno + " até " + hoje + ")...");
            relatorioService.gerarRelatorioVendasPorPeriodo(inicioAno, hoje);

            Long idClienteTeste = 1L;
            
            System.out.println("\n>>> Histórico do Cliente " + idClienteTeste + "...");
            relatorioService.gerarRelatorioPorCliente(idClienteTeste);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JPAUTIL.close();
        }
    }
}
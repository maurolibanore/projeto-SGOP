package main;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import dao.PropostaDAO;
import dao.ServicoDAO;
import model.Proposta;
import model.Servico;
import service.PropostaService;
import service.RelatorioService;
import service.ServicoService;
import util.JPAUTIL;

public class MenuSistema {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        
        PropostaService propostaService = new PropostaService();
        RelatorioService relatorioService = new RelatorioService();
        ServicoService servicoService = new ServicoService();
        
        PropostaDAO propostaDAO = new PropostaDAO(); 
        ServicoDAO servicoDAO = new ServicoDAO();

        System.out.println("=== SISTEMA DE GESTÃO ===");
        
        boolean rodando = true;
        int opcao = -1;

        while (rodando) {
            System.out.println("\n--------------------------------------------");
            System.out.println("1. Listar Propostas");
            System.out.println("2. Enviar Proposta");
            System.out.println("3. Aceitar Proposta");
            System.out.println("4. Relatório de Faturamento");
            System.out.println("5. Histórico do Cliente");
            System.out.println("6. Detalhar Proposta por ID");
            System.out.println("7. Alterar Preço de UM Serviço");
            System.out.println("8. Reajustar TODOS os Serviços (%)");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");

            try {
                opcao = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Digite um número válido!");
                scanner.nextLine(); 
                continue;
            }
            
            scanner.nextLine(); 


            
            if (opcao == 1) {
                List<Proposta> lista = propostaDAO.listarTodos();
                if (lista.isEmpty()) {
                    System.out.println("Nenhuma proposta cadastrada.");
                } else {
                    System.out.println("\n- LISTA DE PROPOSTAS -");
                    for (Proposta p : lista) {
                        System.out.println("ID: " + p.getId() + 
                                           " - Cliente: " + p.getCliente().getNome() + 
                                           " - Valor: R$ " + p.getValorTotal() + 
                                           " - Status: " + p.getStatus());
                    }
                }
            }
            else if (opcao == 2) {
                System.out.print("Digite o ID da proposta para ENVIAR: ");
                try {
                    Long idEnviar = Long.parseLong(scanner.nextLine());
                    propostaService.enviarProposta(idEnviar);
                    System.out.println("Sucesso! Proposta enviada.");
                } catch (Exception e) {
                    System.out.println("Erro: " + e.getMessage());
                }
            }
            else if (opcao == 3) {
                System.out.print("Digite o ID da proposta para ACEITAR: ");
                try {
                    Long idAceitar = Long.parseLong(scanner.nextLine());
                    propostaService.aceitarProposta(idAceitar);
                    System.out.println("Sucesso! Proposta aceita.");
                } catch (Exception e) {
                    System.out.println("Erro: " + e.getMessage());
                }
            }
            else if (opcao == 4) {
                LocalDate hoje = LocalDate.now();
                LocalDate inicio = hoje.minusDays(30);
                System.out.println("Gerando relatório...");
                relatorioService.gerarRelatorioVendasPorPeriodo(inicio, hoje);
            }
            else if (opcao == 5) {
                System.out.print("Digite o ID do Cliente: ");
                try {
                    Long idCliente = Long.parseLong(scanner.nextLine());
                    relatorioService.gerarRelatorioPorCliente(idCliente);
                } catch (NumberFormatException e) {
                    System.out.println("ID inválido.");
                }
            }
            else if (opcao == 6) {
                System.out.print("Digite o ID da Proposta: ");
                try {
                    Long idBusca = Long.parseLong(scanner.nextLine());
                    Proposta p = propostaDAO.buscarPorId(idBusca);
                    
                    if (p != null) {
                        System.out.println("\n=== DETALHES ===");
                        System.out.println("ID: " + p.getId());
                        System.out.println("Cliente: " + p.getCliente().getNome());
                        System.out.println("Total: R$ " + p.getValorTotal());
                    } else {
                        System.out.println("Proposta não encontrada.");
                    }
                } catch (Exception e) {
                    System.out.println("Erro");
                }
            }
            else if (opcao == 7) {
                System.out.println("\n- SERVIÇOS DISPONÍVEIS -");
                List<Servico> servicos = servicoDAO.listarTodos();
                for (Servico s : servicos) {
                    System.out.println("ID: " + s.getId() + " | Nome: " + s.getDescricao() + " | Preço Atual: R$ " + s.getValorAtual());
                }

                System.out.print("\nDigite o ID do Serviço para alterar: ");
                try {
                    Long idServico = Long.parseLong(scanner.nextLine());
                    System.out.print("Digite o NOVO valor: ");
                    Double novoValor = Double.parseDouble(scanner.nextLine());

                    servicoService.atualizarPreco(idServico, novoValor);
                    System.out.println("Sucesso! Preço atualizado.");
                } catch (Exception e) {
                    System.out.println("Erro: " + e.getMessage());
                }
            }
            else if (opcao == 8) {
                System.out.print("Digite a porcentagem de aumento (ex: 10 para 10%): ");
                try {
                    Double percentual = Double.parseDouble(scanner.nextLine());
                    
                    System.out.println("Aplicando reajuste de " + percentual + "% em todos os serviços...");
                    servicoService.reajustarTodosServicos(percentual);
                    
                    System.out.println("Concluído! Todos os serviços foram atualizados.");
                    
                } catch (Exception e) {
                    System.out.println("Erro : " + e.getMessage());
                }
            }
            else if (opcao == 0) {
                rodando = false;
            } 
            else {
                System.out.println("Opção inválida.");
            }
        }
        
        scanner.close();
        JPAUTIL.close();
    }
}
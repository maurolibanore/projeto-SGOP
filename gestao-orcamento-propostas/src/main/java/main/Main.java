package main;

import java.time.LocalDate;

import dao.ClientePessoaFisicaDAO;
import dao.PropostaDAO;
import model.ClientePessoaFisica;
import model.ItemProposta;
import model.Proposta;
import model.Servico;
import model.StatusProposta;
import service.ServicoService;
import util.JPAUTIL;

public class Main {

	public static void main(String[] args) {
		System.out.println("--- INICIANDO TESTE DO SISTEMA ---");

        try {
            // 1. CADASTRAR UM CLIENTE
            // -----------------------
            System.out.println("1. Cadastrando Cliente...");
            ClientePessoaFisica cliente = new ClientePessoaFisica(
                "João da Silva", "joao@teste.com", "449999-8888", "111.222.333-44"
            );
            ClientePessoaFisicaDAO clienteDAO = new ClientePessoaFisicaDAO();
            clienteDAO.cadastrar(cliente);
            System.out.println("-> Cliente salvo com ID: " + cliente.getId());

            // 2. CADASTRAR UM SERVIÇO COM PREÇO (Usando Service)
            // --------------------------------------------------
            System.out.println("\n2. Cadastrando Serviço...");
            ServicoService servicoService = new ServicoService();
            // Esse método já cria o serviço E o preço inicial
            Servico servico = servicoService.cadastrarServico("Consultoria Java", 200.00);
            System.out.println("-> Serviço salvo: " + servico.getDescricao() + " | Valor: " + servico.getValorAtual());

            // 3. CRIAR UMA PROPOSTA
            // ---------------------
            System.out.println("\n3. Criando Proposta...");
            
            // IMPORTANTE: Passamos o cliente que JÁ TEM ID (foi salvo no passo 1)
            Proposta proposta = new Proposta(cliente, LocalDate.now());

            // Criando um Item para a proposta
            ItemProposta item = new ItemProposta();
            item.setServico(servico);
            item.setQuantidade(5); // 5 horas de consultoria
            item.setPrecoUnitarioMomento(servico.getValorAtual()); // Pega o preço de 200.00
            item.calcularSubtotal(); // Calcula 5 * 200 = 1000.00
            
            // Amarrando os dois lados da relação
            item.setProposta(proposta);
            proposta.getItens().add(item);

            // Calculando total da proposta
            proposta.calcularValorTotal();

            // 4. SALVAR A PROPOSTA
            // --------------------
            PropostaDAO propostaDAO = new PropostaDAO();
            propostaDAO.cadastrar(proposta);

            System.out.println("-> Proposta salva com ID: " + proposta.getId());
            System.out.println("-> Status: " + proposta.getStatus());
            System.out.println("-> Valor Total: R$ " + proposta.getValorTotal());
            System.out.println("-> Cliente: " + proposta.getCliente().getNome());

            System.out.println("\n--- TESTE FINALIZADO COM SUCESSO ---");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JPAUTIL.close(); // Fecha a fábrica ao terminar tudo
        }
		
	}

}

package main;

import com.github.javafaker.Faker;
import model.ClientePessoaFisica;
import model.ClientePessoaJuridica;
import model.ItemProposta;
import model.Pessoa;
import model.Proposta;
import model.Servico;
import model.StatusProposta;
import service.ClienteService;
import service.PropostaService;
import service.ServicoService;
import util.JPAUTIL;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class TesteCarga {

    public static void main(String[] args) {
        System.out.println("--- TESTE DE CARGA  ---");
        long inicio = System.currentTimeMillis();

        Faker faker = new Faker(new Locale("pt-BR"));
        Random random = new Random();

        ServicoService servicoService = new ServicoService();
        ClienteService clienteService = new ClienteService();
        PropostaService propostaService = new PropostaService();

        try {
        	// criar servicos base
            List<Servico> servicosCadastrados = new ArrayList<>();
            
            for (int i = 0; i < 20; i++) { // cria 20 servicos
                String nomeServico = faker.job().title() + " " + i; // + i para garantir nome unico
                Double valor = 100.0 + random.nextInt(900);
                
                // O Service cadastra e já cria o preço inicial
                try {
                    Servico s = servicoService.cadastrarServico(nomeServico, valor);
                    servicosCadastrados.add(s);
                } catch (Exception e) {
                    System.out.println("Erro ao criar serviço base: " + e.getMessage());
                }
            }
            
            //Loop para gerar Clientes e Propostas
            System.out.println("Iniciando geração de registros (PF e PJ)...");
            
            // Defina a quantidade aqui (ex: 1000 para teste rápido, 100000 para o final)
            int qtdRegistros = 100000; 

            for (int i = 1; i <= qtdRegistros; i++) {
                
                try {
                    Pessoa clienteSalvo = null;
                    
                    // Decide se cria PF ou PJ (Par = PF, Ímpar = PJ)
                    boolean criarPJ = (i % 2 != 0); 

                    if (criarPJ) {
                        // CRIANDO PJ 
                        String cnpjFake = String.format("%014d", i); // Gera CNPJ único numérico com tamanho 14
                        String razaoSocial = faker.company().name() + " LTDA";  // padrao de cnpj
                        String nomeFantasia = faker.company().industry();
                        
                        ClientePessoaJuridica pj = new ClientePessoaJuridica(
                                nomeFantasia,
                                i + "_pj_" + faker.internet().emailAddress(), // Email único
                                faker.phoneNumber().cellPhone(),
                                cnpjFake,
                                razaoSocial
                        );
                        
                        clienteService.cadastrarPessoaJuridica(pj);
                        clienteSalvo = pj;

                    } else {
                        // CRIANDO PF 
                        String cpfFake = String.format("%011d", i); // Gera CPF único numérico tamanho 11
                        
                        ClientePessoaFisica pf = new ClientePessoaFisica(
                                faker.name().fullName(),
                                i + "_pf_" + faker.internet().emailAddress(), // Email único
                                faker.phoneNumber().cellPhone(),
                                cpfFake
                        );
                        
                        clienteService.cadastrarPessoaFisica(pf);
                        clienteSalvo = pf;
                    }

                    //Criar Proposta para o Cliente (Seja PF ou PJ)
                    Proposta proposta = new Proposta(clienteSalvo, LocalDate.now().minusDays(random.nextInt(365)));
                    
                    // Adiciona itens aleatórios
                    int qtdItens = 1 + random.nextInt(4); // 1 a 4 itens
                    for (int j = 0; j < qtdItens; j++) {
                        Servico servicoAleatorio = servicosCadastrados.get(random.nextInt(servicosCadastrados.size()));
                        
                        ItemProposta item = new ItemProposta();
                        item.setServico(servicoAleatorio);
                        item.setQuantidade(1 + random.nextInt(10));
                        item.setPrecoUnitarioMomento(servicoAleatorio.getValorAtual()); // Preço histórico
                        item.calcularSubtotal();
                        
                        // Vincula os dois lados
                        item.setProposta(proposta);
                        proposta.getItens().add(item);
                    }
                    
                    // Define status aleatório
                    proposta.setStatus(StatusProposta.values()[random.nextInt(StatusProposta.values().length)]);

                    // Salva
                    propostaService.criarProposta(proposta);

                    // fraciona a carga para nao travar 
                    if (i % 1000 == 0) {  
                        System.out.println("Progresso: " + i + " registros inseridos...");
                        // Opcional: Chamar o Garbage Collector se a memória subir muito
                    }

                } catch (Exception e) {
                    // Se der erro em um registro específico, loga e continua o loop
                    System.out.println("Erro no registro " + i + ": " + e.getMessage());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Encerra a fábrica de conexões no final de tudo
            JPAUTIL.close();
        }

        long fim = System.currentTimeMillis();
        System.out.println("--- FIM DO TESTE DE CARGA ---");
        System.out.println("Tempo total: " + (fim - inicio) / 1000 + " segundos.");
    }
}
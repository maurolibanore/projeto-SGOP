package main;

import java.time.LocalDate;
import java.util.Locale;

import com.github.javafaker.Faker;

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
		System.out.println("Testes...");

        Faker faker = new Faker(new Locale("pt-BR"));

        String nome = faker.name().fullName();
        String rua = faker.address().streetName();
        String cidade = faker.address().city();
        String email = faker.internet().emailAddress();
        String profissao = faker.job().title();

        System.out.println("Nome Gerado: " + nome);
        System.out.println("Email: " + email);
        System.out.println("Endereço: " + rua + ", " + cidade);
        System.out.println("Profissão: " + profissao);
        
		
	}

}

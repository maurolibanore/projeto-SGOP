package main;

import java.time.LocalDate;

import dao.ClientePessoaFisicaDAO;
import dao.PropostaDAO;
import model.ClientePessoaFisica;
import model.Proposta;

public class Main {

	public static void main(String[] args) {
		ClientePessoaFisica cf = new ClientePessoaFisica("mauro","dmsadsa@gmail","44984219733","12487838914");
		
		ClientePessoaFisicaDAO dao = new ClientePessoaFisicaDAO();
		
		dao.cadastrar(cf);

		Proposta proposta = new Proposta(cf,LocalDate.now());
		
		PropostaDAO daoP = new PropostaDAO();
		
		
		
	}

}

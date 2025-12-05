package main;

import java.time.LocalDate;

import dao.ClientePessoaFisicaDAO;
import dao.PropostaDAO;
import model.ClientePessoaFisica;
import model.Proposta;
import model.StatusProposta;

public class Main {

	public static void main(String[] args) {
		// ClientePessoaFisica cf = new ClientePessoaFisica();
		
		ClientePessoaFisicaDAO dao = new ClientePessoaFisicaDAO();
		
		ClientePessoaFisica cf =  dao.buscarPorId((long) 1);
		
		
		// System.out.println(cf.getNome());
		

		//Proposta proposta = new Proposta(cf,LocalDate.now());
		
		PropostaDAO daoP = new PropostaDAO();
		
		//daoP.cadastrar(proposta);	
		
		Proposta p = daoP.buscarPorId((long) 1);
		
		System.out.println(p.getStatus());
		
		System.out.println("-----------");
		
		p.setStatus(StatusProposta.ACEITA);
		
		daoP.atualizar(p);
		
		System.out.println(p.getStatus());
		
	}

}

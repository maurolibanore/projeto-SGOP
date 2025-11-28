package main;

import dao.ClientePessoaFisicaDAO;
import model.ClientePessoaFisica;

public class Main {

	public static void main(String[] args) {
		ClientePessoaFisica cf = new ClientePessoaFisica("mauro","dmsadsa@gmail","44984219733","12487838914");
		
		ClientePessoaFisicaDAO dao = new ClientePessoaFisicaDAO();
		
		dao.cadastrar(cf);

	}

}

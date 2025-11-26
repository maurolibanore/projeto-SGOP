package dao;

import javax.persistence.EntityManager;

import model.ClientePessoaFisica;
import util.JPAUTIL;

public class ClientePessoaFisicaDAO extends DaoImplementacao<ClientePessoaFisica> {

    public ClientePessoaFisicaDAO() {
        super(ClientePessoaFisica.class);
    }

    public ClientePessoaFisica buscarPorCpf(String cpf) {
        EntityManager em = JPAUTIL.getEntityManager();
        return em.createQuery("SELECT c FROM ClientePessoaFisica c WHERE c.cpf = :cpf", ClientePessoaFisica.class)
        		.setParameter("cpf", cpf).getSingleResult();
    }
}

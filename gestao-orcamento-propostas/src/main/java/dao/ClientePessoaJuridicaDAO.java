package dao;

import javax.persistence.EntityManager;

import model.ClientePessoaJuridica;
import util.JPAUTIL;

public class ClientePessoaJuridicaDAO extends DaoImplementacao<ClientePessoaJuridica> {

    public ClientePessoaJuridicaDAO() {
        super(ClientePessoaJuridica.class);
    }

    public ClientePessoaJuridica buscarPorCnpj(String cnpj) {
        EntityManager em = JPAUTIL.getEntityManager();
        return em.createQuery("SELECT c FROM ClientePessoaJuridica c WHERE c.cnpj = :cnpj", ClientePessoaJuridica.class)
                .setParameter("cnpj", cnpj)
                .getSingleResult();
    }
}
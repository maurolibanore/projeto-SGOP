package dao;

import java.util.List;
import javax.persistence.EntityManager;
import model.ClientePessoaJuridica;
import util.JPAUTIL;

public class ClientePessoaJuridicaDAO extends DaoImplementacao<ClientePessoaJuridica> {

    public ClientePessoaJuridicaDAO() {
        super(ClientePessoaJuridica.class);
    }

    public ClientePessoaJuridica buscarPorCnpj(String cnpj) {
        EntityManager em = JPAUTIL.getEntityManager();
       
        List<ClientePessoaJuridica> lista = em.createQuery("SELECT c FROM ClientePessoaJuridica c WHERE c.cnpj = :cnpj", ClientePessoaJuridica.class)
                .setParameter("cnpj", cnpj)
                .getResultList();
        
        em.close(); 

        if (lista.isEmpty()) {
            return null; 
        } else {
            return lista.get(0); 
        }
    }
}
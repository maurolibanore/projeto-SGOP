package dao;

import java.util.List;
import javax.persistence.EntityManager;
import model.ClientePessoaFisica;
import util.JPAUTIL;

public class ClientePessoaFisicaDAO extends DaoImplementacao<ClientePessoaFisica> {

    public ClientePessoaFisicaDAO() {
        super(ClientePessoaFisica.class);
    }

    public ClientePessoaFisica buscarPorCpf(String cpf) {
        EntityManager em = JPAUTIL.getEntityManager();
        
        List<ClientePessoaFisica> lista = em.createQuery("SELECT c FROM ClientePessoaFisica c WHERE c.cpf = :cpf", ClientePessoaFisica.class)
                .setParameter("cpf", cpf)
                .getResultList();
        
        em.close();

        if (lista.isEmpty()) {
            return null;
        } else {
            return lista.get(0);
        }
    }
}
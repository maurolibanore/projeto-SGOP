package dao;

import java.util.List;
import javax.persistence.EntityManager;
import model.Servico;
import util.JPAUTIL;

public class ServicoDAO extends DaoImplementacao<Servico> {

    public ServicoDAO() {
        super(Servico.class);
    }
    
    public Servico buscarPorDescricao(String descricao) {
        EntityManager em = JPAUTIL.getEntityManager();
        
        List<Servico> lista = em.createQuery("SELECT s FROM Servico s WHERE s.descricao = :n", Servico.class)
                 .setParameter("n", descricao)
                 .getResultList();
        
        em.close(); 

        if (lista.isEmpty()) {
            return null;
        } else {
            return lista.get(0);
        }
    }
}
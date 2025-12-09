package dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException; 
import model.Servico;
import util.JPAUTIL;

public class ServicoDAO extends DaoImplementacao<Servico> {

    public ServicoDAO() {
        super(Servico.class);
    }
    
    public Servico buscarPorDescricao(String descricao) {
        EntityManager em = JPAUTIL.getEntityManager(); 
        
        try {
            return em.createQuery("SELECT s FROM Servico s WHERE s.descricao = :n", Servico.class)
                     .setParameter("n", descricao)
                     .getSingleResult();
                     
        } catch (NoResultException e) {
            return null;
            
        } finally {
            em.close(); 
        }
    }
}
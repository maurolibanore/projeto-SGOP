package dao;

import java.util.List;

import javax.persistence.EntityManager;

import model.Proposta;
import util.JPAUTIL;

public class PropostaDAO extends DaoImplementacao<Proposta> {

	public PropostaDAO(Class<Proposta> classe) {
		super(classe);
	}
	
	public List<Proposta> buscarPorCliente(Long id){
		EntityManager em = JPAUTIL.getEntityManager();
		return em.createQuery("SELECT p FROM Proposta p WHERE p.cliente.id = :id",Proposta.class).setParameter("id",id).getResultList();
	}

}

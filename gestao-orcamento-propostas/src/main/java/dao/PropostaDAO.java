package dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import model.Proposta;
import model.StatusProposta;
import util.JPAUTIL;

public class PropostaDAO extends DaoImplementacao<Proposta> {

	public PropostaDAO() {
		super(Proposta.class);
	}
	
	public List<Proposta> buscarPorCliente(Long id){
		EntityManager em = JPAUTIL.getEntityManager();
		return em.createQuery("SELECT p FROM Proposta p WHERE p.cliente.id = :id",Proposta.class).setParameter("id",id).getResultList();
	}

	public List<Proposta> buscarPorStatus(StatusProposta status){
		EntityManager em = JPAUTIL.getEntityManager();
		return em.createQuery("SELECT p FROM Proposta p WHERE p.status = :status", Proposta.class).setParameter("status", status).getResultList();
	}
	
	public List<Proposta> buscarPorPeriodo(LocalDate inicio, LocalDate fim){
		EntityManager em = JPAUTIL.getEntityManager();
		return em.createQuery("SELECT p FROM Proposta p WHERE p.dataCriacao BETWEEN :inicio AND :fim", Proposta.class).setParameter("inicio", inicio)
				.setParameter("fim", fim).getResultList();
	}
	
}

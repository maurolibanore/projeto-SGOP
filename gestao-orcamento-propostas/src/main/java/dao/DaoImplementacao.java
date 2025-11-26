package dao;

import java.util.List;

import javax.persistence.EntityManager;

import util.JPAUTIL;

public abstract class DaoImplementacao<T> implements GenericDAO<T> {

	private Class<T> classe;
	
	
	public DaoImplementacao(Class<T> classe) {
		this.classe = classe;
	}
	
	
	public void cadastrar(T entidade) {
		EntityManager em = JPAUTIL.getEntityManager();
		em.getTransaction().begin();
		em.persist(entidade);
		em.getTransaction().commit();
		em.close();
	};
	
	@Override
	public void atualizar(T entidade) {
		EntityManager em = JPAUTIL.getEntityManager();
		em.getTransaction().begin();
		em.merge(entidade);
		em.getTransaction().commit();
		em.close();
	}
	
	@Override
	public T buscarPorId(Long id) {
		EntityManager em = JPAUTIL.getEntityManager();
		return em.find(classe, id);
	}
	
	@Override
	public void remover(Long id) {
		EntityManager em = JPAUTIL.getEntityManager();
		em.getTransaction().begin();
		T entidade = em.find(classe, id);
		if(entidade != null) {
			em.remove(entidade);
		}
		em.getTransaction().commit();
		em.close();
	}
	
	@Override
	public List<T> listarTodos() {
		EntityManager em = JPAUTIL.getEntityManager();
		return em.createQuery("SELECT t FROM " + classe.getSimpleName() + " t").getResultList();
	}
	
	
}

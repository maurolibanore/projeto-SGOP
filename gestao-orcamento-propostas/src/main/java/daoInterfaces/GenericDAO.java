package daoInterfaces;


import java.util.List;

import javax.persistence.EntityManager;

public class GenericDAO<T> {

    private final Class<T> classe;
    private final EntityManager em; 

    public GenericDAO(Class<T> classe, EntityManager em) {
        this.classe = classe;
        this.em = em;
    }

    public void cadastrar(T entidade) {
        try {
            em.getTransaction().begin();
            em.persist(entidade);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback(); // Desfaz se der erro
            throw e;
        }
    }

    public void atualizar(T entidade) {
        try {
            em.getTransaction().begin();
            em.merge(entidade);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    public void remover(Long id) {
        try {
            em.getTransaction().begin();
            T entidade = em.find(classe, id);
            if (entidade != null) {
                em.remove(entidade);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    public T buscarPorId(Long id) {
        return em.find(classe, id);
    }

    public List<T> listarTodos() {
        String jpql = "SELECT t FROM " + classe.getSimpleName() + " t";
        return em.createQuery(jpql, classe).getResultList();
    }
}
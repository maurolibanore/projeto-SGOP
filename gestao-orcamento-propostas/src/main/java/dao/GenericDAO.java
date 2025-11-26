package dao;

import java.util.List;

public interface GenericDAO<T> {
	
	void cadastrar(T entidade);
	
	void atualizar(T entidade);
	
	void remover(Long id);
	
	T buscarPorId(Long id);
	
	List<T> listarTodos();

}

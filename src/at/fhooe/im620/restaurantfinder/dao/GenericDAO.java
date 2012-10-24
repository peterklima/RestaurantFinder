package at.fhooe.im620.restaurantfinder.dao;

import java.util.List;

public interface GenericDAO<T> {
	
	public void saveOrUpdateEntity(T entity);
	public T getEntityById(Long id);
	public List<T> getAllEntities();
	public void deleteEntity(T entity); 
	
}

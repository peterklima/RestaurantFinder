package at.fhooe.im620.restaurantfinder.dao;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;



public class GenericDAOImpl<T>
extends HibernateDaoSupport
implements GenericDAO<T>{

	@Override
	public void deleteEntity(T entity) {
		getHibernateTemplate().delete(entity);
	}
	
	@Override
	public void saveOrUpdateEntity(T entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	private Class<T> persistentClass;
	public GenericDAOImpl() {
		this.persistentClass= 
				(Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	public Class<T> getPersistentClass() {
		return this.persistentClass;
	}
	
	@Override
	public List<T> getAllEntities() {
		Collection<T> entities=
			getHibernateTemplate().find("from "+getPersistentClass().getName());
		
		return new ArrayList<T>(entities);	
	}

	@Override
	public T getEntityById(Long id) {
		return (T) 
			getHibernateTemplate().load(getPersistentClass(), id);
	}
}

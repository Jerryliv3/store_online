package mx.com.ms.ecommerce.gestion.usuarios.service.service;

import java.util.List;

import mx.com.ms.ecommerce.gestion.usuarios.service.exception.ServiceException;

public interface GenericeService<T> {
	
	List<T> findByLike(T t) throws ServiceException;
	
	T findById(Long id)throws ServiceException;
	
	T save (T t) throws ServiceException;
	
	T deleteById (Long id) throws ServiceException;
	
	T update (T t, Long id) throws ServiceException;
}

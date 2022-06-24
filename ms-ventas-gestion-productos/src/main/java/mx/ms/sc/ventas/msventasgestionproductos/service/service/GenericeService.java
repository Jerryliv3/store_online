package mx.ms.sc.ventas.msventasgestionproductos.service.service;

import java.util.List;

import mx.ms.sc.ventas.msventasgestionproductos.service.exception.ServiceException;

public interface GenericeService<T> {
	
	List<T> findByLike(T t) throws ServiceException;
	
	T findById(Long id)throws ServiceException;
	
	T save (T t) throws ServiceException;
	
	T deleteById (Long id) throws ServiceException;
	
	T update (T t, Long id) throws ServiceException;
}

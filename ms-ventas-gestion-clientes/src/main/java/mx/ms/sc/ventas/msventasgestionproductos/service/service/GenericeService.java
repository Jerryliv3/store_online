package mx.ms.sc.ventas.msventasgestionproductos.service.service;

import java.util.List;
import java.util.Optional;

import mx.ms.sc.ventas.msventasgestionproductos.service.exception.ServiceException;

public interface GenericeService<T> {
	
	List<T> findByLike(T t) throws ServiceException;
	
	Optional<T> findById(Long id)throws ServiceException;
	
	T save(T t)throws ServiceException;
	

}

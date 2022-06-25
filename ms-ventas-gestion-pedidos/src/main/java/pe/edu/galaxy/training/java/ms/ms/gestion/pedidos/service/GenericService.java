package pe.edu.galaxy.training.java.ms.ms.gestion.pedidos.service;

import java.util.List;
import java.util.Optional;

import pe.edu.galaxy.training.java.ms.ms.gestion.pedidos.service.exception.ServiceException;

public interface GenericService<T> {
	
	List<T> findLike(T t) throws ServiceException;

	Optional<T> findById(T t) throws ServiceException;
	
	T save(T t) throws ServiceException;
}

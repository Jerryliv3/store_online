package ms.ecommerce.ventas.personas.api.service.service;

import java.util.List;

import ms.ecommerce.ventas.personas.api.dto.BusquedaPersonaDTO;
import ms.ecommerce.ventas.personas.api.dto.PersonaDTO;
import ms.ecommerce.ventas.personas.api.models.Response;
import ms.ecommerce.ventas.personas.api.service.exception.ServiceException;

public interface GenericeService<T> {
	
	List<T> findByLike(T t) throws ServiceException;
	
	T findById(Long id)throws ServiceException;
	
	Response save (T t) throws ServiceException;
	
	T deleteById (Long id) throws ServiceException;
	
	T update (T t, Long id) throws ServiceException;
	
}

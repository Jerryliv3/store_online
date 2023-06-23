package ms.ecommerce.ventas.personas.api.service;

import ms.ecommerce.ventas.personas.api.dto.BusquedaPersonaDTO;
import ms.ecommerce.ventas.personas.api.dto.PersonaDTO;
import ms.ecommerce.ventas.personas.api.dto.RegistroPersonaDTO;
import ms.ecommerce.ventas.personas.api.models.Response;
import ms.ecommerce.ventas.personas.api.service.exception.ServiceException;
import ms.ecommerce.ventas.personas.api.service.service.GenericeService;

public interface IPersonaService extends GenericeService<RegistroPersonaDTO> {
	
	public Response findListPersonas (PersonaDTO personaDTO);
	public Response findByLikeProp(BusquedaPersonaDTO busquedaPersonaDTO) throws ServiceException;

}

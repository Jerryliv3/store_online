package ms.ecommerce.ventas.personas.security.service.service;

import ms.ecommerce.ventas.personas.security.dto.UsuarioDTO;
import ms.ecommerce.ventas.personas.security.models.Response;

public interface GenericeService<T> {
	
	public Response findUser (T t);
	
	public Response findUser (String username);
	
	public Response findRolUser (UsuarioDTO usuarioDTO);
	
}

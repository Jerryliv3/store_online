package ms.ecommerce.ventas.usuarios.security.service.service;

import ms.ecommerce.ventas.usuarios.security.dto.UsuarioDTO;
import ms.ecommerce.ventas.usuarios.security.models.Response;

public interface GenericeService<T> {
	
	public Response findUser (T t);
	
	public Response findUser (String username);
	
	public Response findRolUser (UsuarioDTO usuarioDTO);
	
}

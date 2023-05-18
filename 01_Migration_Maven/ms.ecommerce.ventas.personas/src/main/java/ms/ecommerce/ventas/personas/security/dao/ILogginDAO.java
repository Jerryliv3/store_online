package ms.ecommerce.ventas.personas.security.dao;

import org.springframework.stereotype.Repository;

import ms.ecommerce.ventas.personas.security.entity.LogginEntity;
import ms.ecommerce.ventas.personas.security.entity.UsuarioEntity;
import ms.ecommerce.ventas.personas.security.models.Response;

@Repository
public interface ILogginDAO {

	public Response getUser (LogginEntity logginEntity);
	
	public Response getUser (String username);
	
	public Response getListUserRol(UsuarioEntity usuarioEntity);
}

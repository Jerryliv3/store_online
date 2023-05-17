package ms.ecommerce.ventas.usuarios.security.dao;

import org.springframework.stereotype.Repository;

import ms.ecommerce.ventas.usuarios.security.entity.LogginEntity;
import ms.ecommerce.ventas.usuarios.security.entity.UsuarioEntity;
import ms.ecommerce.ventas.usuarios.security.models.Response;

@Repository
public interface ILogginDAO {

	public Response getUser (LogginEntity logginEntity);
	
	public Response getListUserRol(UsuarioEntity usuarioEntity);
}

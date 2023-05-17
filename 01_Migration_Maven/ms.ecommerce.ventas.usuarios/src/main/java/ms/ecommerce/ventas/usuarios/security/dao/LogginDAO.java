package ms.ecommerce.ventas.usuarios.security.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ms.ecommerce.ventas.usuarios.security.entity.LogginEntity;
import ms.ecommerce.ventas.usuarios.security.entity.UsuarioEntity;
import ms.ecommerce.ventas.usuarios.security.models.Response;

@Repository
public class LogginDAO implements ILogginDAO {
	
	@Autowired
	private IExecDAO dao;

	@Override
	public Response getUser(LogginEntity logginEntity) {
		Response response = dao.execStoredProcedure(logginEntity, "scObtenerUsuario");
		return response;
	}

	@Override
	public Response getListUserRol(UsuarioEntity usuarioEntity) {
		Response response = dao.execStoredProcedure(usuarioEntity, "scObtenerRolUsuario");
		return response;
	}



}

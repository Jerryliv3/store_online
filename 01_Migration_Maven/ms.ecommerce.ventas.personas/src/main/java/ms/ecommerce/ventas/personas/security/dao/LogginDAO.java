package ms.ecommerce.ventas.personas.security.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ms.ecommerce.ventas.personas.security.entity.LogginEntity;
import ms.ecommerce.ventas.personas.security.entity.UsuarioEntity;
import ms.ecommerce.ventas.personas.security.models.Response;

@Repository
public class LogginDAO implements ILogginDAO {
	
	@Autowired
	private IExecSPDAO dao;

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

	@Override
	public Response getUser(String username) {
		LogginEntity logginEntity = new LogginEntity (username,"");
		Response response = dao.execStoredProcedure(logginEntity, "scObtenerUsuarioLogin");
		return response;
	}



}

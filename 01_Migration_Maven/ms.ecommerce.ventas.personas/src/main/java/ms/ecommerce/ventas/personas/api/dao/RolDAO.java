package ms.ecommerce.ventas.personas.api.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ms.ecommerce.ventas.personas.api.entity.RolEntity;
import ms.ecommerce.ventas.personas.api.entity.RolListEntity;
import ms.ecommerce.ventas.personas.api.models.Response;

@Repository
public class RolDAO implements IRolDAO {
	
	@Autowired
	private IExecDAO dao;

	@Override
	public Response saveListRol(RolListEntity rolEntity) {
		Response response = dao.execStoredProcedure(rolEntity, "scGuardarRoles");
		return response;
	}

	@Override
	public Response deleteRol(RolEntity rolEntity) {
		Response response = dao.execStoredProcedure(rolEntity, "");
		return response;
	}

	@Override
	public Response getListRol(List<RolEntity> rolEntity) {
		Response response = dao.execStoredProcedure(rolEntity, "");
		return response;
	}
	
	

}

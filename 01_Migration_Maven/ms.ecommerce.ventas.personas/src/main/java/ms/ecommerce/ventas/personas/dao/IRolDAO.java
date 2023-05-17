package ms.ecommerce.ventas.personas.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import ms.ecommerce.ventas.personas.entity.RolEntity;
import ms.ecommerce.ventas.personas.entity.RolListEntity;
import ms.ecommerce.ventas.personas.models.Response;

@Repository
public interface IRolDAO {
	public Response saveListRol (RolListEntity rolEntity);
	
	public Response deleteRol (RolEntity rolEntity);
	
	public Response getListRol (List<RolEntity> rolEntity);

}

package ms.ecommerce.ventas.personas.api.dao;

import java.util.List;

import ms.ecommerce.ventas.personas.api.models.Response;

public interface IExecDAO {

	public Response execStoredProcedure(Object object, String name);
}

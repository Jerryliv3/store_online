package ms.ecommerce.ventas.personas.dao;

import java.util.List;

import ms.ecommerce.ventas.personas.models.Response;

public interface IExecDAO {

	public Response execStoredProcedure(Object object, String name);
}

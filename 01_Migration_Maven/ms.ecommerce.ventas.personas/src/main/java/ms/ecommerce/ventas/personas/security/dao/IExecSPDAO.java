package ms.ecommerce.ventas.personas.security.dao;

import ms.ecommerce.ventas.personas.security.models.Response;

public interface IExecSPDAO {

	public Response execStoredProcedure(Object object, String name);
}

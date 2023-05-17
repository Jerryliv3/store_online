package ms.ecommerce.ventas.usuarios.security.dao;

import ms.ecommerce.ventas.usuarios.security.models.Response;

public interface IExecDAO {

	public Response execStoredProcedure(Object object, String name);
}

package ms.ecommerce.ventas.usuarios.security.dao;

import ms.ecommerce.ventas.usuarios.security.models.Response;

public interface IExecSPDAO {

	public Response execStoredProcedure(Object object, String name);
}

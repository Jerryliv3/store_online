package ms.ecommerce.ventas.personas.api.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ms.ecommerce.ventas.personas.api.entity.PaginadoEntity;
import ms.ecommerce.ventas.personas.api.entity.PersonaEntity;
import ms.ecommerce.ventas.personas.api.entity.UsuarioEntity;
import ms.ecommerce.ventas.personas.api.models.Response;

@Repository
public class PersonaDAO implements IPersonaDAO {

	@Autowired
	private IExecDAO dao;
	
	@Override
	public Response savePerson(PersonaEntity personaEntity) {
		Response response = dao.execStoredProcedure(personaEntity, "scGuardarPersona");
		return response;
	}

	@Override
	public Response savePersonUser(UsuarioEntity usuarioEntity) {
		Response response = dao.execStoredProcedure(usuarioEntity, "scGuardarUsuario");
		return response;
	}

	@Override
	public Response deletePerson(PersonaEntity personaEntity) {
		Response response = dao.execStoredProcedure(personaEntity, "scEliminarPersona");
		return response;
	}

	@Override
	public Response deletePersonUser(UsuarioEntity usuarioEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getListPerson(PaginadoEntity paginadoentity) {
		Response response = dao.execStoredProcedure(paginadoentity, "scObtenerPersonas");
		return response;
	}
	
}

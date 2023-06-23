package ms.ecommerce.ventas.personas.api.dao;

import org.springframework.stereotype.Repository;

import ms.ecommerce.ventas.personas.api.entity.BusquedaPersonaEntity;
import ms.ecommerce.ventas.personas.api.entity.PaginadoEntity;
import ms.ecommerce.ventas.personas.api.entity.PersonaEntity;
import ms.ecommerce.ventas.personas.api.entity.UsuarioEntity;
import ms.ecommerce.ventas.personas.api.models.Response;

@Repository
public interface IPersonaDAO {
	public Response savePerson (PersonaEntity personaEntity);
	
	public Response deletePerson (PersonaEntity personaEntity);
	
	public Response savePersonUser (UsuarioEntity usuarioEntity);
	
	public Response deletePersonUser (UsuarioEntity usuarioEntity);
	
	public Response getListPerson (PaginadoEntity paginadoEntity);
	
	public Response getPerson (BusquedaPersonaEntity busquedaPersonaEntity);
}

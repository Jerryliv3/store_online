package ms.ecommerce.ventas.personas.dao;

import org.springframework.stereotype.Repository;

import ms.ecommerce.ventas.personas.entity.PersonaEntity;
import ms.ecommerce.ventas.personas.entity.UsuarioEntity;
import ms.ecommerce.ventas.personas.models.Response;

@Repository
public interface IPersonaDAO {
	public Response savePerson (PersonaEntity personaEntity);
	
	public Response deletePerson (PersonaEntity personaEntity);
	
	public Response savePersonUser (UsuarioEntity usuarioEntity);
	
	public Response deletePersonUser (UsuarioEntity usuarioEntity);
}

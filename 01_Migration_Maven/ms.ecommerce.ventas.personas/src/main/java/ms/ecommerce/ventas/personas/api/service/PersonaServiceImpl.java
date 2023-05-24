package ms.ecommerce.ventas.personas.api.service;

import static ms.ecommerce.ventas.personas.api.commons.GlobalMessages.*;

import java.util.List;
import java.util.Objects;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import lombok.extern.slf4j.Slf4j;
import ms.ecommerce.ventas.personas.api.dao.IPersonaDAO;
import ms.ecommerce.ventas.personas.api.dto.PersonaDTO;
import ms.ecommerce.ventas.personas.api.dto.RegistroPersonaDTO;
import ms.ecommerce.ventas.personas.api.entity.PaginadoEntity;
import ms.ecommerce.ventas.personas.api.entity.PersonaEntity;
import ms.ecommerce.ventas.personas.api.entity.UsuarioEntity;
import ms.ecommerce.ventas.personas.api.models.Response;
import ms.ecommerce.ventas.personas.api.service.exception.ServiceException;

@Slf4j
@Service
public class PersonaServiceImpl implements IPersonaService {

	private IPersonaDAO personaDAO;
	private JsonMapper jsonMapper;
	private Gson gson;
	private Response response = new Response();
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public PersonaServiceImpl(IPersonaDAO personaDAO, JsonMapper jsonMapper, Gson gson,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.jsonMapper = jsonMapper;
		this.personaDAO = personaDAO;
		this.gson = gson;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public Response save(RegistroPersonaDTO registroDTO) throws ServiceException {
		Long personaId = 0L;
		try {
			if (!Objects.isNull(registroDTO.getPersona())) {
				registroDTO.getUsuario()
						.setPassword(bCryptPasswordEncoder.encode(registroDTO.getUsuario().getPassword()));
				response = personaDAO.savePerson(getPersonaEntity(registroDTO.getPersona()));
				if (response.getIsCorrect().equals("true")) {
					PersonaDTO persona = new PersonaDTO();
					List<PersonaDTO> listPersonaDTO = gson.fromJson(response.getData().getInfo().getResult(),new TypeToken<List<PersonaDTO>>() {}.getType());
					persona = listPersonaDTO.get(0);
					personaId = persona.getId();
					response.setRowsEntitites(persona);
					registroDTO.getUsuario().setPersonaId(personaId);

					if (!Objects.isNull(registroDTO.getUsuario())) {
						response = personaDAO
								.savePersonUser(jsonMapper.convertValue(registroDTO.getUsuario(), UsuarioEntity.class));
						if (response.getIsCorrect().equals(IS_CORRECT_FALSE)) {
							personaDAO.deletePerson(jsonMapper.convertValue(persona, PersonaEntity.class));
						} else {
							response.setRowsEntitites(persona);
						}
					}

				}
			} else {
				response.setIsCorrect("false");
				response.setIsBreakOperation("true");
				response.setMessage(OBJECT_NULL);
			}
			return response;
		} catch (Exception e) {
			log.error("Error al procesar la información: {}", e);
			response.setIsCorrect("false");
			response.setIsBreakOperation("true");
			response.setMessage(e.getMessage());
		}
		return response;
	}

	@Override
	public List<RegistroPersonaDTO> findByLike(RegistroPersonaDTO t) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RegistroPersonaDTO findById(Long id) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RegistroPersonaDTO deleteById(Long id) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RegistroPersonaDTO update(RegistroPersonaDTO t, Long id) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}


	private PersonaEntity getPersonaEntity(PersonaDTO d) {
		return jsonMapper.convertValue(d, PersonaEntity.class);
	}

	@Override
	public Response findListPersonas(PersonaDTO personaDTO) {

		try {
			if (Objects.nonNull(personaDTO.getPaginado())) {

				PaginadoEntity paginadoEntity = jsonMapper.convertValue(personaDTO.getPaginado(), PaginadoEntity.class);
				response = personaDAO.getListPerson(paginadoEntity);
				if (response.getIsCorrect().equals("true")) {
					List<PersonaDTO> listPersonaDTO = gson.fromJson(response.getData().getInfo().getResult(),new TypeToken<List<PersonaDTO>>() {}.getType());
					response.setRowsEntitites(listPersonaDTO);
				}

			} else {
				response.setIsCorrect("false");
				response.setIsBreakOperation("true");
				response.setMessage(OBJECT_NULL);
			}
			return response;
		} catch (Exception e) {
			log.error("Error al procesar la información: {}", e);
			response.setIsCorrect("false");
			response.setIsBreakOperation("true");
			response.setMessage(e.getMessage());
		}
		return response;
	}

}

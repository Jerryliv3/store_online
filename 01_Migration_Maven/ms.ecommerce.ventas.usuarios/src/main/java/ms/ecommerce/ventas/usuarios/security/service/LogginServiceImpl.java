package ms.ecommerce.ventas.usuarios.security.service;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import lombok.extern.slf4j.Slf4j;
import ms.ecommerce.ventas.usuarios.security.dao.ILogginDAO;
import ms.ecommerce.ventas.usuarios.security.dto.LogginDTO;
import ms.ecommerce.ventas.usuarios.security.dto.RolDTO;
import ms.ecommerce.ventas.usuarios.security.dto.UsuarioDTO;
import ms.ecommerce.ventas.usuarios.security.entity.LogginEntity;
import ms.ecommerce.ventas.usuarios.security.entity.UsuarioEntity;
import ms.ecommerce.ventas.usuarios.security.models.Response;

import static java.util.Objects.isNull;
import static ms.ecommerce.ventas.usuarios.security.commons.GlobalMessages.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class LogginServiceImpl implements ILogginService {
	
	private ILogginDAO logginDAO;
	private JsonMapper jsonMapper;
	private Gson gson;
	
	public LogginServiceImpl( ILogginDAO logginDAO,
				JsonMapper jsonMapper,
				Gson gson
			) {
		super();
		this.jsonMapper=jsonMapper;
		this.logginDAO = logginDAO;
		this.gson = gson;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response findUser(LogginDTO logginDTO) {
		Response response = new Response ();
		try {
			if ( !isNull(logginDTO) ) {
				response = logginDAO.getUser(getLogginEntity(logginDTO));
				if ( response.getIsCorrect().equals(IS_CORRECT_TRUE) ) {
					UsuarioDTO usuarioDTO = new UsuarioDTO();
					List<UsuarioDTO> listUsuarioDTO = gson.fromJson(response.getData().getInfo().getResult(), new TypeToken <List<UsuarioDTO>>(){}.getType());
					if ( !isNull(listUsuarioDTO) ) {
						Response responseRol = new Response();
						usuarioDTO = listUsuarioDTO.get(0);
						responseRol = findRolUser(usuarioDTO);
						if ( responseRol.getIsCorrect().equals(IS_CORRECT_TRUE) ) {
							usuarioDTO.setListRoles((List<RolDTO>)responseRol.getRowsEntitites());
						}
						response.setRowsEntitites(usuarioDTO);
					}
					
				}
			} else {
				response.setIsCorrect("false");
				response.setIsBreakOperation("true");
				response.setMessage(OBJECT_NULL);
			}
			
		} catch (Exception e) {
			response.setIsCorrect("false");
			response.setIsBreakOperation("true");
			response.setMessage(e.getMessage());
			log.error("Error al procesar la información: {}", e);
		}

		return response;
	}

	@Override
	public Response findRolUser(UsuarioDTO usuarioDTO) {
		Response response = new Response ();
		try {
			if ( !isNull(usuarioDTO) ) {
				response = logginDAO.getListUserRol(jsonMapper.convertValue(usuarioDTO, UsuarioEntity.class));
				if ( response.getIsCorrect().equals(IS_CORRECT_TRUE) ) {
					List<RolDTO> listRolDTO = new ArrayList<RolDTO>();
					listRolDTO = gson.fromJson(response.getData().getInfo().getResult(), new TypeToken <List<RolDTO>>(){}.getType());
					response.setRowsEntitites(listRolDTO);
				}
					
			} else {
				response.setIsCorrect("false");
				response.setIsBreakOperation("true");
				response.setMessage(OBJECT_NULL);
			}
		} catch (Exception e) {
			response.setIsCorrect("false");
			response.setIsBreakOperation("true");
			response.setMessage(e.getMessage());
			log.error("Error al procesar la información: {}", e);
		}
 			
 		return response;
	}
	
	private LogginEntity getLogginEntity(LogginDTO d) {
		return jsonMapper.convertValue(d, LogginEntity.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response findUser(String username) {
		Response response = new Response ();
		try {
			if ( !isNull(username) ) {
				response = logginDAO.getUser(username);
				if ( response.getIsCorrect().equals(IS_CORRECT_TRUE) ) {
					UsuarioDTO usuarioDTO = new UsuarioDTO();
					List<UsuarioDTO> listUsuarioDTO = gson.fromJson(response.getData().getInfo().getResult(), new TypeToken <List<UsuarioDTO>>(){}.getType());
					if ( !isNull(listUsuarioDTO) ) {
						Response responseRol = new Response();
						usuarioDTO = listUsuarioDTO.get(0);
						responseRol = findRolUser(usuarioDTO);
						if ( responseRol.getIsCorrect().equals(IS_CORRECT_TRUE) ) {
							usuarioDTO.setListRoles((List<RolDTO>)responseRol.getRowsEntitites());
						}
						response.setRowsEntitites(usuarioDTO);
					}
					
				}
			} else {
				response.setIsCorrect("false");
				response.setIsBreakOperation("true");
				response.setMessage(OBJECT_NULL);
			}
			
		} catch (Exception e) {
			response.setIsCorrect("false");
			response.setIsBreakOperation("true");
			response.setMessage(e.getMessage());
			log.error("Error al procesar la información: {}", e);
		}

		return response;
	}

}

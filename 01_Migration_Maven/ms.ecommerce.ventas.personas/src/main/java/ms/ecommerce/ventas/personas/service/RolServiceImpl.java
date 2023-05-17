package ms.ecommerce.ventas.personas.service;

import java.util.ArrayList;
import java.util.List;
import static java.util.Objects.isNull;
import static ms.ecommerce.ventas.personas.commons.GlobalMessages.*;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import lombok.extern.slf4j.Slf4j;
import ms.ecommerce.ventas.personas.dao.IRolDAO;
import ms.ecommerce.ventas.personas.dto.RegistroRolDTO;
import ms.ecommerce.ventas.personas.dto.RolDTO;
import ms.ecommerce.ventas.personas.entity.RolEntity;
import ms.ecommerce.ventas.personas.entity.RolListEntity;
import ms.ecommerce.ventas.personas.models.Response;
import ms.ecommerce.ventas.personas.service.exception.ServiceException;

@Slf4j
@Service
public class RolServiceImpl implements IRolService {
	
	private IRolDAO rolDAO;
	private JsonMapper jsonMapper;
	private Gson gson;
	private Response response = new Response ();
	
	public RolServiceImpl(	IRolDAO rolDAO,
										JsonMapper jsonMapper,
										Gson gson
									) {
		super();
		this.jsonMapper=jsonMapper;
		this.rolDAO = rolDAO;
		this.gson = gson;
	}


	@Override
	public Response save(RegistroRolDTO registroRolDTO) throws ServiceException {
		try {
			
			if ( !isNull(registroRolDTO.getListRol()) ) {
				List<RolEntity> listRolEntities = new ArrayList<RolEntity>();
				for ( RolDTO rolDTO : registroRolDTO.getListRol() ) { // * (Mejorar)
					listRolEntities.add( jsonMapper.convertValue(rolDTO , RolEntity.class));
				}
				RolListEntity rolListEntity = new RolListEntity();
				rolListEntity.setListRol(listRolEntities);
				response = rolDAO.saveListRol(rolListEntity);
				
				if ( response.getIsCorrect().equals(IS_CORRECT_TRUE) ) {
					List<RolDTO> listRolDTO = gson.fromJson(response.getData().getInfo().getResult(), new TypeToken <List<RolDTO>>(){}.getType());
					response.setRowsEntitites(listRolDTO);
				}
				
			} else {
				
				response.setIsCorrect("false");
				response.setIsBreakOperation("true");
				response.setMessage(OBJECT_NULL);
			}	
		} catch (Exception e) {
			log.error("Error al procesar la información: ", e.toString());
			response.setIsCorrect("false");
			response.setIsBreakOperation("true");
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	@Override
	public List<RegistroRolDTO> findByLike(RegistroRolDTO t) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RegistroRolDTO findById(Long id) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RegistroRolDTO deleteById(Long id) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RegistroRolDTO update(RegistroRolDTO t, Long id) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}

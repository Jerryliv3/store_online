package mx.com.ms.ecommerce.gestion.usuarios.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.json.JsonMapper;

import mx.com.ms.ecommerce.gestion.usuarios.dto.UsuarioDTO;
import mx.com.ms.ecommerce.gestion.usuarios.entity.UsuarioEntity;
import mx.com.ms.ecommerce.gestion.usuarios.repository.UsuarioRepository;
import mx.com.ms.ecommerce.gestion.usuarios.service.exception.ServiceException;

@Service
public class UsuarioServiceImpl implements UsuarioService{
 
	private UsuarioRepository usuarioRepository;
	private JsonMapper jsonMapper;
	
	public UsuarioServiceImpl(	UsuarioRepository productoRepository,
								JsonMapper jsonMapper
									) {
		super();
		this.usuarioRepository = productoRepository;
		this.jsonMapper=jsonMapper;
	}

	@Override
	@Transactional(readOnly=true) 
	public List<UsuarioDTO> findByLike(UsuarioDTO t) throws ServiceException {
		try {
			List<UsuarioEntity> lUsuarioEntity= usuarioRepository.findAll();
			return lUsuarioEntity.stream().map(e -> this.getUsuarioDTO(e))
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}

	@Override
	@Transactional(readOnly=true) 
	public UsuarioDTO findById (Long id) throws ServiceException {
		try {
			UsuarioDTO usuarioDTO = new UsuarioDTO ();
			Optional<UsuarioEntity> oUsuarioEntity = usuarioRepository.findById(id);
			if (oUsuarioEntity.isPresent()) {
				return this.getUsuarioDTO(oUsuarioEntity.get());
			}
			return usuarioDTO;
		} catch ( Exception e ) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public UsuarioDTO save(UsuarioDTO usuarioDTO) throws ServiceException {
		try {
			UsuarioEntity oUsuarioEntity = usuarioRepository.save(this.getUsuarioEntity(usuarioDTO));
			return this.getUsuarioDTO(oUsuarioEntity);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public UsuarioDTO deleteById(Long id) throws ServiceException {
		try {
			UsuarioDTO usuarioDTO = findById(id);
			if ( usuarioDTO.getId() == null ) {
				return usuarioDTO;
			}
			usuarioRepository.deleteById(id);
			return usuarioDTO;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}
	
	@Override
	public UsuarioDTO update(UsuarioDTO usuarioDTO, Long id) throws ServiceException {
		try {
			UsuarioDTO usuarioDTOFind = findById(id);
			if ( usuarioDTOFind.getId() == null ) {
				return usuarioDTOFind;
			}
			UsuarioEntity usuarioEntity = usuarioRepository.save(this.getUsuarioEntity(usuarioDTO));
			return this.getUsuarioDTO(usuarioEntity);
			
		} catch (Exception e) {
			throw new ServiceException (e);
		}
	}

	// Mappers convercion DTO - Entity,  Entity - DTO
	private UsuarioDTO getUsuarioDTO(UsuarioEntity e) {
		return jsonMapper.convertValue(e, UsuarioDTO.class);
	}
	
	private UsuarioEntity getUsuarioEntity(UsuarioDTO d) {
		return jsonMapper.convertValue(d, UsuarioEntity.class);
	}
	
}

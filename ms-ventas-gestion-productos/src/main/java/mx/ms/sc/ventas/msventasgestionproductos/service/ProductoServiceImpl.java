package mx.ms.sc.ventas.msventasgestionproductos.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.json.JsonMapper;

import mx.ms.sc.ventas.msventasgestionproductos.dto.ProductoDTO;
import mx.ms.sc.ventas.msventasgestionproductos.entity.ProductoEntity;
import mx.ms.sc.ventas.msventasgestionproductos.repository.ProductoRepository;
import mx.ms.sc.ventas.msventasgestionproductos.service.exception.ServiceException;

@Service
public class ProductoServiceImpl implements ProductoService{
 
	private ProductoRepository productoRepository;
	private JsonMapper jsonMapper;
	
	public ProductoServiceImpl(	ProductoRepository productoRepository,
								JsonMapper jsonMapper
									) {
		super();
		this.productoRepository = productoRepository;
		this.jsonMapper=jsonMapper;
	}

	@Override
	@Transactional(readOnly=true) 
	public List<ProductoDTO> findByLike(ProductoDTO t) throws ServiceException {
		try {
			List<ProductoEntity> lProductoEntity= productoRepository.findAll();
			return lProductoEntity.stream().map(e -> this.getProductoDTO(e))
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}

	@Override
	@Transactional(readOnly=true) 
	public ProductoDTO findById (Long id) throws ServiceException {
		try {
			ProductoDTO productoDTO = new ProductoDTO ();
			Optional<ProductoEntity> oProductoEntity = productoRepository.findById(id);
			if (oProductoEntity.isPresent()) {
				return this.getProductoDTO(oProductoEntity.get());
			}
			return productoDTO;
		} catch ( Exception e ) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public ProductoDTO save(ProductoDTO productoDTO) throws ServiceException {
		try {
			ProductoEntity oProductoEntity = productoRepository.save(this.getProductoEntity(productoDTO));
			return this.getProductoDTO(oProductoEntity);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public ProductoDTO deleteById(Long id) throws ServiceException {
		try {
			ProductoDTO productoDTO = findById(id);
			if ( productoDTO.getId() == null ) {
				return productoDTO;
			}
			productoRepository.deleteById(id);
			return productoDTO;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}
	
	@Override
	public ProductoDTO update(ProductoDTO productoDTO, Long id) throws ServiceException {
		try {
			ProductoDTO productoDTOFind = findById(id);
			if ( productoDTOFind.getId() == null ) {
				return productoDTOFind;
			}
			ProductoEntity oProductoEntity = productoRepository.save(this.getProductoEntity(productoDTO));
			return this.getProductoDTO(oProductoEntity);
			
		} catch (Exception e) {
			throw new ServiceException (e);
		}
	}

	// Mappers convercion DTO - Entity,  Entity - DTO
	private ProductoDTO getProductoDTO(ProductoEntity e) {
		return jsonMapper.convertValue(e, ProductoDTO.class);
	}
	
	private ProductoEntity getProductoEntity(ProductoDTO d) {
		return jsonMapper.convertValue(d, ProductoEntity.class);
	}
	
}

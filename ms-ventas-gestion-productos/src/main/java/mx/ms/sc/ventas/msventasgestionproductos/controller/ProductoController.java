package mx.ms.sc.ventas.msventasgestionproductos.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import mx.ms.sc.ventas.msventasgestionproductos.dto.ProductoDTO;
import mx.ms.sc.ventas.msventasgestionproductos.service.ProductoService;

import static mx.ms.sc.ventas.msventasgestionproductos.commons.GobalConstants.API_PRODUCTO;
import static mx.ms.sc.ventas.msventasgestionproductos.commons.GobalConstants.COD_CONSULTA;
import static mx.ms.sc.ventas.msventasgestionproductos.commons.GobalConstants.MSG_CONSULTA;
import static mx.ms.sc.ventas.msventasgestionproductos.commons.GobalConstants.API_PRODUCTO_ID;
import static mx.ms.sc.ventas.msventasgestionproductos.commons.GobalConstants.API_PATH_VAR_ID;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(API_PRODUCTO)
public class ProductoController {

	@Autowired
	private ProductoService productoService;
	
	@GetMapping
	public ResponseEntity<Response> findByLike(){	
		try {
			List <ProductoDTO> lProductoDTO = productoService.findByLike(null);
			if ( lProductoDTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
				return ResponseEntity.ok(
						Response
						.builder()
						.message(Message.builder().code(COD_CONSULTA).message(MSG_CONSULTA).build())
						.data(lProductoDTO)
						.build());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@GetMapping(API_PRODUCTO_ID)
	public ResponseEntity<Response> findById(@PathVariable(API_PATH_VAR_ID) long id) {
		try {
			ProductoDTO productoDTO = productoService.findById(id);
			if ( productoDTO.getId() == null ) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(
					Response
					.builder()
					.message(Message.builder().code(COD_CONSULTA).message(MSG_CONSULTA).build())
					.data(productoDTO)
					.build()
					);
		} catch ( Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}	
	}
	
	@PostMapping
	public ResponseEntity<Response> save (@RequestBody ProductoDTO reqProductoDTO) {
		try {
			ProductoDTO productoDTO = productoService.save(reqProductoDTO);
			return ResponseEntity.ok(
					Response
					.builder()
					.message(Message.builder().code(COD_CONSULTA).message(MSG_CONSULTA).build())
					.data(productoDTO)
					.build()
					);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@DeleteMapping(API_PRODUCTO_ID)
	public ResponseEntity<Response> delete (@PathVariable(API_PATH_VAR_ID) long id) {
		try {
			ProductoDTO productoDTO = productoService.deleteById(id);
			if ( productoDTO.getId() == null ) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(
					Response
					.builder()
					.message(Message.builder().code(COD_CONSULTA).message(MSG_CONSULTA).build())
					.data(productoDTO)
					.build()
					);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@PutMapping(API_PRODUCTO_ID)
	public ResponseEntity<Response> update (@PathVariable(API_PATH_VAR_ID)Long id, @RequestBody ProductoDTO reqProductoDTO) {
		try {
			ProductoDTO productoDTO = productoService.update(reqProductoDTO, id);
			if ( productoDTO.getId() == null) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(
					Response
					.builder()
					.message(Message.builder().code(COD_CONSULTA).message(MSG_CONSULTA).build())
					.data(productoDTO)
					.build()
					);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}
	}
}

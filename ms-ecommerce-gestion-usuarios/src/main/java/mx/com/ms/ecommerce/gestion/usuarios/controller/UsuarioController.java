package mx.com.ms.ecommerce.gestion.usuarios.controller;


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
import mx.com.ms.ecommerce.gestion.usuarios.dto.UsuarioDTO;
import mx.com.ms.ecommerce.gestion.usuarios.service.UsuarioService;

import static mx.com.ms.ecommerce.gestion.usuarios.commons.GobalConstants.API_PATH_VAR_ID;
import static mx.com.ms.ecommerce.gestion.usuarios.commons.GobalConstants.API_USUARIO;
import static mx.com.ms.ecommerce.gestion.usuarios.commons.GobalConstants.API_USUARIO_ID;
import static mx.com.ms.ecommerce.gestion.usuarios.commons.GobalConstants.COD_CONSULTA;
import static mx.com.ms.ecommerce.gestion.usuarios.commons.GobalConstants.MSG_CONSULTA;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(API_USUARIO)
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<Response> findByLike(){	
		try {
			List <UsuarioDTO> lUsuarioDTO = usuarioService.findByLike(null);
			if ( lUsuarioDTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
				return ResponseEntity.ok(
						Response
						.builder()
						.message(Message.builder().code(COD_CONSULTA).message(MSG_CONSULTA).build())
						.data(lUsuarioDTO)
						.build());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@GetMapping(API_USUARIO_ID)
	public ResponseEntity<Response> findById(@PathVariable(API_PATH_VAR_ID) long id) {
		try {
			UsuarioDTO usuarioDTO = usuarioService.findById(id);
			if ( usuarioDTO.getId() == null ) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(
					Response
					.builder()
					.message(Message.builder().code(COD_CONSULTA).message(MSG_CONSULTA).build())
					.data(usuarioDTO)
					.build()
					);
		} catch ( Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}	
	}
	
	@PostMapping
	public ResponseEntity<Response> save (@RequestBody UsuarioDTO reqUsuarioDTO) {
		try {
			UsuarioDTO usuarioDTO = usuarioService.save(reqUsuarioDTO);
			return ResponseEntity.ok(
					Response
					.builder()
					.message(Message.builder().code(COD_CONSULTA).message(MSG_CONSULTA).build())
					.data(usuarioDTO)
					.build()
					);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@DeleteMapping(API_USUARIO_ID)
	public ResponseEntity<Response> delete (@PathVariable(API_PATH_VAR_ID) long id) {
		try {
			UsuarioDTO usuarioDTO = usuarioService.deleteById(id);
			if ( usuarioDTO.getId() == null ) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(
					Response
					.builder()
					.message(Message.builder().code(COD_CONSULTA).message(MSG_CONSULTA).build())
					.data(usuarioDTO)
					.build()
					);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@PutMapping(API_USUARIO_ID)
	public ResponseEntity<Response> update (@PathVariable(API_PATH_VAR_ID)Long id, @RequestBody UsuarioDTO reqUsuarioDTO) {
		try {
			UsuarioDTO usuarioDTO = usuarioService.update(reqUsuarioDTO, id);
			if ( usuarioDTO.getId() == null) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(
					Response
					.builder()
					.message(Message.builder().code(COD_CONSULTA).message(MSG_CONSULTA).build())
					.data(usuarioDTO)
					.build()
					);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}
	}
}

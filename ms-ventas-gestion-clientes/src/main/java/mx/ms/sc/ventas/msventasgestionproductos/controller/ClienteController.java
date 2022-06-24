package mx.ms.sc.ventas.msventasgestionproductos.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import mx.ms.sc.ventas.msventasgestionproductos.dto.ClienteDTO;
import mx.ms.sc.ventas.msventasgestionproductos.service.ClienteService;

import static mx.ms.sc.ventas.msventasgestionproductos.commons.GobalConstants.API_CLIENTE;
import static mx.ms.sc.ventas.msventasgestionproductos.commons.GobalConstants.COD_CONSULTA;
import static mx.ms.sc.ventas.msventasgestionproductos.commons.GobalConstants.MSG_CONSULTA;
import static mx.ms.sc.ventas.msventasgestionproductos.commons.GobalConstants.MSG_REGISTRO;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(API_CLIENTE)
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	/*
	@GetMapping
	public ResponseEntity<?> findByLike(){	
		try {
			List <ProductoDTO> lProductoDTO = productoService.findByLike(null);
			if ( lProductoDTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.ok(lProductoDTO);
			}
		} catch (Exception e) {
			//log.error(e.getMessage(), e);
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
	}*/
	
	@GetMapping
	public ResponseEntity<Response> findByLike(){	
		try {
			List <ClienteDTO> lProductoDTO = clienteService.findByLike(null);
			if ( lProductoDTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.ok(
						Response
						.builder()
						.message(Message.builder().code(COD_CONSULTA).message(MSG_CONSULTA).build())
						.data(lProductoDTO)
						.build());
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<Response> save(@RequestBody ClienteDTO clienteDTO){	
		try {
			ClienteDTO rClienteDTO = clienteService.save(clienteDTO);
			if ( rClienteDTO == null) {
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.ok(
						Response
						.builder()
						.message(Message.builder().code(COD_CONSULTA).message(MSG_REGISTRO).build())
						.data(rClienteDTO)
						.build());
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}
	}
}

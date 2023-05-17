package ms.ecommerce.ventas.personas.controller;

import static ms.ecommerce.ventas.personas.commons.GlobalMessages.*;
import static ms.ecommerce.ventas.personas.commons.GobalConstants.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import ms.ecommerce.ventas.personas.controller.response.ResponseRequest;
import ms.ecommerce.ventas.personas.dto.RegistroPersonaDTO;
import ms.ecommerce.ventas.personas.models.Response;
import ms.ecommerce.ventas.personas.service.IPersonaService;

@Slf4j
@RestController
@RequestMapping(API_PERSON)
public class PersonaController {
	
	@Autowired
	private IPersonaService personaService;
	
	@PostMapping(API_PERSON_SAVE)
	public ResponseEntity<ResponseRequest> save(@RequestBody RegistroPersonaDTO registroDTO) {
		try {
			Response response = personaService.save(registroDTO);
			
			return ResponseEntity.ok(
					ResponseRequest
						.builder()
						.data(response.getRowsEntitites())
						.isCorrect(response.getIsCorrect())
						.isBreakOperation(response.getIsBreakOperation())
						.message(response.getMessage())
						.code( response.getIsCorrect().equals(IS_CORRECT_FALSE) ? 0 : 1)
						.build()
					);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}
	}

}

package ms.ecommerce.ventas.personas.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class RegistroPersonaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PersonaDTO persona;
	private UsuarioDTO usuario;
}

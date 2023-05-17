package ms.ecommerce.ventas.personas.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class RolDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long rolId;
	private Long usuarioId;
	
}

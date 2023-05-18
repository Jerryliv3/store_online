package ms.ecommerce.ventas.personas.api.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class RegistroRolDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<RolDTO> listRol;
}

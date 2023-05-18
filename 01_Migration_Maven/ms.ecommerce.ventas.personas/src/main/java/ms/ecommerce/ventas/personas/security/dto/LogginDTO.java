package ms.ecommerce.ventas.personas.security.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class LogginDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String user;
	private String password;
}

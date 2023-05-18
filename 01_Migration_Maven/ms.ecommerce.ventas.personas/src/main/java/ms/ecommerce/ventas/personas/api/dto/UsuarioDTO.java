package ms.ecommerce.ventas.personas.api.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class UsuarioDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long usuarioId;
	private String loginUsuario;
	private String correo;
	private String password;
	private String fechaRegistro;
	private String fechaCaducidad;
	private int primeraVez;
	private int bloqueo;
	private int logueado;
	private Long personaId;

}
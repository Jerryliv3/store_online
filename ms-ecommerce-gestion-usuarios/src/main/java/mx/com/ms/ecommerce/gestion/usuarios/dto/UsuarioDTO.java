package mx.com.ms.ecommerce.gestion.usuarios.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class UsuarioDTO implements Serializable{
		  
	private static final long serialVersionUID = 1L;

		private Long id;
	  	private String nombre;
	  	private String apellidoMaterno;
	  	private String apellidoPaterno;
	  	private String fechaNacimiento;
	  	private String direccion;
	  	private Long codigoPostal;
	  	private String telefono;
	  	private String email;
	  	private String usuario;
	  	private String claveAcceso;
	  	private Long idRol;

}

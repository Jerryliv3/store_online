package mx.com.ms.ecommerce.gestion.usuarios.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class UsuarioDTO implements Serializable{
		  
	private static final long serialVersionUID = 1L;

		private Long id;
	  	private String nombre;
	  	private String descripcion;
	  	private Double precio;
	  	private Double stock;
	  	private String estado;

}

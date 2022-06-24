package mx.ms.sc.ventas.msventasgestionproductos.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class ClienteDTO implements Serializable{
		  
	private static final long serialVersionUID = 1L;

		private Long id;
	  	private String razonSocial;
	  	private String rfc;
	  	private String direccion;
	  	private String telefono;
	  	private String correo;
	  	private String estado;
	  

}

package mx.ms.sc.ventas.msventasgestionproductos.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class ProductoDTO implements Serializable{
		  
	private static final long serialVersionUID = 1L;

		private Long id;
	  	private String nombre;
	  	private String descripcion;
	  	private Double precio;
	  	private Double stock;
	  	private String estado;

}

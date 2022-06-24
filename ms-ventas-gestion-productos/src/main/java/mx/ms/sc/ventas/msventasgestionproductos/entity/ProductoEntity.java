package mx.ms.sc.ventas.msventasgestionproductos.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "PRODUCTO")
public class ProductoEntity implements Serializable{
		  
	private static final long serialVersionUID = -2170897015344177815L;

		@Id
	  	@Column(name = "ID_PRODUCTO")
	  	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqProducto")
	    @SequenceGenerator(sequenceName = "SEQ_PRODUCTO", allocationSize = 1, name = "seqProducto")
		private Long id;
	  
	  	@NotNull(message = "Nombre del producto es requerido")
	  	@Size(min = 5, max = 120, message = "El nombre.....")
	  	@Column(name = "NOMBRE")
	  	private String nombre;
	  	
	  	@Column(name = "DESCRIPCION")
	  	private String descripcion;

	  	@Column(name = "PRECIO")
	  	private Double precio;

	  	@Column(name = "STOCK")
	  	private Double stock;

	  	@Column(name = "ESTADO")
	  	private String estado;

}

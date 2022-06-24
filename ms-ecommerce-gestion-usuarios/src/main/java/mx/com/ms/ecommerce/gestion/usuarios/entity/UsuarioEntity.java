package mx.com.ms.ecommerce.gestion.usuarios.entity;

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
@Table(name = "USUARIO")
public class UsuarioEntity implements Serializable{
		  
	private static final long serialVersionUID = -2170897015344177815L;  	
		@Id
	  	@Column(name = "ID_USUARIO")
	  	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqUsuario")
	    @SequenceGenerator(sequenceName = "SEQ_USUARIO", allocationSize = 1, name = "seqUsuario")
		private Long id;
	  
	  	@NotNull(message = "Nombre de usario es requerido...")
	  	//@Size(min = 5, max = 120, message = "El nombre.....")
	  	@Column(name = "NOMBRE")
	  	private String nombre;
	  	
	  	@Column(name = "APELLIDO_MATERNO")
	  	private String apellidoMaterno;
	  	
	  	@Column(name = "APELLIDO_PATERNO")
	  	private String apellidoPaterno;
	  	
	  	@Column(name = "FECHA_NACIMIENTO")
	  	private String fechaNacimiento;
	  	
	  	@Column(name = "DIRECCION")
	  	private String direccion;
	  	
	  	@Column(name = "CODIGO_POSTAL")
	  	private Long codigoPostal;
	  	
	  	@Column(name = "TELEFONO")
	  	private String telefono;
	  	
	  	@Column(name = "EMAIL")
	  	private String email;
	  	
	  	@Column(name = "USUARIO")
	  	private String usuario;
	  	
	  	@Column(name = "CLAVE_ACCESO")
	  	private String claveAcceso;
	  	
	  	@Column(name = "ID_ROL")
	  	private Long idRol;

}

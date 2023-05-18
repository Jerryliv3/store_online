package ms.ecommerce.ventas.personas.api.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@Entity
@XmlRootElement(name = "UsuarioEntity")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsuarioEntity implements Serializable {

	private static final long serialVersionUID = -2170897015344177815L;

	@Id
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

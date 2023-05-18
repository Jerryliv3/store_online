package ms.ecommerce.ventas.usuarios.security.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import ms.ecommerce.ventas.usuarios.security.dto.RolDTO;

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
	private List<RolDTO> listRoles;

}
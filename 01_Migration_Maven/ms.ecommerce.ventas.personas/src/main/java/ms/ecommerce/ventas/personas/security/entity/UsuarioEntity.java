package ms.ecommerce.ventas.personas.security.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import ms.ecommerce.ventas.personas.security.dto.RolDTO;

@Data
@XmlRootElement(name = "UsuarioEntity")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsuarioEntity implements Serializable {

	private static final long serialVersionUID = -2170897015344177815L;

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

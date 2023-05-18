package ms.ecommerce.ventas.personas.api.commons;

public class GobalConstants {
	
	/* APIS */
	public static final String API_USUARIO = "/v1/usuario";
	public static final String API_USUARIO_ID = "/{id}";
	public static final String API_USUARIO_PATH_VAR_ID = "id";
	
	public static final String API_PERSON = "/v1/persona";
	public static final String API_PERSON_SAVE = "/guardar";
	public static final String API_PERSON_ID = "/{id}";
	public static final String API_PERSON_PATH_VAR_ID = "id";
	
	public static final String API_SAVE_ROL = "/rol/guardar";
	public static final String API_GET_ROL = "/rol/obtener";

	
	/* Mensajes */
	
	public static final Integer COD_CONSULTA = 1;
	public static final String MSG_CONSULTA = "Success";
	
}

package ms.ecommerce.ventas.personas.commons;

import java.io.StringReader;
import java.util.List;
import java.lang.reflect.Type;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.XML;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;
import ms.ecommerce.ventas.personas.dto.PersonaDTO;
import ms.ecommerce.ventas.personas.models.Response;




@Slf4j
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String xml = "<Response><data><total>26</total><info><result xmlns:json=\"http://james.newtonking.com/projects/json\" json:Array=\"true\"><nombre>ULTIMO</nombre><segundoNombre>sd</segundoNombre><apellidoPaterno>fd</apellidoPaterno><apellidoMaterno>hgd</apellidoMaterno><rfc>fg</rfc><fechaNacimiento>2023-01-10</fechaNacimiento><fechaRegistro>2023-10-01</fechaRegistro><sexoId>1</sexoId><estadoCivilId>1</estadoCivilId><domicilioId>1</domicilioId><tipoPersonaId>1</tipoPersonaId><id>26</id></result></info></data><isCorrect>true</isCorrect><message>Operación realizada correctamente</message><isBreakOperation>false</isBreakOperation><totalRegistros>0</totalRegistros></Response>";
		Response response = new Response();
		response = (Response) xmlToObject(xml, response);
		log.info(response.toString());
		log.info(response.getData().getInfo().getResult());
		
		
		
		//final Gson gson = new Gson();
		//final Type tipoListaPersonas = new TypeToken <List<PersonaDTO>>(){}.getType();
		//final List<PersonaDTO> listPersona = gson.fromJson(response.getData().getInfo().getResult(), tipoListaPersonas);
		
		//public static <T> List<?> stringToClassList(String data, Class<T> convertType, ObjectMapper mapper)
		//List<?> objectList = ObjectNodeUtils.stringToClassList(arrayString, Object.class, objectMapper);
		
		/*
		List<PersonaDTO> listPersonaTest  = (List<PersonaDTO>) deserializeObjectJson(response.getData().getInfo().getResult(), new PersonaDTO(), new PersonaDTO().getClass());

		for (PersonaDTO persona : listPersonaTest) {
			log.info(persona.toString());
		}*/
	}
	


	public static <T> List<PersonaDTO> deserializeObjectJson (String objectJson, T t, Class<T> clazz) {
		
			Gson gson = new Gson();
			List<PersonaDTO> listPersonaDTO = gson.fromJson(objectJson, new TypeToken <List<PersonaDTO>>(){}.getType());
			
		return listPersonaDTO;
	}
	
	public static Response xmlToObject(String xml, Response response ) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(response.getClass());
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			response = (Response) unmarshaller.unmarshal(new StringReader(xml));
			JSONObject jsonObject = XML.toJSONObject(xml);   	
			try {
				response.getData().getInfo().setResult( jsonObject.getJSONObject("Response").getJSONObject("data").getJSONObject("info").getJSONArray("result").toString());
			} catch (Exception a) {
				try {
					response.getData().getInfo().setResult("[".concat(jsonObject.getJSONObject("Response").getJSONObject("data").getJSONObject("info").getJSONObject("result").toString()).concat("]"));
				} catch (Exception e) {
					response.getData().getInfo().setResult("[]");
				}	
			}
			 return response;
		} catch (JAXBException i) {
			throw new IllegalArgumentException("Error while converting xml to object", i);
		}
	}

	
	
	// Mappers convercion DTO - Entity,  Entity - DTO
		private Object getPersonaDTO(String e) {
		JsonMapper jsonMapper = new JsonMapper();
			return jsonMapper.convertValue(e, Object.class);
		}

}

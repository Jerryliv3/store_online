package ms.ecommerce.ventas.personas.security.helpers;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.XMLConstants;

import org.json.JSONObject;
import org.json.XML;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import ms.ecommerce.ventas.personas.security.models.Response;

public class Helper {

	public static String objectToXml(Object object, Class<?> clazz) {
		StringWriter sw = new StringWriter();
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			// jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI);
			jaxbMarshaller.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION,
					"http://www.w3.org/2001/XMLSchema-instance.xsd");
			jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "utf-16");
			jaxbMarshaller.marshal(object, sw);
		} catch (JAXBException e) {
			throw new IllegalArgumentException("Error while converting object to xml", e);
		}
		return sw.toString();
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

}

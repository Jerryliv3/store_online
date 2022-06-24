package mx.ms.sc.ventas.msventasgestionproductos.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;

@Builder // Patron diseño de software
@Data
public class Response {
	
	private Message message;
	
	//@JsonIgnore // Cuando no haya datos o sea nulo
	private Object data;
	

	
	
}

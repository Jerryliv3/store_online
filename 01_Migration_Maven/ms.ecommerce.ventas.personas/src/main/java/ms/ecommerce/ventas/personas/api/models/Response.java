package ms.ecommerce.ventas.personas.api.models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Response")
@XmlAccessorType(XmlAccessType.FIELD)
public class Response {
	private Data data;
	private String isCorrect;
	private String message;
	private String isBreakOperation;
	private int totalRegistros;
	private Object rowsEntitites;
	
	public Response() {
	}
	
	public Response(Data data, String isCorrect, String message, String isBreakOperation, int totalRegistros, Object rowsEntitites) {
		super();
		this.data = data;
		this.isCorrect = isCorrect;
		this.message = message;
		this.isBreakOperation = isBreakOperation;
		this.totalRegistros = totalRegistros;
		this.rowsEntitites = rowsEntitites;
	}
	
	public Object getRowsEntitites() {
		return rowsEntitites;
	}

	public void setRowsEntitites(Object rowsEntitites) {
		this.rowsEntitites = rowsEntitites;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public String getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(String isCorrect) {
		this.isCorrect = isCorrect;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getIsBreakOperation() {
		return isBreakOperation;
	}

	public void setIsBreakOperation(String isBreakOperation) {
		this.isBreakOperation = isBreakOperation;
	}

	public int getTotalRegistros() {
		return totalRegistros;
	}

	public void setTotalRegistros(int totalRegistros) {
		this.totalRegistros = totalRegistros;
	}

	@Override
	public String toString() {
		return "Response [data=" + data + ", isCorrect=" + isCorrect + ", message=" + message + ", isBreakOperation="
				+ isBreakOperation + ", totalRegistros=" + totalRegistros + "]";
	}
}

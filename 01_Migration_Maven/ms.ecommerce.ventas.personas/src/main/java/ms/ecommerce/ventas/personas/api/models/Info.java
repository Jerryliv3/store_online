package ms.ecommerce.ventas.personas.api.models;

public class Info {
	
	private String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Info(String result) {
		super();
		this.result = result;
	}

	public Info() {
		super();
	}

	@Override
	public String toString() {
		return "Info [result=" + result + "]";
	}
	
	
	
}

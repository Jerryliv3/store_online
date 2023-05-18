package ms.ecommerce.ventas.personas.security.models;

public class Data {
	
	private int total;
	private Info info;

	public Data() {
		super();
	}
	public Data(int total, Info info) {
		super();
		this.total = total;
		this.info = info;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public Info getInfo() {
		return info;
	}
	public void setInfo(Info info) {
		this.info = info;
	}
	@Override
	public String toString() {
		return "Data [total=" + total + ", info=" + info + "]";
	}
	
}


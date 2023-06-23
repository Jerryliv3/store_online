package ms.ecommerce.ventas.personas;

public class Test {

	static void main(String[] args) {
		// TODO Auto-generated method stub

		String s = "";
		StringBuffer sb1 = new StringBuffer("hi");
		StringBuffer sb2 = new StringBuffer("hi");
		StringBuffer sb3 = new StringBuffer(sb2);
		StringBuffer sb4 = sb3;
		
		
		if (sb1.equals(sb2)) {
			s+= "1 "; 
		}
		if (sb2.equals(sb3)) {
			s+= "2 "; 
		}
		if (sb3.equals(sb4)) {
			s+= "3 "; 
		}
		
		System.out.print("Response: " + s);
		
	}

}

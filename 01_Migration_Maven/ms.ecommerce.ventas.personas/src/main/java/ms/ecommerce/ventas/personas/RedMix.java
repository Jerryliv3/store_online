package ms.ecommerce.ventas.personas;

public class RedMix extends Concrete{
	RedMix () {
		System.out.print("r ");
	}
	
	public static void main(String[] args) {
		new RedMix();
	}
	
	class Cell {
		
	}
}


class Concrete extends Sand {
	Concrete () {
		System.out.print("c ");
	}
	private Concrete(String s) {
		
	}
}

abstract class Sand {
	Sand() {
		System.out.print("s ");
	}
}
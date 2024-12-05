package factory;

import java.io.FileNotFoundException;

public class ConcreteCsvCreator extends Creator{

	@Override
	public Product factoryMethod() throws FileNotFoundException {
		// TODO Auto-generated method stub
		return new ConcreteCsvProduct();
	}

	
}

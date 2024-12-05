package factory;

import java.io.FileNotFoundException;

public class ConcreteTxtCreator extends Creator{

	@Override
	public Product factoryMethod() throws FileNotFoundException {
		// TODO Auto-generated method stub
		return new ConcreteTxtProduct();
	}

}

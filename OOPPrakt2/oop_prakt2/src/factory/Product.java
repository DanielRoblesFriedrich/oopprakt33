package factory;

import java.io.IOException;

import business.Cafe;

public abstract class Product {

	public abstract String[] leseAusDatei() throws IOException;
	
	public abstract void schliesseDatei() throws IOException;
}

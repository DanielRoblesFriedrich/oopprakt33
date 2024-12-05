package factory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import business.Cafe;

public class ConcreteCsvProduct extends Product {

	private BufferedReader ein;

	public ConcreteCsvProduct() throws FileNotFoundException {
		this.ein = new BufferedReader(new FileReader("Cafe.csv"));;
	}
	
	@Override
	public String[] leseAusDatei() throws IOException {
		
		String[] zeile = ein.readLine().split(";");
		return zeile;
	}

	@Override
	public void schliesseDatei() throws IOException {
		// TODO Auto-generated method stub
		
		ein.close();
		
	}

}

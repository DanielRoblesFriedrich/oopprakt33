package factory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import business.Cafe;

	public class ConcreteTxtProduct extends Product {
		private BufferedReader ein;

		public ConcreteTxtProduct() throws FileNotFoundException {
			this.ein = new BufferedReader(new FileReader("Cafe.txt"));;
		}
	@Override
	public String[] leseAusDatei() throws IOException {
		String [] ergebnisZeile = new String[5];
		String zeile = ein.readLine();
		int i = 0;
		while (i < ergebnisZeile.length){
			ergebnisZeile[i] = zeile;
			zeile = ein.readLine();
			i++;
	}
		return ergebnisZeile;
}

	@Override
	public void schliesseDatei() throws IOException {
		// TODO Auto-generated method stub
		ein.close();
		
	}

}

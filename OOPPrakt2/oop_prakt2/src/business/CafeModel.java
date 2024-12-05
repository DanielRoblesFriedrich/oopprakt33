package business;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import factory.Creator;
import factory.Product;
import ownUtil.Observable;
import ownUtil.Observer;
import factory.ConcreteCsvCreator;
import factory.ConcreteTxtCreator;


public class CafeModel implements Observable{

	private Cafe cafe;
	
	//Singleton:
	private static CafeModel cafeModel;
	
	
	
	
	private Vector<Observer> observers = new Vector<Observer>();
	public static CafeModel getInstance() {
		
		if(cafeModel == null) {
			cafeModel = new CafeModel();
		}
		return cafeModel;
	}
	
	public void schreibeCafeInCsvDatei() throws IOException{
		
		
		if (this.cafe == null) {
	        throw new IllegalStateException("Cafe nicht verfügbar");
	    }
			BufferedWriter aus = new BufferedWriter(new FileWriter("Cafe.csv", true));
			aus.write(this.getCafe().gibCafeZuruck(';'));
			aus.close();
		
	}
	public void leseCafeAusCsvDatei() throws IOException {
		
		Creator  readerCreator = new ConcreteCsvCreator();
		Product reader = readerCreator.factoryMethod();
		
        String[] zeile = reader.leseAusDatei();
        
        this.setCafe(new Cafe(zeile[0], 
            zeile[1], 
            zeile[2], 
            zeile[3], 
            zeile[4].split("_"))); 
        reader.schliesseDatei();
	}
	
	public void leseCafeAusTxtDatei() throws IOException {
			
			Creator  readerCreator = new ConcreteTxtCreator();
			Product reader = readerCreator.factoryMethod();
			
	        String[] zeile = reader.leseAusDatei();
	        
	        this.setCafe(new Cafe(zeile[0], 
	            zeile[1], 
	            zeile[2], 
	            zeile[3], 
	            zeile[4].split("_"))); 
	        reader.schliesseDatei();
		}

	public Cafe getCafe() {
		return cafe;
	}
	

	public void setCafe(Cafe cafe) {
		this.cafe = cafe;
		notifyObservers();
	}
	private CafeModel() {
		
	}

	//Observable:

	@Override
	public void addObserver(Observer obs) {
		
		observers.addElement(obs);
		
	}

	@Override
	public void removeObserver(Observer obs) {

		observers.removeElement(obs);
		
	}

	@Override
	public void notifyObservers() {

		for(Observer obs: observers) {
			obs.update();
		}
		
	}

	

	
	
}

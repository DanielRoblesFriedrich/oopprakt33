package guiCafe;

import java.io.IOException;
import business.*;
import javafx.stage.Stage;

public class CafeControl{
	
	private CafeModel cafeModel;
	private CafeView cafeView;
	
	public  CafeControl(Stage primaryStage) throws Exception {
		
		//Singleton:
		this.cafeModel = CafeModel.getInstance();
		this.cafeView = new CafeView(primaryStage, this, cafeModel);
		
		
		
	}

	
	public void schreibeCafeInDatei(String typ) {
		try {
			if("csv".equals(typ)) {
				this.cafeModel.schreibeCafeInCsvDatei();
				this.cafeView.zeigeInformationsfensterAn("Cafe wurde gespeichert");
			}
			
			else {
				this.cafeView.zeigeInformationsfensterAn("Noch nicht implementiert");
			}
		} 
		catch (IOException ioe) {
			cafeView.zeigeFehlermeldungsfensterAn("IOException");
			ioe.printStackTrace();
		} catch(Exception exc) {
			cafeView.zeigeFehlermeldungsfensterAn("Unbekannter Fehler beim Speichern!");
			exc.printStackTrace();
		} 
	}
	
	public void leseAusDatei(String typ) {
        try {
            if ("csv".equals(typ)) {
            	this.cafeModel.leseCafeAusCsvDatei();
                this.cafeView.zeigeInformationsfensterAn("Cafe wurde gelesen!");
            }
            else if("txt".equals(typ)) {
            	this.cafeModel.leseCafeAusTxtDatei();
                this.cafeView.zeigeInformationsfensterAn("Cafe wurde gelesen!");
            }
            else {
                this.cafeView.zeigeInformationsfensterAn("Noch nicht implementiert!");
            }
        } catch (IOException exc) {
            this.cafeView.zeigeFehlermeldungsfensterAn("IOException beim Lesen!");
        } catch (Exception exc) {
            this.cafeView.zeigeFehlermeldungsfensterAn("Unbekannter Fehler beim Lesen!");
            exc.printStackTrace();
        }
    }
	
}

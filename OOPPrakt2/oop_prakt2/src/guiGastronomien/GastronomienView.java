package guiGastronomien;

import business.CafeModel;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ownUtil.*;

public class GastronomienView implements Observer{
	
private GastronomienControl
 	gastronomienControl;
private CafeModel cafesModel;		
    	//---Anfang Attribute der grafischen Oberflaeche---
    	private Pane pane = new  Pane();
    	private Label lblAnzeigeCafes     
 		= new Label("Anzeige Cafes");
    	private TextArea txtAnzeigeCafes  = new TextArea();
    	private Button btnAnzeigeCafes = new Button("Anzeige");
    	//-------Ende Attribute der grafischen Oberflaeche-------
    
    	public GastronomienView(
 		GastronomienControl 
 		GastronomienControl, 
   	 	Stage primaryStage, 
 		CafeModel cafesModel){
    		Scene scene = new Scene(this.pane, 560, 340);
    		primaryStage.setScene(scene);
    		primaryStage.setTitle("Anzeige von Gastronomien");
    		primaryStage.show();
    		this.gastronomienControl 
 			= gastronomienControl;
 		this.cafesModel = cafesModel;
 		this.initKomponenten();
		this.initListener();
		
		//Observer:
		cafesModel.addObserver(this);
    	}

 	private void initKomponenten(){
    		// Label
 		Font font = new Font("Arial", 20);
       	lblAnzeigeCafes.setLayoutX(310);
    		lblAnzeigeCafes.setLayoutY(40);
    		lblAnzeigeCafes.setFont(font);
    		lblAnzeigeCafes.setStyle("-fx-font-weight: bold;"); 
       	pane.getChildren().add(lblAnzeigeCafes);           
// Textbereich	
        	txtAnzeigeCafes.setEditable(false);
     		txtAnzeigeCafes.setLayoutX(310);
    		txtAnzeigeCafes.setLayoutY(90);
     		txtAnzeigeCafes.setPrefWidth(220);
    		txtAnzeigeCafes.setPrefHeight(185);
       	pane.getChildren().add(txtAnzeigeCafes);        	
        	// Button
          	btnAnzeigeCafes.setLayoutX(310);
        	btnAnzeigeCafes.setLayoutY(290);
        	pane.getChildren().add(btnAnzeigeCafes); 
   }
   
   private void initListener() {
	    btnAnzeigeCafes.setOnAction(
 			new EventHandler<ActionEvent>() {
	    		@Override
	        	public void handle(ActionEvent e) {
	            	zeigeCafesAn();
	        	} 
   	    });
    }
   
    public void zeigeCafesAn(){
    		if(cafesModel.getCafe() != null){
    			txtAnzeigeCafes.setText(
    				cafesModel.getCafe()
 				.gibCafeZuruck(' '));
    		}
    		else{
    			zeigeInformationsfensterAn(
 				"Bisher wurde kein Cafe aufgenommen!");
    		}
    }	
   
    public void zeigeInformationsfensterAn(String meldung){
    	  	new MeldungsfensterAnzeiger(AlertType.INFORMATION,
               	"Information", meldung).zeigeMeldungsfensterAn();
    }

	@Override
	public void update() {
		this.zeigeCafesAn();
		
	}	
    
}


package guiCafe;

import business.CafeModel;
import business.Cafe;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ownUtil.MeldungsfensterAnzeiger;
import ownUtil.Observer;

public class CafeView implements Observer{
	 //---Anfang Attribute der grafischen Oberflaeche---
    private Pane pane     					= new  Pane();
    private Label lblEingabe    	 		= new Label("Eingabe");
    private Label lblAnzeige   	 	    	= new Label("Anzeige");
    private Label lblName 					= new Label("Name:");
    private Label lblOrt   		= new Label("Ort:");
    private Label lblBeschreibung  	 		= new Label("Beschreibung:");
    private Label lblBaeckerei   			= new Label("Bäckerei:");
    private Label lblKaffeeProdukt  		= new Label("Kaffee Produkt:");
    private TextField txtName 	 			= new TextField();
    private TextField txtOrt		= new TextField();
    private TextField txtBeschreibung		= new TextField();
    private TextField txtBaeckerei			= new TextField();
    private TextField txtKaffeeProdukt	= new TextField();
    private TextArea txtAnzeige  			= new TextArea();
    private Button btnEingabe 		 		= new Button("Eingabe");
    private Button btnAnzeige 		 		= new Button("Anzeige");
    private MenuBar mnbrMenuLeiste  		= new MenuBar();
    private Menu mnDatei             		= new Menu("Datei");
    private MenuItem mnItmCsvImport 		= new MenuItem("csv-Import");
    private MenuItem mnItmCsvExport 		= new MenuItem("csv-Export");
    private MenuItem mnItmTxtImport 		= new MenuItem("txt-Import");
    //-------Ende Attribute der grafischen Oberflaeche-------
    
    // speichert temporaer ein Objekt vom Typ Buergeramt
	private CafeControl cafeControl;
	private CafeModel cafeModel;
    
    private void initKomponenten(){
       	// Labels
    	lblEingabe.setLayoutX(20);
    	lblEingabe.setLayoutY(40);
    	Font font = new Font("Arial", 24); 
    	lblEingabe.setFont(font);
    	lblEingabe.setStyle("-fx-font-weight: bold;"); 
    	lblAnzeige.setLayoutX(400);
    	lblAnzeige.setLayoutY(40);
      	lblAnzeige.setFont(font);
       	lblAnzeige.setStyle("-fx-font-weight: bold;"); 
       	lblName.setLayoutX(20);
    	lblName.setLayoutY(90);
    	lblOrt.setLayoutX(20);
    	lblOrt.setLayoutY(130);
    	lblBeschreibung.setLayoutX(20);
    	lblBeschreibung.setLayoutY(170);
    	lblBaeckerei.setLayoutX(20);
    	lblBaeckerei.setLayoutY(210);
    	lblKaffeeProdukt.setLayoutX(20);
    	lblKaffeeProdukt.setLayoutY(250);    	
       	pane.getChildren().addAll(lblEingabe, lblAnzeige, 
       		lblName, lblOrt, lblBeschreibung,
       		lblBaeckerei, lblKaffeeProdukt);
    
    	// Textfelder
     	txtName.setLayoutX(170);
    	txtName.setLayoutY(90);
    	txtName.setPrefWidth(200);
    	txtOrt.setLayoutX(170);
    	txtOrt.setLayoutY(130);
    	txtOrt.setPrefWidth(200);
    	txtBeschreibung.setLayoutX(170);
    	txtBeschreibung.setLayoutY(170);
    	txtBeschreibung.setPrefWidth(200);
      	txtBaeckerei.setLayoutX(170);
    	txtBaeckerei.setLayoutY(210);
    	txtBaeckerei.setPrefWidth(200);
    	txtKaffeeProdukt.setLayoutX(170);
    	txtKaffeeProdukt.setLayoutY(250);
    	txtKaffeeProdukt.setPrefWidth(200);
      	pane.getChildren().addAll( 
     		txtName, txtOrt, txtBeschreibung,
     		txtBaeckerei, txtKaffeeProdukt);
     	
        // Textbereich	
        txtAnzeige.setEditable(false);
     	txtAnzeige.setLayoutX(400);
    	txtAnzeige.setLayoutY(90);
     	txtAnzeige.setPrefWidth(270);
    	txtAnzeige.setPrefHeight(185);
       	pane.getChildren().add(txtAnzeige); 
       	
        // Buttons
        btnEingabe.setLayoutX(20);
        btnEingabe.setLayoutY(290);
        btnAnzeige.setLayoutX(400);
        btnAnzeige.setLayoutY(290);
        pane.getChildren().addAll(btnEingabe, btnAnzeige); 
        
 		// Menue
   	    this.mnbrMenuLeiste.getMenus().add(mnDatei);
   	 this.mnDatei.getItems().add(mnItmCsvImport);
  	    this.mnDatei.getItems().add(mnItmCsvExport);
  	    this.mnDatei.getItems().add(mnItmTxtImport);
 	    pane.getChildren().add(mnbrMenuLeiste);
   }
   
   private void initListener() {
	   
	   mnItmCsvExport.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent e) {
        	   schreibeCafeInDatei("csv");
           }
	   });
	   
	   mnItmTxtImport.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent e) {
        	   leseCafeAusDatei("txt");
           }
	   });
	   
	   mnItmCsvImport.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
		    	leseCafeAusDatei("csv");
		    }
   	});
	   
	   
	   btnEingabe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
        	    schreibeCafe();
        	    
            }
	    });
	   btnAnzeige.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	        public void handle(ActionEvent e) {
	           zeigeCafesAn();
	        } 
   	    });  
    }
   	
   public void zeigeInformationsfensterAn(String meldung){
   	new MeldungsfensterAnzeiger(AlertType.INFORMATION,
   		"Information", meldung).zeigeMeldungsfensterAn();
   }	
	
	public void zeigeFehlermeldungsfensterAn(String meldung){
      	new MeldungsfensterAnzeiger(AlertType.ERROR,
       	"Fehler", meldung).zeigeMeldungsfensterAn();
   }
	
	private void leseCafeAusDatei(String typ) {
		this.cafeControl.leseAusDatei(typ);
	} 
	private void schreibeCafeInDatei(String typ) {
		this.cafeControl.schreibeCafeInDatei(typ);
	} 
    
    private void schreibeCafe(){
    	try{
    		this.cafeModel.setCafe(new Cafe (
    			txtName.getText(), 
   	            txtOrt.getText(),
   	            txtBeschreibung.getText(),
    		    txtBaeckerei.getText(),
    		    txtKaffeeProdukt.getText().split(";")));
    		zeigeInformationsfensterAn("Cafe wurde aufgenommen!");
       	}
       	catch(Exception exc){
       		zeigeFehlermeldungsfensterAn(exc.getMessage());
     	}
    }
   
    public void update() {
    	this.zeigeCafesAn();
    }
    
    private void zeigeCafesAn(){
    	if(cafeModel.getCafe() != null){
    		txtAnzeige.setText(
    				cafeModel.getCafe().gibCafeZuruck(' '));
    	}else{
    		zeigeInformationsfensterAn("Bisher wurde kein Cafe aufgenommen!");
    	}
    }	
    
    
    public CafeView(Stage primaryStage, CafeControl cafeControl,CafeModel cafeModel){
    	this.cafeControl = cafeControl;
		this.cafeModel = cafeModel;
		Scene scene = new Scene(this.pane, 700, 340);
    	primaryStage.setScene(scene);
    	primaryStage.setTitle("Verwaltung von Cafes");
    	primaryStage.show();
    	this.initKomponenten();
		this.initListener();
    }

		
}

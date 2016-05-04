package it.polito.tdp.metrodeparis;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.model.Fermata;
import it.polito.tdp.model.MetroModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class MetroDeParisController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> boxPartenza;

    @FXML
    private ComboBox<String> boxArrivo;

    @FXML
    private Button btnCalcola;

    @FXML
    private TextArea txtResult;
	
    private MetroModel model;
    
    public void setModel(MetroModel model) {
			this.model = model;			
		}
		
    @FXML
    void doCalcola(ActionEvent event) {

    	txtResult.clear();
    	
    	String s1 = boxPartenza.getValue();
    	String s2 = boxArrivo.getValue();
    	
    	if(s1 == null || s2 == null || s1.compareTo("") == 0 || s2.compareTo("") == 0)
    		txtResult.setText("SCEGLIERE stazione di partenza e stazione di arrivo...");
    	else if(s1.compareTo(s2) == 0){
    		txtResult.setText("Errore: stazione partenza coincide con stazione arrivo!");
    	} else{
    		model.generaGrafo();
	    	List<Fermata> cammino = model.getCammino(s1, s2);
	    	txtResult.appendText("Percorso:\n");
	    	
	    	for(Fermata temp: cammino)
	    		txtResult.appendText("" + temp.getNome() + "\n");
	    	
	    	txtResult.appendText("\n\nTempo di percorrenza stimato: " );
    	}
    }

    @FXML
    void initialize() {
        assert boxPartenza != null : "fx:id=\"boxPartenza\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert boxArrivo != null : "fx:id=\"boxArrivo\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert btnCalcola != null : "fx:id=\"btnCalcola\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'MetroDeParis.fxml'.";

        model = new MetroModel();
        
        boxPartenza.getItems().add("");
        boxPartenza.getItems().addAll(model.elencoNomeFermate());
        boxArrivo.getItems().add("");
        boxArrivo.getItems().addAll(model.elencoNomeFermate());
    }

	
}

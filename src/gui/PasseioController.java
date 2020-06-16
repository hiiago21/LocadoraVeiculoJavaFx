package gui;

import java.net.URL;
import java.util.ResourceBundle;

import hiago.silva.entities.Passeio;
import hiago.silva.entities.Veiculo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class PasseioController implements Initializable{

	@FXML
	private TextField txtFielPlaca;
	
	@FXML
	private TextField txtFielAno;
	
	@FXML
	private TextField txtFielMarca;
	
	@FXML
	private TextField txtFielModelo;
	
	@FXML
	private TextField txtFielKm;
	
	@FXML
	private TextField txtFielPortas;
	
	@FXML
	private TextField txtFielPotencia;
	
	
	@FXML
	private ComboBox<Boolean> estilo;
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}
	
	public void upadateTable(Veiculo passeio, Boolean enable) { 
		txtFielPlaca.setText(passeio.getPlaca());
		txtFielMarca.setText(passeio.getMarca());
		txtFielModelo.setText(passeio.getModelo());
		txtFielAno.setText(String.valueOf(passeio.getAno()));
		txtFielPotencia.setText(String.valueOf((((Passeio) passeio).getPotencia())));
		txtFielKm.setText(String.valueOf(passeio.getKmRodado()));
		txtFielPortas.setText(String.valueOf(((Passeio)passeio).getNroPortas()));
		estilo.setValue(((Passeio)passeio).isLuxo());
		
		if(enable) {
			txtFielPlaca.setEditable(false);
			txtFielMarca.setEditable(false);
			txtFielModelo.setEditable(false);
			txtFielAno.setEditable(false);
			txtFielPotencia.setEditable(false);
			txtFielKm.setEditable(false);
			txtFielPortas.setEditable(false);
			estilo.setEditable(false);
		}
	}
}

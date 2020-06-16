package gui;

import java.net.URL;
import java.util.ResourceBundle;

import hiago.silva.entities.Moto;
import hiago.silva.entities.Veiculo;
import hiago.silva.entities.enums.Estilo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class MotoController implements Initializable{

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
	private TextField txtFielPotencia;
	
	@FXML
	private ComboBox<Estilo> estilo;
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}
	
	public void upadateTable(Veiculo moto, Boolean enable) { 
		txtFielPlaca.setText(moto.getPlaca());
		txtFielMarca.setText(moto.getMarca());
		txtFielModelo.setText(moto.getModelo());
		txtFielAno.setText(String.valueOf(moto.getAno()));
		txtFielPotencia.setText(String.valueOf((((Moto) moto).getPotencia())));
		txtFielKm.setText(String.valueOf(moto.getKmRodado()));
		estilo.setValue(((Moto)moto).getEstiloMoto());
		
		if(enable) {
			txtFielPlaca.setEditable(false);
			txtFielMarca.setEditable(false);
			txtFielModelo.setEditable(false);
			txtFielAno.setEditable(false);
			txtFielPotencia.setEditable(false);
			txtFielKm.setEditable(false);
			estilo.setEditable(false);
		}
	}
}

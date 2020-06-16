package gui;

import java.net.URL;
import java.util.ResourceBundle;

import hiago.silva.entities.Caminhao;
import hiago.silva.entities.Veiculo;
import hiago.silva.entities.enums.TipoCaminhao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CaminhaoController implements Initializable{

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
	private TextField txtFielCarga;
	
	@FXML
	private TextField txtFielEixos;
	
	
	@FXML
	private ComboBox<TipoCaminhao> estilo;
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}
	
	public void upadateTable(Veiculo caminhao, Boolean enable) { 
		txtFielPlaca.setText(caminhao.getPlaca());
		txtFielMarca.setText(caminhao.getMarca());
		txtFielModelo.setText(caminhao.getModelo());
		txtFielAno.setText(String.valueOf(caminhao.getAno()));
		txtFielCarga.setText(String.valueOf((((Caminhao) caminhao).getCargaMaxima())));
		txtFielKm.setText(String.valueOf(caminhao.getKmRodado()));
		txtFielEixos.setText(String.valueOf(((Caminhao)caminhao).getNrEixos()));
		estilo.setValue(((Caminhao)caminhao).getTipo());
		
		if(enable) {
			txtFielPlaca.setEditable(false);
			txtFielMarca.setEditable(false);
			txtFielModelo.setEditable(false);
			txtFielAno.setEditable(false);
			txtFielCarga.setEditable(false);
			txtFielKm.setEditable(false);
			txtFielEixos.setEditable(false);
			estilo.setEditable(false);
		}
	}
}

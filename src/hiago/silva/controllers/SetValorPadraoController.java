package hiago.silva.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import hiago.silva.model.entities.Veiculo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SetValorPadraoController implements Initializable {

	Veiculo veiculo;

	@FXML
	TextField txtFielVlrAtual;
	
	@FXML
	TextField txtFielNovoValor;

	@FXML
	Button btAlterar;

	@FXML
	private void btAlterarsAction() {
		if(!txtFielNovoValor.getText().equals("")) {
			Veiculo.valorPadrao = Double.parseDouble(txtFielNovoValor.getText());
		}
		else {
			
		}
	}
	

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}


	private void initializeNodes() {
		txtFielVlrAtual.setText(String.valueOf(Veiculo.valorPadrao));
		
	}
	

}

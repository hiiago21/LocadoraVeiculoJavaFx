package hiago.silva.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import hiago.silva.app.Main;
import hiago.silva.model.entities.Locadora;
import hiago.silva.model.entities.Veiculo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SetValorPadraoController implements Initializable {
	Locadora locadora = new Locadora();
	MainViewControler mv = new MainViewControler();
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
			locadora.importar("C:\\Users\\locadora.txt");
			mv.loadView("/gui/SetValorPadraoView.fxml", x -> {
			});
			
		}
		else {
			
		}
	}
	

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Stage stage = (Stage) Main.getMainScene().getWindow();
		stage.setHeight(600);
		stage.setWidth(620);
		initializeNodes();
	}


	private void initializeNodes() {
		txtFielVlrAtual.setText(String.valueOf(Veiculo.valorPadrao));
		
	}
	

}

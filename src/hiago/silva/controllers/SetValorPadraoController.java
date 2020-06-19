package hiago.silva.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import hiago.silva.app.MainLocadora;
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
		try {
			if(!txtFielNovoValor.getText().equals("")) {
				Veiculo.valorPadrao = Double.parseDouble(txtFielNovoValor.getText());
				locadora.importar("C:\\Users\\locadora.txt");
				mv.loadView("/gui/SetValorPadraoView.fxml", x -> {
				});
				
			}
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Dados Incorretos", "Falha!", 0, null);
		}
	}
	

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Stage stage = (Stage) MainLocadora.getMainScene().getWindow();
		stage.setHeight(600);
		stage.setWidth(620);
		initializeNodes();
	}


	private void initializeNodes() {
		txtFielVlrAtual.setText(String.valueOf(Veiculo.valorPadrao));
		
	}
	

}

package hiago.silva.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import hiago.silva.model.entities.Locadora;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class ComprarController implements Initializable{
	private Locadora locadora = new Locadora();
	MainViewControler mv = new MainViewControler();
	
	@FXML
	private ComboBox<String> tipoVeiculo;
	
	@FXML
	private Button btComprar;
	
	private ObservableList<String> obsList;
	
	public void onBtComprarAction() {
		String result = tipoVeiculo.getValue();
		
		if(result.equals("Moto")) {
			mv.loadView("/gui/MotoView.fxml", (MotoController controller) -> {
				controller.upadateTable(null, false, false);
			});
		}else if(result.equals("Passeio")) {
			mv.loadView("/gui/PasseioView.fxml", (PasseioController controller) -> {
				controller.upadateTable(null, false, false);
			});
		}else if(result.equals("Caminh�o")) {
			mv.loadView("/gui/CaminhaoView.fxml", (CaminhaoController controller) -> {
				controller.upadateTable(null, false, false);
			});
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {		
		upadateTable();
	}

	public void upadateTable() { 
		locadora.importar("C:\\Users\\locadora.txt");
		obsList = FXCollections.observableArrayList("Moto", "Passeio", "Caminh�o");
		tipoVeiculo.setItems(obsList);
	}

}

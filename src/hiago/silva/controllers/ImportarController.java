package hiago.silva.controllers;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import hiago.silva.app.Main;
import hiago.silva.model.entities.Locadora;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ImportarController implements Initializable {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	Locadora locadora = new Locadora();
	MainViewControler mv = new MainViewControler();

	@FXML
	private TextField txtFielPath;
	
	@FXML
	private Label labelAviso;

	@FXML
	private Button btImportar;
	
	@FXML
	private Pane fundo1;
	@FXML
	private Pane fundo2;

	@FXML
	private void btImportarAction() {
		String path = "C:\\Users\\locadora.txt";
		
		locadora.importar(path);
		txtFielPath.setText(path);
		labelAviso.setText("Impotação Concluída");
	}
	

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		Stage stage = (Stage) Main.getMainScene().getWindow();
		stage.setHeight(600);
		stage.setWidth(620);
		fundo2.setPrefHeight(600);
		fundo2.setPrefWidth(600);
		labelAviso.setPadding(new Insets(0,0,0,30));
		fundo1.setLayoutX(200);
	}

}

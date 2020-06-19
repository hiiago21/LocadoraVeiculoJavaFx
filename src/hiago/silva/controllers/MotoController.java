package hiago.silva.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import hiago.silva.app.MainLocadora;
import hiago.silva.model.entities.Locadora;
import hiago.silva.model.entities.Moto;
import hiago.silva.model.entities.Veiculo;
import hiago.silva.model.entities.enums.Estilo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MotoController implements Initializable{
	
	Locadora locadora = new Locadora();
	MainViewControler mv = new MainViewControler();
	
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
	
	@FXML
	private Button btAction;
	
	@FXML
	private Pane fundo1;
	@FXML
	private Pane fundo2;
	
	private ObservableList<Estilo> obsList;
	
	@FXML
	public void onBtAction() {
		String placa = txtFielPlaca.getText();
		String ano = txtFielAno.getText();
		String marca = txtFielMarca.getText();
		String modelo = txtFielModelo.getText();
		String kmRodado = txtFielKm.getText();
		Estilo estiloMoto = estilo.getValue();
		String potencia = txtFielPotencia.getText();
		
		
		boolean result = false;
		try {
			Moto m = new Moto(Integer.parseInt(ano), Long.parseLong(kmRodado), marca, modelo, placa,
					estiloMoto, Integer.parseInt(potencia));
			result = locadora.compraVeiculo(m);
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Dados Incorretos", "Falha!", 0, null);
		}
		
		
		if(result) {
			locadora.exportar();
			JOptionPane.showMessageDialog(null, "Compra Efetuada", "Compra", JOptionPane.INFORMATION_MESSAGE);
			mv.loadView("/gui/MainView2.fxml", x -> {
			});
		} 
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {		
		Stage stage = (Stage) MainLocadora.getMainScene().getWindow();
		stage.setHeight(600);
		stage.setWidth(800);
		fundo2.setPrefHeight(600);
		fundo2.setPrefWidth(800);
		upadateTable();
	}

	public void upadateTable() { 
		locadora.importar("C:\\Users\\locadora.txt");
		obsList = FXCollections.observableArrayList(Estilo.values());
		estilo.setItems(obsList);
	}
	
	public void upadateTable(Veiculo moto, Boolean enable, Boolean action) { 
	
		if(action) {
			txtFielPlaca.setText(moto.getPlaca());
			txtFielMarca.setText(moto.getMarca());
			txtFielModelo.setText(moto.getModelo());
			txtFielAno.setText(String.valueOf(moto.getAno()));
			txtFielPotencia.setText(String.valueOf((((Moto) moto).getPotencia())));
			txtFielKm.setText(String.valueOf(moto.getKmRodado()));
			estilo.setValue(((Moto)moto).getEstiloMoto());
		}
		
		
		if(enable) {
			txtFielPlaca.setEditable(false);
			txtFielMarca.setEditable(false);
			txtFielModelo.setEditable(false);
			txtFielAno.setEditable(false);
			txtFielPotencia.setEditable(false);
			txtFielKm.setEditable(false);
			estilo.setEditable(false);
			btAction.setVisible(false);
		}
	}
}

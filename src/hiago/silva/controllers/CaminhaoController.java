package hiago.silva.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import hiago.silva.app.MainLocadora;
import hiago.silva.model.entities.Caminhao;
import hiago.silva.model.entities.Locadora;
import hiago.silva.model.entities.Veiculo;
import hiago.silva.model.entities.enums.TipoCaminhao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CaminhaoController implements Initializable {
	
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
	private TextField txtFielCarga;

	@FXML
	private TextField txtFielEixos;

	@FXML
	private ComboBox<TipoCaminhao> tipo;

	@FXML
	private Button btAction;
	
	@FXML
	private Pane fundo1;
	@FXML
	private Pane fundo2;
	
	private ObservableList<TipoCaminhao> list;

	@FXML
	public void onBtAction() {
		String placa = txtFielPlaca.getText();
		String ano = txtFielAno.getText();
		String marca = txtFielMarca.getText();
		String modelo = txtFielModelo.getText();
		String kmRodado = txtFielKm.getText();
		String nrEixos = txtFielEixos.getText();
		String cargaMaxima = txtFielCarga.getText();
		TipoCaminhao estilo = tipo.getValue();

		
		
		boolean result = false;
		
		try {
			Caminhao c = new Caminhao(Integer.parseInt(ano), Long.parseLong(kmRodado), marca, modelo, placa,
					Long.parseLong(cargaMaxima), Integer.parseInt(nrEixos),
					estilo);
			
			result = locadora.compraVeiculo(c);
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
		list = FXCollections.observableArrayList(TipoCaminhao.values());
		tipo.setItems(list);
	}

	public void upadateTable(Veiculo caminhao, Boolean enable, Boolean action) { 
		
		if(action) {
			txtFielPlaca.setText(caminhao.getPlaca());
			txtFielMarca.setText(caminhao.getMarca());
			txtFielModelo.setText(caminhao.getModelo());
			txtFielAno.setText(String.valueOf(caminhao.getAno()));
			txtFielCarga.setText(String.valueOf((((Caminhao) caminhao).getCargaMaxima())));
			txtFielKm.setText(String.valueOf(caminhao.getKmRodado()));
			txtFielEixos.setText(String.valueOf(((Caminhao)caminhao).getNrEixos()));
			tipo.setValue(((Caminhao)caminhao).getTipo());
		}else {
			
		}
		
		if(enable) {
			txtFielPlaca.setEditable(false);
			txtFielMarca.setEditable(false);
			txtFielModelo.setEditable(false);
			txtFielAno.setEditable(false);
			txtFielCarga.setEditable(false);
			txtFielKm.setEditable(false);
			txtFielEixos.setEditable(false);
			tipo.setEditable(false);
			btAction.setVisible(false);
		}
	}
}

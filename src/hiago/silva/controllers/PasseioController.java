package hiago.silva.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import hiago.silva.model.entities.Locadora;
import hiago.silva.model.entities.Passeio;
import hiago.silva.model.entities.Veiculo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class PasseioController implements Initializable{
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
	private TextField txtFielPortas;
	
	@FXML
	private TextField txtFielPotencia;
	
	@FXML
	private Button btAction;
	
	
	@FXML
	private ComboBox<Boolean> estilo;
	
	private ObservableList<Boolean> obsList;
	
	@FXML
	public void onBtAction() {
		String placa = txtFielPlaca.getText();
		String ano = txtFielAno.getText();
		String marca = txtFielMarca.getText();
		String modelo = txtFielModelo.getText();
		String kmRodado = txtFielKm.getText();
		String nroPortas = txtFielPortas.getText();
		String potencia = txtFielPotencia.getText();
		Boolean luxo = estilo.getValue();

		Passeio p = new Passeio(Integer.parseInt(ano), Long.parseLong(kmRodado), marca, modelo, placa,
				luxo, Integer.parseInt(nroPortas), Integer.parseInt(potencia));
		
		boolean result = locadora.compraVeiculo(p);
		
		if(result) {
			locadora.exportar();
		}{
			JOptionPane.showMessageDialog(null, "Placa incorreta ou não consta no sistema", "Error!", 0, null);
		}
		mv.loadView("/gui/MainView.fxml", x -> {
		});
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {		
		upadateTable();
	}

	public void upadateTable() { 
		locadora.importar("C:\\Users\\locadora.txt");
		obsList = FXCollections.observableArrayList(true, false);
		estilo.setItems(obsList);
	}
	
	public void upadateTable(Veiculo passeio, Boolean enable, Boolean action) { 
		
		if(action) {
			txtFielPlaca.setText(passeio.getPlaca());
			txtFielMarca.setText(passeio.getMarca());
			txtFielModelo.setText(passeio.getModelo());
			txtFielAno.setText(String.valueOf(passeio.getAno()));
			txtFielPotencia.setText(String.valueOf((((Passeio) passeio).getPotencia())));
			txtFielKm.setText(String.valueOf(passeio.getKmRodado()));
			txtFielPortas.setText(String.valueOf(((Passeio)passeio).getNroPortas()));
			estilo.setValue(((Passeio)passeio).isLuxo());
		}
		
		if(enable) {
			txtFielPlaca.setEditable(false);
			txtFielMarca.setEditable(false);
			txtFielModelo.setEditable(false);
			txtFielAno.setEditable(false);
			txtFielPotencia.setEditable(false);
			txtFielKm.setEditable(false);
			txtFielPortas.setEditable(false);
			estilo.setEditable(false);
			btAction.setVisible(false);
		}
	}
}

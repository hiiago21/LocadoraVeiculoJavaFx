package hiago.silva.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

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
		
		Moto m = new Moto(Integer.parseInt(ano), Long.parseLong(kmRodado), marca, modelo, placa,
				estiloMoto, Integer.parseInt(potencia));
		
		boolean result = locadora.compraVeiculo(m);
		
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

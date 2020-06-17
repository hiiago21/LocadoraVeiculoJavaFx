package hiago.silva.controllers;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import hiago.silva.app.Main;
import hiago.silva.model.entities.Locadora;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SimularDevolucaoController implements Initializable {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	Locadora locadora = new Locadora();
	MainViewControler mv = new MainViewControler();

	@FXML
	private DatePicker dateInicial;
	
	@FXML
	private DatePicker dateFim;
	
	@FXML
	private TextField txtFielPlaca;
	
	@FXML
	private TextField txtFielValorTotal;

	@FXML
	private Button btSimular;

	@FXML
	private void btConsultarAction() {
		try{
			locadora.importar("C:\\Users\\locadora.txt");
			String pattern = "dd/MM/yyyy";
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
	
			String dateInicia = dateFormatter.format(dateInicial.getValue());
			String dateFinal = dateFormatter.format(dateFim.getValue());
			

			long ini = sdf.parse(dateInicia).getTime();
			long fim =  sdf.parse(dateFinal).getTime();
			
			int dias  = (int) Math.ceil((fim - ini)/1000/60/60/24);
			
			
			Double valorTotal = locadora.simularValorDevolucao( txtFielPlaca.getText().toUpperCase(), dias);
			
			if(valorTotal.equals(0.0)) {
				JOptionPane.showMessageDialog(null, "Dados Incorretos", "Error!", 0, null);
			}else {
				txtFielValorTotal.setText(valorTotal.toString());
			}
			
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Dados Incorretos", "Error!", 0, null);
			e.getMessage();
		}
	}
	

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Stage stage = (Stage) Main.getMainScene().getWindow();
		stage.setHeight(600);
		stage.setWidth(620);
	}

}

package gui;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import hiago.silva.entities.Locadora;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class SimularDevolucaoController implements Initializable {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	Locadora locadora = new Locadora();
	MainViewControler mv = new MainViewControler();

	@FXML
	DatePicker dateInicial;
	
	@FXML
	DatePicker dateFim;
	
	@FXML
	TextField txtFielPlaca;
	
	@FXML
	TextField txtFielValorTotal;

	@FXML
	Button btSimular;

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
			
			
			Double valorTotal = locadora.simularValorDevolucao( txtFielPlaca.getText(), dias);
			
			txtFielValorTotal.setText(valorTotal.toString());
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Dados Incorretos", "Error!", 0, null);
			e.getMessage();
		}
	}
	

	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}

}

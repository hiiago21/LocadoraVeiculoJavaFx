package hiago.silva.controllers;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import hiago.silva.app.MainLocadora;
import hiago.silva.model.entities.Locadora;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LocacaoController implements Initializable {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	Locadora locadora = new Locadora();
	MainViewControler mv = new MainViewControler();

	@FXML
	private DatePicker dateInicial;
	
	@FXML
	private DatePicker dateFim;
	
	@FXML
	private TextField txtFielNome;
	
	@FXML
	private TextField txtFielPlaca;

	@FXML
	private Button btLocar;
	
	@FXML
	private Pane fundo1;
	@FXML
	private Pane fundo2;

	@FXML
	private void btLocarAction() {
		try{
			locadora.importar("C:\\Users\\locadora.txt");
			String pattern = "dd/MM/yyyy";
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
	
			String dateInicia = dateFormatter.format(dateInicial.getValue());
			String dateFinal = dateFormatter.format(dateFim.getValue());
			

			long ini = sdf.parse(dateInicia).getTime();
			long fim =  sdf.parse(dateFinal).getTime();
			
			int dias  = (int) Math.ceil((fim - ini)/1000/60/60/24);
			
			boolean result = locadora.locar(txtFielPlaca.getText(), txtFielNome.getText(), sdf.parse(dateInicia));
			
			
			if(result) {
				JOptionPane.showInternalMessageDialog(null, "Locação","Locação efetuada com sucesso"
						, JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null, "Carro Locado ou Não consta", "Error!", 0, null);
			}
			
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Dados Incorretos", "Error!", 0, null);
			e.getMessage();
		}
	}
	

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Stage stage = (Stage) MainLocadora.getMainScene().getWindow();
		stage.setHeight(600);
		stage.setWidth(800);
		
		fundo1.setPrefHeight(800);
		fundo1.setPrefWidth(800);
	}

}

package gui;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import hiago.silva.entities.Locadora;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ImportarController implements Initializable {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	Locadora locadora = new Locadora();
	MainViewControler mv = new MainViewControler();

	@FXML
	TextField txtFielPath;
	
	@FXML
	Label labelAviso;

	@FXML
	Button btImportar;

	@FXML
	private void btImportarAction() {
		String path = "C:\\Users\\locadora.txt";
		
		locadora.importar(path);
		txtFielPath.setText(path);
		labelAviso.setText("Impotação Concluída");
	}
	

	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}

}

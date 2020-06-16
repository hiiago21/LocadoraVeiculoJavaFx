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

public class ExportController implements Initializable {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	Locadora locadora = new Locadora();
	MainViewControler mv = new MainViewControler();

	@FXML
	TextField txtFielPath;
	
	@FXML
	Label labelAviso;

	@FXML
	Button btExportar;

	@FXML
	private void btbtExportarAction() {
		
		String path = "C:\\Users\\locadora.txt";
		locadora.importar(path);
		String result = locadora.exportar();
		txtFielPath.setText(path);
		labelAviso.setText(result);
	}
	

	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}

}

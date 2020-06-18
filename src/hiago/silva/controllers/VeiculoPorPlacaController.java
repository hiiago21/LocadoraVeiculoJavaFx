package hiago.silva.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import hiago.silva.app.Main;
import hiago.silva.model.entities.Locadora;
import hiago.silva.model.entities.Veiculo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class VeiculoPorPlacaController implements Initializable {

	Locadora locadora = new Locadora();
	MainViewControler mv = new MainViewControler();

	@FXML
	private TextField txtFielPlaca;

	@FXML
	private Button btConsultar;

	@FXML
	private void btConsultarAction() {
		locadora.importar("C:\\Users\\locadora.txt");
		Veiculo returnConsulta;
		try{
			 returnConsulta = locadora.consultarVeiculo(txtFielPlaca.getText().toUpperCase());
			 if (returnConsulta.getClass().getSimpleName().equalsIgnoreCase("Moto")) {
					mv.loadView("/gui/MotoView.fxml", (MotoController controller) -> {
						controller.upadateTable(returnConsulta, true, true);
					});
			 }
			 else if (returnConsulta.getClass().getSimpleName().equalsIgnoreCase("Caminhao")) {
					mv.loadView("/gui/CaminhaoView.fxml", (CaminhaoController controller) -> {
						controller.upadateTable(returnConsulta, true, true);
					});
			 }
			 else if (returnConsulta.getClass().getSimpleName().equalsIgnoreCase("Passeio")) {
					mv.loadView("/gui/PasseioView.fxml", (PasseioController controller) -> {
						controller.upadateTable(returnConsulta, true, true);
					});
			 }
			}catch(	Exception e){
				e.getMessage();
				JOptionPane.showMessageDialog(null, "Placa incorreta ou n�o consta no sistema", "Error!", 0, null);
			}
	}
	

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Stage stage = (Stage) Main.getMainScene().getWindow();
		stage.setHeight(600);
		stage.setWidth(620);
	}

}

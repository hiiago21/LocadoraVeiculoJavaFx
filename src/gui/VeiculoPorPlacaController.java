package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import hiago.silva.entities.Locadora;
import hiago.silva.entities.Veiculo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class VeiculoPorPlacaController implements Initializable {

	Locadora locadora = new Locadora();
	MainViewControler mv = new MainViewControler();

	@FXML
	TextField txtFielPlaca;

	@FXML
	Button btConsultar;

	@FXML
	private void btConsultarAction() {
		locadora.importar("C:\\Users\\locadora.txt");
		Veiculo returnConsulta;
		try{
			 returnConsulta = locadora.consultarVeiculo(txtFielPlaca.getText());
			 if (returnConsulta.getTipoVeiculo().equals("Moto")) {
					mv.loadView("/gui/MotoView.fxml", (MotoController controller) -> {
						controller.upadateTable(returnConsulta, true);
					});
			 }
			 else if (returnConsulta.getTipoVeiculo().equals("Caminhão")) {
					mv.loadView("/gui/CaminhaoView.fxml", (CaminhaoController controller) -> {
						controller.upadateTable(returnConsulta, true);
					});
			 }
			 else if (returnConsulta.getTipoVeiculo().equals("Passeio")) {
					mv.loadView("/gui/PasseioView.fxml", (PasseioController controller) -> {
						controller.upadateTable(returnConsulta, true);
					});
			 }
			}catch(	Exception e){
				e.getMessage();
				JOptionPane.showMessageDialog(null, "Placa incorreta ou não consta no sistema", "Error!", 0, null);
			}
	}
	

	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}

}

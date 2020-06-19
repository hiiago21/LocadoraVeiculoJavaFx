package hiago.silva.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import hiago.silva.app.MainLocadora;
import hiago.silva.model.entities.Locadora;
import hiago.silva.model.entities.Veiculo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class VenderController implements Initializable {

	Locadora locadora = new Locadora();
	MainViewControler mv = new MainViewControler();

	@FXML
	TextField txtFielPlaca;

	@FXML
	Button btVender;

	@FXML
	private void btConsultarAction() {
		locadora.importar("C:\\Users\\locadora.txt");
		Veiculo returnConsulta;
		try{
			 returnConsulta = locadora.consultarVeiculo(txtFielPlaca.getText().toUpperCase());
			 if (returnConsulta.getClass().getSimpleName().equalsIgnoreCase("Moto")) {
				 String[] options = { "Sim", "Não" };
					int op = JOptionPane.showOptionDialog(null, "Você tem certeza?", "Warning",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
					null, options, options[0]);
					if(op == 0) {
						boolean load =  mv.loadView("/gui/MotoView.fxml", (MotoController controller) -> {
							controller.upadateTable(returnConsulta, true, true);
						});
						if(load) {
							String result = locadora.vender(returnConsulta.getPlaca());
							 JOptionPane.showInternalMessageDialog(null, result, "Veiculo Vendido"
										, JOptionPane.INFORMATION_MESSAGE);
							 return;
						}
					}
			 }else if (returnConsulta.getClass().getSimpleName().equalsIgnoreCase("Caminhao")) {
				 String[] options = { "Sim", "Não" };
					int op = JOptionPane.showOptionDialog(null, "Você tem certeza?", "Warning",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
					null, options, options[0]);
					if(op == 0) {
						boolean load = mv.loadView("/gui/CaminhaoView.fxml", (CaminhaoController controller) -> {
							controller.upadateTable(returnConsulta, true, true);
						});
						if(load) {
							String result = locadora.vender(returnConsulta.getPlaca());
							 JOptionPane.showInternalMessageDialog(null, result, "Veiculo Vendido"
										, JOptionPane.INFORMATION_MESSAGE);
							 return;
						}
					}
			 }else if (returnConsulta.getClass().getSimpleName().equalsIgnoreCase("Passeio")) {
				 String[] options = { "Sim", "Não" };
					int op = JOptionPane.showOptionDialog(null, "Você tem certeza?", "Warning",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
					null, options, options[0]);
					if(op == 0) {
						boolean load = mv.loadView("/gui/PasseioView.fxml", (PasseioController controller) -> {
								controller.upadateTable(returnConsulta, true, true);
							});
						if(load) {
							String result = locadora.vender(returnConsulta.getPlaca());
							 JOptionPane.showInternalMessageDialog(null, result, "Veiculo Vendido"
										, JOptionPane.INFORMATION_MESSAGE);
							 return;
						}
					}
			 }
			}catch(	Exception e){
				e.getMessage();
				JOptionPane.showMessageDialog(null, "Placa incorreta ou não consta no sistema", "Error!", 0, null);
			}
	}
	

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Stage stage = (Stage) MainLocadora.getMainScene().getWindow();
		stage.setHeight(600);
		stage.setWidth(620);
	}

}


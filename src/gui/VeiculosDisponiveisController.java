package gui;

import java.net.URL;
import java.util.ResourceBundle;

import hiago.silva.app.Main;
import hiago.silva.entities.Locadora;
import hiago.silva.entities.Veiculo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class VeiculosDisponiveisController implements Initializable {
	
	private Locadora locadora = new Locadora();
	
	
	private ObservableList<Veiculo> obsList;

	@FXML
	private TableView<Veiculo> tableViewVeiculosDisponiveis;
	
	@FXML
	private TableColumn<Veiculo, String> tableColunmPlaca;
	
	@FXML
	private TableColumn<Veiculo, String> tableColunmTipo;

	@FXML
	private TableColumn<Veiculo, String> tableColunmModelo;

	@FXML
	private TableColumn<Veiculo, String> tableColunmMarca;

	@FXML
	private TableColumn<Veiculo, Long> tableColunmKm;


	@FXML
	private TableColumn<Veiculo, String> tableColunmData;
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		initializeNodes();
	}


	private void initializeNodes() {
		
	
		tableColunmTipo.setCellValueFactory(new PropertyValueFactory<>("tipoVeiculo"));
		tableColunmData.setCellValueFactory(new PropertyValueFactory<>("ano"));
		tableColunmKm.setCellValueFactory(new PropertyValueFactory<>("kmRodado"));
		tableColunmMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
		tableColunmModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
		tableColunmPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
		
		Stage stage = (Stage)Main.getMainScene().getWindow();
		tableViewVeiculosDisponiveis.prefHeightProperty().bind(stage.heightProperty());
	}
	
	public void upadateTable() { 
		locadora.importar("C:\\Users\\locadora.txt");
		obsList = FXCollections.observableArrayList(locadora.veiculosDisponiveis());
		tableViewVeiculosDisponiveis.setItems(obsList);
	}
	
}

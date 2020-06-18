package hiago.silva.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import hiago.silva.app.Main;
import hiago.silva.model.entities.Locadora;
import hiago.silva.model.entities.Veiculo;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

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
		
	
		tableColunmTipo.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Veiculo, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Veiculo, String> param) {
				return new SimpleStringProperty(param.getValue().getClass().getSimpleName());
			}
		});
		tableColunmData.setCellValueFactory(new PropertyValueFactory<>("ano"));
		tableColunmKm.setCellValueFactory(new PropertyValueFactory<>("kmRodado"));
		tableColunmMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
		tableColunmModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
		tableColunmPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
		
		Stage stage = (Stage) Main.getMainScene().getWindow();

		stage.setHeight(600);
		stage.setWidth(800);

		tableViewVeiculosDisponiveis.setPrefHeight(stage.getHeight());
	}
	
	public void upadateTable() { 
		locadora.importar("C:\\Users\\locadora.txt");
		obsList = FXCollections.observableArrayList(locadora.veiculosDisponiveis());
		tableViewVeiculosDisponiveis.setItems(obsList);
	}
	
}

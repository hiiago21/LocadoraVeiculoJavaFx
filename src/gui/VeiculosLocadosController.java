package gui;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import hiago.silva.app.Main;
import hiago.silva.entities.Locacao;
import hiago.silva.entities.Locadora;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

public class VeiculosLocadosController implements Initializable {

	private Locadora locadora = new Locadora();

	private ObservableList<Locacao> obsList;

	@FXML
	private TableView<Locacao> tableViewVeiculosDisponiveis;

	@FXML
	private TableColumn<Locacao, String> tableColunmPlaca;

	@FXML
	private TableColumn<Locacao, String> tableColunmNome;

	@FXML
	private TableColumn<Locacao, String> tableColunmModelo;

	@FXML
	private TableColumn<Locacao, String> tableColunmMarca;

	@FXML
	private TableColumn<Locacao, String> tableColunmKm;

	@FXML
	private TableColumn<Locacao, Date> tableColunmData;

	@FXML
	private Button btAtualizar;

	@FXML
	public void onBtAtualizarAction() {

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		initializeNodes();
	}

	private void initializeNodes() {

		tableColunmNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tableColunmData.setCellValueFactory(new PropertyValueFactory<>("dataLocacao"));
		tableColunmData.setCellFactory(column -> {
			TableCell<Locacao, Date> cell = new TableCell<Locacao, Date>() {
				private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

				@Override
				protected void updateItem(Date item, boolean empty) {
					super.updateItem(item, empty);
					if (empty) {
						setText(null);
					} else {
						this.setText(sdf.format(item));

					}
				}
			};

			return cell;
		});
		
		tableColunmKm.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Locacao, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Locacao, String> param) {
						return new SimpleStringProperty(
								String.valueOf(param.getValue().getVeiculoLocado().getKmRodado()));
					}
				});
		tableColunmMarca.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Locacao, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Locacao, String> param) {
						return new SimpleStringProperty(String.valueOf(param.getValue().getVeiculoLocado().getMarca()));
					}
				});
		tableColunmModelo.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Locacao, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Locacao, String> param) {
						return new SimpleStringProperty(
								String.valueOf(param.getValue().getVeiculoLocado().getModelo()));
					}
				});
		tableColunmPlaca.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Locacao, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Locacao, String> param) {
						return new SimpleStringProperty(String.valueOf(param.getValue().getVeiculoLocado().getPlaca()));
					}
				});

		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewVeiculosDisponiveis.prefHeightProperty().bind(stage.heightProperty());
	}

	public void upadateTable() {
		locadora.importar("C:\\Users\\locadora.txt");
		obsList = FXCollections.observableArrayList(locadora.getLocacao());
		tableViewVeiculosDisponiveis.setItems(obsList);
	}

}

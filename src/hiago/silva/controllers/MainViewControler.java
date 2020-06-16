package hiago.silva.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javax.swing.JOptionPane;

import hiago.silva.app.Main;
import hiago.silva.model.entities.Locacao;
import hiago.silva.model.entities.Locadora;
import hiago.silva.model.entities.Veiculo;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class MainViewControler implements Initializable {

	Locadora locadora = new Locadora();
	
	private ObservableList<Veiculo> obsList;
	private ObservableList<Locacao> obsListl;
	
	@FXML
	private MenuItem veiculoPorPlaca;
	@FXML
	private MenuItem veiculosDisponveis;
	@FXML
	private MenuItem veiculosLocados;
	@FXML
	private MenuItem simular;
	@FXML
	private MenuItem locacao;
	@FXML
	private MenuItem devolucao;
	@FXML
	private MenuItem importe;
	@FXML
	private MenuItem exporte;
	@FXML
	private MenuItem compra;
	@FXML
	private MenuItem venda;
	@FXML
	private MenuItem setValorPadra;
	@FXML
	private TableView<Veiculo> veiculosPrincipalDisp;
	@FXML
	private TableView<Locacao> veiculosPrincipalLoc;
	@FXML
	private TableColumn<Veiculo, String>  veiculosDisp;
	@FXML
	private TableColumn<Locacao, String> veiculosLoc;
	@FXML
	private Pane fundo1;
	@FXML
	private Pane fundo2;
	
	

	@FXML
	public void onMenuItemVeiculoPorPlacaAction() {
		loadView("/gui/VeiculoPorPlacaView.fxml", x -> {
		});
	}

	@FXML
	public void onMenuItemVeiculosDisponveisAction() {

		loadView("/gui/VeiculosDisponiveisView.fxml", (VeiculosDisponiveisController controller) -> {
			controller.upadateTable();
		});
	}

	@FXML
	public void onMenuItemveiculosLocadosAction() {

		loadView("/gui/VeiculosLocadosView.fxml", (VeiculosLocadosController controller) -> {
			controller.upadateTable();
		});
	}

	@FXML
	public void onMenuItemSimularAction() {
		loadView("/gui/SimularDevolucaoView.fxml", x -> {
		});
	}

	@FXML
	public void onMenuItemLocacaoAction() {
		loadView("/gui/LocacaoView.fxml", x -> {
		});
	}

	@FXML
	public void onMenuItemDevolucaoAction() {
		loadView("/gui/DevolucaoView.fxml", x -> {
		});
	}

	@FXML
	public void onMenuItemImportarAction() {
		loadView("/gui/ImportarView.fxml", x -> {
		});
	}

	@FXML
	public void onMenuItemExportarAction() {
		loadView("/gui/ExportarView.fxml", x -> {
		});
	}

	@FXML
	public void onMenuItemComprarAction() {
		loadView("/gui/ComprarView.fxml", x -> {
		});
	}

	@FXML
	public void onMenuItemVenderAction() {
		loadView("/gui/VenderView.fxml", x -> {
		});
	}
	
	@FXML
	public void onMenuSetValorPadraoAction() {
		loadView("/gui/SetValorPadraoView.fxml", x -> {
		});
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();

	}


	private void initializeNodes() {
	
		veiculosDisp.setCellValueFactory(new PropertyValueFactory<>("modelo"));
		veiculosLoc.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Locacao, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Locacao, String> param) {
						return new SimpleStringProperty(
								String.valueOf(param.getValue().getVeiculoLocado().getModelo()));
					}
				});
		update();
		
	}
	
	public void update() {
		locadora.importar("C:\\Users\\locadora.txt");
		obsList = FXCollections.observableArrayList(locadora.veiculosDisponiveis());
		obsListl = FXCollections.observableArrayList(locadora.getLocacao());
		veiculosPrincipalDisp.setItems(obsList);
		veiculosPrincipalLoc.setItems(obsListl);
		
	}
	

	public synchronized <T> void loadView(String path, Consumer<T> inicializacaoAction) {

		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
			VBox cenaAtual = loader.load();
			Scene mainScene = Main.getMainScene();

			VBox mainVbox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

			Node mainMenu = mainVbox.getChildren().get(0);

			mainVbox.getChildren().clear();
			mainVbox.getChildren().add(mainMenu);
			mainVbox.getChildren().addAll(cenaAtual.getChildren());

			T controller = loader.getController();
			inicializacaoAction.accept(controller);
			

		} catch (IOException e) {
			e.getMessage();
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro em carregar a página", "Error!", 0, null);
		}

	}

}

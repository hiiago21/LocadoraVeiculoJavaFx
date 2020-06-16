package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javax.swing.JOptionPane;

import hiago.silva.app.Main;
import hiago.silva.entities.Locadora;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class MainViewControler implements Initializable {

	Locadora locadora;
	
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

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
	}

	

	protected synchronized <T> void loadView(String path, Consumer<T> inicializacaoAction) {

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
			JOptionPane.showMessageDialog(null, "Erro em carregar a página", "Error!", 0, null);
		}

	}

}

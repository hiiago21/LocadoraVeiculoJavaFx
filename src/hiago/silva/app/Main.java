package hiago.silva.app;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class Main extends Application {
	private static Scene mainScene;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
			ScrollPane scrollPane = loader.load();
			mainScene = new Scene(scrollPane);
			
			scrollPane.setFitToHeight(true);
			scrollPane.setFitToWidth(true);
			
			primaryStage.setScene(mainScene);
			primaryStage.setTitle("Sistema Controle Locadora");
			primaryStage.show();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro em carregar a p�gina","Error!", 0, null);
		}

	}
	
	public static Scene getMainScene() {
		return mainScene;
	}

	public static void main(String[] args) {
		launch(args);
		
	}
}
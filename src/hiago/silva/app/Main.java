package hiago.silva.app;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
	private static Scene mainScene;
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
			ScrollPane scrollPane = loader.load();
			mainScene = new Scene(scrollPane);
			
			Image applicationIcon = new Image(getClass().getResourceAsStream("/images/logoSSMotors.png"));
			primaryStage.getIcons().add(applicationIcon);
			
			scrollPane.setFitToHeight(true);
			scrollPane.setFitToWidth(true);
			primaryStage.setHeight(600.0);
			primaryStage.setWidth(800);
			primaryStage.setResizable(false);
			
			primaryStage.setScene(mainScene);
			primaryStage.setTitle("Sistema Controle Locadora");
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro em carregar a página","Error!", 0, null);
		}

	}
	
	public static Scene getMainScene() {
		return mainScene;
	}

	public static void main(String[] args) {
		launch(args);
		
	}
}
package scyjc1.frogger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
	public static Stage mainStage;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		mainStage = primaryStage;
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/HomeView.fxml"));
			Scene scene = new Scene(root, 600, 800);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Frogger");
			primaryStage.getIcons().add(new Image("file:resources/images/frogger_icon.png"));
			primaryStage.show();
			root.requestFocus();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

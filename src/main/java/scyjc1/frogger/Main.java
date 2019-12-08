package scyjc1.frogger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
	public static Stage mainStage;

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Initialises the primary stage of the application.
	 * This includes the loading of the home view, fonts, title and icon.
	 *
	 * @param primaryStage the primary stage of the application.
	 */
	@Override
	public void start(Stage primaryStage) {
		// Load font.
		Font.loadFont(getClass().getResourceAsStream("/fonts/prstartk.ttf"), 10);
		// Load the home view.
		mainStage = primaryStage;
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/HomeView.fxml"));
			Scene scene = new Scene(root, 600, 800);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Frogger");
			primaryStage.getIcons().add(new Image("file:src/main/resources/images/frogger_icon.png"));
			primaryStage.show();
			root.requestFocus();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

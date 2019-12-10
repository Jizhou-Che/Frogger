package scyjc1.frogger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
	public static Stage primaryStage;

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
		Main.primaryStage = primaryStage;
		primaryStage.setTitle("Frogger");
		primaryStage.getIcons().add(new Image("file:src/main/resources/images/frogger_icon.png"));
		primaryStage.setResizable(false);
		switchScene(0);
		primaryStage.show();
	}

	public static void switchScene(int id) {
		try {
			Parent loader;
			Scene scene;
			switch (id) {
				case 0:
					// Switch to Home view.
					loader = FXMLLoader.load(Main.class.getResource("/view/HomeView.fxml"));
					scene = new Scene(loader, 600, 800);
					scene.getRoot().requestFocus();
					primaryStage.setScene(scene);
					break;
				case 1:
					// Switch to Game view.
					loader = FXMLLoader.load(Main.class.getResource("/view/GameView.fxml"));
					scene = new Scene(loader, 600, 800);
					scene.getRoot().requestFocus();
					primaryStage.setScene(scene);
					break;
				case 2:
					// Switch to High Score view.
					loader = FXMLLoader.load(Main.class.getResource("/view/HighScoreView.fxml"));
					scene = new Scene(loader, 600, 800);
					scene.getRoot().requestFocus();
					primaryStage.setScene(scene);
					break;
				case 3:
					// Switch to Leaderboard view.
					loader = FXMLLoader.load(Main.class.getResource("/view/LeaderboardView.fxml"));
					scene = new Scene(loader, 600, 800);
					scene.getRoot().requestFocus();
					primaryStage.setScene(scene);
					break;
				case 4:
					// Switch to Help view.
					loader = FXMLLoader.load(Main.class.getResource("/view/HelpView.fxml"));
					scene = new Scene(loader, 600, 800);
					scene.getRoot().requestFocus();
					primaryStage.setScene(scene);
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

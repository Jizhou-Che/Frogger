package scyjc1.frogger.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import scyjc1.frogger.Main;

public class LeaderboardController {
	@FXML
	private Text leaderboard;
	@FXML
	private Text home;

	@FXML
	private void initialize() {
		// Set font.
		Font prstartk = Font.loadFont(getClass().getResourceAsStream("/fonts/prstartk.ttf"), 25);
		leaderboard.setFont(prstartk);
		home.setFont(prstartk);
	}

	@FXML
	private void keyPressed(KeyEvent keyEvent) {
		if (keyEvent.getCode() == KeyCode.ENTER) {
			// Switch to home view.
			try {
				Parent homeLoader = FXMLLoader.load(getClass().getResource("/view/HomeView.fxml"));
				Scene homeScene = new Scene(homeLoader, 600, 800);
				homeScene.getRoot().requestFocus();
				Main.mainStage.setScene(homeScene);
			} catch(Exception e) {
				//
			}
		}
	}
}

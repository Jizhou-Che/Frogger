package scyjc1.frogger.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import scyjc1.frogger.Main;

public class HighScoreController {
	@FXML
	private AnchorPane pane;
	@FXML
	private Text name;

	@FXML
	private void initialize() {
		// Load font.
		Font.loadFont(getClass().getResourceAsStream("/fonts/prstartk.ttf"), 10);
		pane.setOnKeyTyped(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent keyEvent) {
				if (name.getText().length() < 24 && !keyEvent.getCharacter().isBlank()) {
					name.setText(name.getText().substring(0, name.getText().length() - 2) + keyEvent.getCharacter().toUpperCase() + " <");
				}
			}
		});
		pane.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				switch (keyEvent.getCode()) {
					case SPACE:
						if (name.getText().length() < 24) {
							name.setText(name.getText().substring(0, name.getText().length() - 1) + " <");
						}
						break;
					case BACK_SPACE:
						if (name.getText().length() > 4) {
							// Name is not empty.
							name.setText(name.getText().substring(0, name.getText().length() - 3) + " <");
						}
						break;
					case ENTER:
						if (name.getText().substring(2, name.getText().length() - 2).isBlank()) {
							// Invalid name.
							name.setText("> FROGGER <");
						} else {
							// Switch to leaderboard view.
							try {
								Parent leaderboardLoader = FXMLLoader.load(getClass().getResource("/view/LeaderboardView.fxml"));
								Scene leaderboardScene = new Scene(leaderboardLoader, 600, 800);
								leaderboardScene.getRoot().requestFocus();
								Main.mainStage.setScene(leaderboardScene);
							} catch (Exception e) {
								//
							}
						}
						break;
				}
			}
		});
	}
}

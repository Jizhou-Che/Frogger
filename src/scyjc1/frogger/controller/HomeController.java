package scyjc1.frogger.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import scyjc1.frogger.Main;

public class HomeController {
	@FXML
	private VBox optionBox;

	private int optionOld = 0;
	private int optionNew = 0;
	static boolean musicOn = true;

	@FXML
	private void initialize() {
		// Set font.
		Font prstartk = Font.loadFont(getClass().getResourceAsStream("/fonts/prstartk.ttf"), 10);
		// Set style for start.
		optionBox.getChildren().get(0).getStyleClass().add("active");
		// Set music status.
		if (!musicOn) {
			Text musicText = (Text) optionBox.getChildren().get(3);
			musicText.setText("MUSIC: OFF");
		}
	}

	@FXML
	private void keyPressed(KeyEvent event) {
		optionOld = optionNew;
		switchOption(event.getCode());
	}

	private void switchOption(KeyCode code) {
		// Get new option.
		switch (code) {
			case W:
				optionNew = optionOld - 1;
				if (optionNew < 0) {
					optionNew += 5;
				}
				break;
			case S:
				optionNew = optionOld + 1;
				if (optionNew > 4) {
					optionNew -= 5;
				}
				break;
			case ENTER:
				switch (optionNew) {
					case 0:
						// Start game.
						try {
							Parent gameLoader = FXMLLoader.load(getClass().getResource("/view/GameView.fxml"));
							Scene gameScene = new Scene(gameLoader, 600, 800);
							Main.mainStage.setScene(gameScene);
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
					case 1:
						// Go to leaderboard.
						try {
							Parent leaderboardLoader = FXMLLoader.load(getClass().getResource("/view/LeaderboardView.fxml"));
							Scene leaderboardScene = new Scene(leaderboardLoader, 600, 800);
							leaderboardScene.getRoot().requestFocus();
							Main.mainStage.setScene(leaderboardScene);
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
					case 3:
						// Switch music.
						switchMusic();
						break;
					case 4:
						// Quit game.
						Platform.exit();
						System.exit(0);
						break;
				}
				break;
		}

		// Set text.
		if (optionNew != optionOld) {
			// Reset original option text.
			Text textOld = (Text) optionBox.getChildren().get(optionOld);
			textOld.setText(textOld.getText().substring(2, textOld.getText().length() - 2));
			textOld.getStyleClass().clear();
			// Set new option text.
			Text textNew = (Text) optionBox.getChildren().get(optionNew);
			textNew.setText("> " + textNew.getText() + " <");
			textNew.getStyleClass().add("active");
		}
	}

	private void switchMusic() {
		Text tMusic = (Text) optionBox.getChildren().get(3);
		if (musicOn) {
			tMusic.setText("> MUSIC: OFF <");
		} else {
			tMusic.setText("> MUSIC: ON <");
		}
		musicOn = !musicOn;
	}
}

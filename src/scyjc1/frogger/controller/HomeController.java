package scyjc1.frogger.controller;

import javafx.fxml.FXML;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import scyjc1.frogger.Main;

public class HomeController {
	@FXML
	private VBox optionBox;

	private int optionOld = 0;
	private int optionNew = 0;
	private boolean musicOn = true;

	@FXML
	private void initialize() {
		// Set font.
		Font prstartk = Font.loadFont(getClass().getResourceAsStream("/fonts/prstartk.ttf"), 25);
		for (Node n : optionBox.getChildren()) {
			Text t = (Text) n;
			t.setFont(prstartk);
			// Set style for start.
			if (n == optionBox.getChildren().get(0)) {
				t.setScaleX(1.2);
				t.setScaleY(1.2);
			}
		}
	}

	@FXML
	public void keyPressed(javafx.scene.input.KeyEvent event) {
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
						} catch(Exception e) {
							//
						}
						break;
					case 3:
						// Switch music.
						switchMusic();
						break;
					case 4:
						// Quit game.
						Platform.exit();
						break;
				}
				break;
		}

		// Set text.
		if (optionNew != optionOld) {
			// Reset original option text.
			Text tOld = (Text) optionBox.getChildren().get(optionOld);
			tOld.setText(tOld.getText().substring(2, tOld.getText().length() - 2));
			tOld.setScaleX(1);
			tOld.setScaleY(1);
			// Set new option text.
			Text tNew = (Text) optionBox.getChildren().get(optionNew);
			tNew.setText("> " + tNew.getText() + " <");
			tNew.setScaleX(1.2);
			tNew.setScaleY(1.2);
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

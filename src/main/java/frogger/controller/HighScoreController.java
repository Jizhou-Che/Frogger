package frogger.controller;

import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import frogger.Main;
import frogger.model.Record;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Controls behaviours and handles events on the high score view.
 */
public class HighScoreController {
	@FXML
	public Text title;
	@FXML
	private Text name;

	/**
	 * Initialises the High Score view.
	 */
	@FXML
	private void initialize() {
		// Set title.
		title.setText("HIGH SCORE: " + GameController.score);
	}

	/**
	 * Handles key-typed events on the high score view.
	 * This includes the typing of name and its validity check.
	 *
	 * @param keyEvent the key-typed event.
	 */
	public void keyTyped(KeyEvent keyEvent) {
		if (name.getText().length() < 21 && !keyEvent.getCharacter().isBlank()) {
			name.setText(name.getText().substring(0, name.getText().length() - 2) + keyEvent.getCharacter().toUpperCase() + " <");
		}
	}

	/**
	 * Handles key-pressed events on the high score view.
	 * This includes the typing of spaces, backspaces and the validity check of the name before entering the leaderboard view.
	 *
	 * @param keyEvent the key-pressed event.
	 */
	public void keyPressed(KeyEvent keyEvent) {
		switch (keyEvent.getCode()) {
			case SPACE:
				if (name.getText().length() < 21) {
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
					// Write high score to file.
					try {
						Record records = new Record();
						records.add(GameController.score, LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yy")), name.getText().substring(2, name.getText().length() - 2));
						records.sort();
						records.write();
					} catch (Exception e) {
						e.printStackTrace();
					}
					// Switch to leaderboard view.
					Main.switchScene(3);
				}
				break;
		}
	}
}

package frogger.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import frogger.Main;
import frogger.model.Record;

/**
 * Controls behaviours and handles events on the Leaderboard view.
 */
public class LeaderboardController {
	@FXML
	private ListView<String> score;
	@FXML
	private ListView<String> date;
	@FXML
	private ListView<String> name;

	/**
	 * Initialises the leaderboard view.
	 */
	@FXML
	private void initialize() {
		// Load high scores.
		try {
			Record records = new Record();
			for (int i = 0; i < records.size(); i++) {
				score.getItems().add(Integer.toString(records.getScore(i)));
				date.getItems().add(records.getDate(i));
				name.getItems().add(records.getName(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Handles the key-pressed events on the leaderboard view.
	 * This includes the scene switching to the home view.
	 *
	 * @param keyEvent the key-pressed event.
	 */
	@FXML
	private void keyPressed(KeyEvent keyEvent) {
		if (keyEvent.getCode() == KeyCode.ENTER) {
			// Switch to home view.
			Main.switchScene(0);
		}
	}
}

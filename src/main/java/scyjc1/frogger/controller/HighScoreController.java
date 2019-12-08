package scyjc1.frogger.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import scyjc1.frogger.Main;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

/**
 * Controls behaviours and handles events on the high score view.
 */
public class HighScoreController {
	@FXML
	public Text title;
	@FXML
	private Text name;

	/**
	 * Initialises the high score view.
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
						File dataDirectory = new File(".data");
						File leaderboardFile = new File(dataDirectory, "leaderboard.csv");
						if (!dataDirectory.mkdir()) {
							if (!dataDirectory.exists()) {
								throw new Exception("Failed to create the data directory.");
							}
						}
						if (!leaderboardFile.createNewFile()) {
							if (!leaderboardFile.exists()) {
								throw new Exception("Failed to create the leaderboard file.");
							}
						}
						List<String> records = Files.readAllLines(Paths.get(".data/leaderboard.csv"), StandardCharsets.UTF_8);
						records.add(GameController.score + "," + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yy")) + "," + name.getText().substring(2, name.getText().length() - 2));
						records.sort(Comparator.comparing(s -> Integer.parseInt(s.substring(0, s.indexOf(','))), Comparator.reverseOrder()));
						if (records.size() >= 10) {
							Files.write(Paths.get(".data/leaderboard.csv"), records.subList(0, 10));
						} else {
							Files.write(Paths.get(".data/leaderboard.csv"), records.subList(0, records.size()));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					// Switch to leaderboard view.
					try {
						Parent leaderboardLoader = FXMLLoader.load(getClass().getResource("/view/LeaderboardView.fxml"));
						Scene leaderboardScene = new Scene(leaderboardLoader, 600, 800);
						leaderboardScene.getRoot().requestFocus();
						Main.mainStage.setScene(leaderboardScene);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				break;
		}
	}
}

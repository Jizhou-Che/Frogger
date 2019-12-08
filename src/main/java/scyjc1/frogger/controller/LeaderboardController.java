package scyjc1.frogger.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import scyjc1.frogger.Main;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Controls behaviours and handles events on the leaderboard view.
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
			for (String record : records) {
				String[] parts = record.split(",", 3);
				score.getItems().add(parts[0]);
				date.getItems().add(parts[1]);
				name.getItems().add(parts[2]);
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
			try {
				Parent homeLoader = FXMLLoader.load(getClass().getResource("/view/HomeView.fxml"));
				Scene homeScene = new Scene(homeLoader, 600, 800);
				homeScene.getRoot().requestFocus();
				Main.mainStage.setScene(homeScene);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

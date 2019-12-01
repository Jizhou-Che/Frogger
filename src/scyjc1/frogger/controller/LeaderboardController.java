package scyjc1.frogger.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import scyjc1.frogger.Main;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class LeaderboardController {
	@FXML
	private ListView score;
	@FXML
	private ListView date;
	@FXML
	private ListView name;

	@FXML
	private void initialize() {
		// Load font.
		Font prstartkLarge = Font.loadFont(getClass().getResourceAsStream("/fonts/prstartk.ttf"), 10);

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
				//
			}
		}
	}
}

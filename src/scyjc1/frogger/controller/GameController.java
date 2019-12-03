package scyjc1.frogger.controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import scyjc1.frogger.Main;
import scyjc1.frogger.model.*;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class GameController {
	@FXML
	private World world;
	@FXML
	public HBox lifeBox;

	private AnimationTimer timer;
	private Frog frog;
	private BackgroundMusic bgm = BackgroundMusic.getBgm();
	private boolean gamePaused = false;
	private boolean musicMuted = false;
	static int score;

	@FXML
	private void initialize() {
		// Add frog.
		frog = new Frog();
		world.add(frog);
		// Initialise score.
		setScoreNumber(0);
		// Start game.
		startGame();
	}

	@FXML
	public void keyPressed(KeyEvent event) {
		switch (event.getCode()) {
			case SPACE:
				if (gamePaused) {
					resumeGame();
				} else {
					pauseGame();
				}
				gamePaused = !gamePaused;
				break;
			case M:
				if (musicMuted) {
					bgm.unmute();
				} else {
					bgm.mute();
				}
				musicMuted = !musicMuted;
				break;
		}
	}

	private void createTimer() {
		timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				if (frog.changeLives()) {
					setLivesNumber(frog.getLives());
				}
				if (frog.changeScore()) {
					setScoreNumber(frog.getScore());
				}
				if (frog.gameWon()) {
					// Clear slots.
					frog.clear_slots();
				}
				if (frog.gameOver()) {
					// Stop game.
					stopGame();
				}
			}
		};
	}

	private void startGame() {
		if (HomeController.musicOn) {
			bgm.play();
		}
		createTimer();
		timer.start();
		world.start();
	}

	private void pauseGame() {
		if (HomeController.musicOn) {
			bgm.pause();
		}
		timer.stop();
		frog.toggleNoMove();
		world.stop();
	}

	private void resumeGame() {
		if (HomeController.musicOn) {
			bgm.play();
		}
		timer.start();
		frog.toggleNoMove();
		world.resume();
	}

	private void stopGame() {
		if (HomeController.musicOn) {
			bgm.stop();
		}
		timer.stop();
		frog.toggleNoMove();
		world.stop();
		score = frog.getScore();
		// Check for high score.
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
			if (records.size() < 10 || frog.getScore() > Integer.parseInt(records.get(9).substring(0, records.get(9).indexOf(',')))) {
				// Go to high score.
				Parent highScoreLoader = FXMLLoader.load(getClass().getResource("/view/HighScoreView.fxml"));
				Scene highScoreScene = new Scene(highScoreLoader, 600, 800);
				highScoreScene.getRoot().requestFocus();
				Main.mainStage.setScene(highScoreScene);
			} else {
				// Go to leaderboard.
				Parent leaderboardLoader = FXMLLoader.load(getClass().getResource("/view/LeaderboardView.fxml"));
				Scene leaderboardScene = new Scene(leaderboardLoader, 600, 800);
				leaderboardScene.getRoot().requestFocus();
				Main.mainStage.setScene(leaderboardScene);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setLivesNumber(int lives) {
		for (int i = 2; i >= lives; i--) {
			lifeBox.getChildren().get(i).setVisible(false);
		}
	}

	private void setScoreNumber(int score) {
		// Clear digits.
		for (Digit d : world.getObjects(Digit.class)) {
			world.remove(d);
		}
		// Set new digits.
		if (score == 0) {
			world.add(new Digit(0, 30, 360, 25));
		} else {
			int shift = 0;
			while (score > 0) {
				int k = score - (score / 10) * 10;
				world.add(new Digit(k, 30, 360 - shift, 25));
				shift += 30;
				score /= 10;
			}
		}
	}
}

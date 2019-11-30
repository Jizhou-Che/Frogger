package scyjc1.frogger.controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import scyjc1.frogger.model.*;

public class GameController {
	@FXML
	private World world;
	@FXML
	public HBox lifeBox;

	private AnimationTimer timer;
	private Frog frog;
	private BackgroundMusic bgm = BackgroundMusic.getBgm();
	private boolean gamePaused = false;

	@FXML
	private void initialize() {
		// Add slots.
		world.add(new Slot(13, 96));
		world.add(new Slot(141, 96));
		world.add(new Slot(141 + 141 - 13, 96));
		world.add(new Slot(141 + 141 - 13 + 141 - 13 + 1, 96));
		world.add(new Slot(141 + 141 - 13 + 141 - 13 + 141 - 13 + 3, 96));
		// Add logs.
		world.add(new Log("file:resources/images/log_1.png", 150, 0, 166, 0.75));
		world.add(new Log("file:resources/images/log_1.png", 150, 220, 166, 0.75));
		world.add(new Log("file:resources/images/log_1.png", 150, 440, 166, 0.75));
		world.add(new Log("file:resources/images/log_3.png", 300, 0, 276, -2));
		world.add(new Log("file:resources/images/log_3.png", 300, 400, 276, -2));
		world.add(new Log("file:resources/images/log_1.png", 150, 50, 329, 0.75));
		world.add(new Log("file:resources/images/log_1.png", 150, 270, 329, 0.75));
		world.add(new Log("file:resources/images/log_1.png", 150, 490, 329, 0.75));
		// Add turtles.
		world.add(new Turtle(500, 376, -1, 130, 130));
		world.add(new Turtle(300, 376, -1, 130, 130));
		// Add wet turtles.
		world.add(new WetTurtle(700, 376, -1, 130, 130));
		world.add(new WetTurtle(600, 217, -1, 130, 130));
		world.add(new WetTurtle(400, 217, -1, 130, 130));
		world.add(new WetTurtle(200, 217, -1, 130, 130));
		// Add obstacles.
		world.add(new Obstacle("file:resources/images/truck_1_right.png", 0, 649, 1, 120, 120));
		world.add(new Obstacle("file:resources/images/truck_1_right.png", 300, 649, 1, 120, 120));
		world.add(new Obstacle("file:resources/images/truck_1_right.png", 600, 649, 1, 120, 120));
		world.add(new Obstacle("file:resources/images/car_1_left.png", 100, 597, -1, 50, 50));
		world.add(new Obstacle("file:resources/images/car_1_left.png", 250, 597, -1, 50, 50));
		world.add(new Obstacle("file:resources/images/car_1_left.png", 400, 597, -1, 50, 50));
		world.add(new Obstacle("file:resources/images/car_1_left.png", 550, 597, -1, 50, 50));
		world.add(new Obstacle("file:resources/images/truck_2_right.png", 0, 540, 1, 200, 200));
		world.add(new Obstacle("file:resources/images/truck_2_right.png", 500, 540, 1, 200, 200));
		world.add(new Obstacle("file:resources/images/car_1_left.png", 500, 490, -5, 50, 50));
		// Add frog.
		frog = new Frog("file:resources/images/frogger_up.png");
		world.add(frog);
		// Initialise score.
		setScoreNumber(0);
		// Start game.
		startGame();
	}

	@FXML
	public void keyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.SPACE) {
			if (gamePaused) {
				resumeGame();
			} else {
				pauseGame();
			}
			gamePaused = !gamePaused;
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
		bgm.play();
		createTimer();
		timer.start();
		world.start();
	}

	private void pauseGame() {
		bgm.pause();
		timer.stop();
		frog.toggleNoMove();
		world.stop();
	}

	private void resumeGame() {
		bgm.play();
		timer.start();
		frog.toggleNoMove();
		world.resume();
	}

	private void stopGame() {
		bgm.stop();
		timer.stop();
		frog.toggleNoMove();
		world.stop();
//		Alert alert = new Alert(Alert.AlertType.INFORMATION);
//		alert.setTitle("GAME OVER");
//		alert.setHeaderText("Score: " + frog.getScore());
//		alert.setContentText("Highest Possible Score: Infinity");
//		alert.show();
		// Check for high score.
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

package scyjc1.frogger.controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import scyjc1.frogger.Main;
import scyjc1.frogger.model.*;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Controls behaviours and handles events on the game view.
 */
public class GameController {
	@FXML
	private World world;
	@FXML
	private HBox lifeBox;
	@FXML
	private Text levelText;
	@FXML
	private Text message;
	@FXML
	private ImageView background;
	@FXML
	private ImageView snow;

	private AnimationTimer timer;
	private Frog frog;
	private BackgroundMusic bgm = BackgroundMusic.getBgm();
	private boolean gamePaused = false;
	private boolean musicMuted = false;
	static int score;
	private int level = 1;
	private int timeValue = 0;
	private boolean specialSlots = false;
	private int numSpecialSlots = 0;
	private boolean logSnakes = false;
	private int easterEgg = 0;
	private boolean easterEggOn = false;

	/**
	 * Initialises the game view.
	 */
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

	/**
	 * Handles key-pressed events on the game view.
	 * This includes the movement controls of the frog, pausing or muting the game, and the activation of the easter egg.
	 *
	 * @param event the key-pressed event.
	 */
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
			case S:
				if (easterEgg == 0) {
					easterEgg++;
				} else {
					easterEgg = 0;
				}
				break;
			case C:
				if (easterEgg == 1) {
					easterEgg++;
				} else if (easterEgg == 4) {
					easterEgg++;
				} else {
					easterEgg = 0;
				}
				break;
			case Y:
				if (easterEgg == 2) {
					easterEgg++;
				} else {
					easterEgg = 0;
				}
				break;
			case J:
				if (easterEgg == 3) {
					easterEgg++;
				} else {
					easterEgg = 0;
				}
				break;
			case DIGIT1:
				if (easterEgg == 5) {
					toggleEasterEgg();
				}
				easterEgg = 0;
				break;
			default:
				easterEgg = 0;
				break;
		}
	}

	/**
	 * Creates the timer for checking events consecutively.
	 * The events include the change of score or number of lives, addition of random elements, and the winning or losing of the game.
	 */
	private void createTimer() {
		timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				timeValue++;

				// Set number of lives if applicable.
				if (frog.changeLives()) {
					setLivesNumber(frog.getLives());
				}

				// Set the score if applicable.
				if (frog.changeScore()) {
					setScoreNumber(frog.getScore());
					// Hide the message on score change.
					message.setVisible(false);
				}

				// Make a random special slot periodically.
				if (specialSlots && timeValue % 500 == 0) {
					if (numSpecialSlots == 0) {
						// Choose a random slot.
						Slot randomSlot = world.getObjects(Slot.class).get((int) (Math.random() * 5));
						if (randomSlot.getStatus() == 0) {
							// Make a random special slot if the chosen slot is empty.
							if ((int) (Math.random() * 2) == 0) {
								randomSlot.setCrocodile();
							} else {
								randomSlot.setFly();
							}
						}
						numSpecialSlots++;
					} else {
						// Remove the special slot if there is already one.
						for (Slot s : world.getObjects(Slot.class)) {
							if (s.getStatus() >= 2) {
								s.setEmpty();
							}
						}
						numSpecialSlots--;
					}
				}

				// Put a log snake on a random log periodically.
				if (logSnakes && timeValue % 750 == 0) {
					world.add(new LogSnake(world.getObjects(Log.class).get((int) (Math.random() * world.getObjects(Log.class).size())), 0.5 - (int) (Math.random() * 2)));
					frog.toFront();
				}

				// Handles the completion of a level.
				if (frog.levelComplete()) {
					// Clear slots.
					frog.resetSlots();
					// Level up.
					levelUp();
					// Display message.
					levelText.setText("LEVEL-" + level);
					message.setVisible(true);
					// Award an extra life on occasion.
					if (level % 5 == 1 && frog.getLives() < 3) {
						frog.setLives(frog.getLives() + 1);
						setLivesNumber(frog.getLives());
					}
				}

				// Handles the ending of the game.
				if (frog.gameOver()) {
					// Stop game.
					stopGame();
				}
			}
		};
	}

	/**
	 * Starts the game.
	 */
	private void startGame() {
		if (HomeController.musicOn) {
			bgm.unmute();
			bgm.play();
		}
		createTimer();
		timer.start();
		world.start();
	}

	/**
	 * Pauses the game.
	 */
	private void pauseGame() {
		if (HomeController.musicOn) {
			bgm.pause();
		}
		timer.stop();
		frog.toggleNoMove();
		world.stop();
	}

	/**
	 * Resumes the game.
	 */
	private void resumeGame() {
		if (HomeController.musicOn) {
			bgm.play();
		}
		timer.start();
		frog.toggleNoMove();
		world.resume();
	}

	/**
	 * Stops the game.
	 * Checks for high scores and switches to the respective view on demand.
	 */
	private void stopGame() {
		if (HomeController.musicOn) {
			bgm.stop();
		}
		timer.stop();
		frog.toggleNoMove();
		world.stop();

		// Check for high score.
		score = frog.getScore();
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

	/**
	 * Displays the number of lives with corresponding number of pictures.
	 *
	 * @param lives the number of lives to be set as an integer.
	 */
	private void setLivesNumber(int lives) {
		for (int i = 0; i < 3; i++) {
			lifeBox.getChildren().get(i).setVisible(i + 1 <= lives);
		}
	}

	/**
	 * Displays the score with pictures.
	 *
	 * @param score the score to be set as an integer.
	 */
	private void setScoreNumber(int score) {
		// Clear digits.
		for (Digit d : world.getObjects(Digit.class)) {
			world.remove(d);
		}
		// Set new digits.
		if (score == 0) {
			world.add(new Digit(0, 30, 500, 25));
		} else {
			int shift = 0;
			while (score > 0) {
				int k = score - (score / 10) * 10;
				world.add(new Digit(k, 30, 500 - shift, 25));
				shift += 25;
				score /= 10;
			}
		}
	}

	/**
	 * Handles the speeding up of moving elements and the introduction of challenging elements on level up.
	 */
	private void levelUp() {
		level++;
		switch (level) {
			case 2:
				// Speed up.
				for (MovingActor a : world.getObjects(MovingActor.class)) {
					if (a.getY() == 166 || a.getY() == 217 || a.getY() == 329 || a.getY() == 376 || a.getY() == 540) {
						a.setSpeed(a.getSpeed() * 1.2);
					}
				}
				// Add crocodile head and fly into slots.
				specialSlots = true;
				break;
			case 3:
				// Speed up.
				for (MovingActor a : world.getObjects(MovingActor.class)) {
					if (a.getY() == 217 || a.getY() == 276 || a.getY() == 329 || a.getY() == 376 || a.getY() == 540 || a.getY() == 649) {
						a.setSpeed(a.getSpeed() * 1.2);
					}
				}
				// Add snake at the middle.
				world.add(new Snake(80, -100, 440, 2));
				break;
			case 4:
				// Speed up.
				for (MovingActor a : world.getObjects(MovingActor.class)) {
					if (a.getY() == 166 || a.getY() == 329 || a.getY() == 376 || a.getY() == 440 || a.getY() == 490 || a.getY() == 540 || a.getY() == 597 || a.getY() == 649) {
						a.setSpeed(a.getSpeed() * 1.2);
					}
				}
				// Replace some logs with crocodiles.
				Log oldLog1 = world.getObjects(Log.class).get(0);
				world.add(new Crocodile(150, (int) oldLog1.getX(), (int) oldLog1.getY(), oldLog1.getSpeed()));
				world.remove(oldLog1);
				Log oldLog2 = world.getObjects(Log.class).get(5);
				world.add(new Crocodile(150, (int) oldLog2.getX(), (int) oldLog2.getY(), oldLog2.getSpeed()));
				world.remove(oldLog2);
				frog.toFront();
				break;
			case 5:
				// Speed up.
				for (MovingActor a : world.getObjects(MovingActor.class)) {
					if (a.getY() == 166 || a.getY() == 217 || a.getY() == 276 || a.getY() == 329 || a.getY() == 376 || a.getY() == 440 || a.getY() == 540 || a.getY() == 597 || a.getY() == 649) {
						a.setSpeed(a.getSpeed() * 1.2);
					}
				}
				// Add snakes on logs.
				logSnakes = true;
				break;
		}
	}

	/**
	 * Toggles the easter egg on or off.
	 */
	private void toggleEasterEgg() {
		easterEggOn = !easterEggOn;
		if (easterEggOn) {
			// Turn easter egg on.
			background.setImage(new Image("file:src/main/resources/images/game_background_snow.png", 600, 600, true, true));
			snow.setOpacity(100);
			snow.toFront();
		} else {
			// Turn easter egg off.
			background.setImage(new Image("file:src/main/resources/images/game_background.png", 600, 600, true, true));
			snow.setOpacity(0);
		}
	}
}

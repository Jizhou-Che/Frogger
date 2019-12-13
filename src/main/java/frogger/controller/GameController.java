package frogger.controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import frogger.Main;
import frogger.model.*;

import java.util.List;

/**
 * Controls behaviours and handles events on the Game view.
 */
public class GameController {
	@FXML
	private World world;
	@FXML
	private Frog frog;
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

	static int score;
	private int lives = 3;
	private int level = 1;
	private boolean gamePaused = false;
	private boolean musicMuted = false;
	private boolean keyHold = false; // Whether the jump key is held.
	private char keyPressed; // The pressed jump key character.
	private double progressY = 800; // The furthest position reached in the current life. Used for points calculation.
	private int slotsOccupied = 0; // The number of occupied slots.
	private int timeFrame = 0; // The flag for periodic enabling of special features.
	private boolean specialSlots = false; // Whether crocodile heads and flies can appear in slots.
	private int numSpecialSlots = 0; // The number of special slots.
	private boolean logSnakes = false; // Whether log snakes can appear on logs.
	private int easterEgg = 0; // The flag for enabling the easter egg.
	private boolean easterEggOn = false; // Whether the easter egg is enabled.
	private AnimationTimer timer;
	private BackgroundMusic bgm = BackgroundMusic.getBgm();
	private Image backgroundNormal = new Image("file:src/main/resources/images/game_background.png", 600, 600, true, true);
	private Image backgroundSnow = new Image("file:src/main/resources/images/game_background_snow.png", 600, 600, true, true);

	/**
	 * Initialises the game view.
	 */
	@FXML
	private void initialize() {
		// Initialise score.
		setScoreNumber(0);
		// Start game.
		startGame();
	}

	/**
	 * Handles key-pressed events on the game view.
	 * This includes a part of movement controls of the frog, the pausing or muting of the game, and the activation of the easter egg.
	 *
	 * @param keyEvent the key-pressed event.
	 */
	@FXML
	public void keyPressed(KeyEvent keyEvent) {
		switch (keyEvent.getCode()) {
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
			case W:
				if (frog.checkMovable()) {
					if (keyHold) {
						keyReleased();
					} else {
						frog.moveUp(true);
						keyPressed = 'W';
						keyHold = true;
					}
				}
				break;
			case A:
				if (frog.checkMovable()) {
					if (keyHold) {
						keyReleased();
					} else {
						frog.moveLeft(true);
						keyPressed = 'A';
						keyHold = true;
					}
				}
				break;
			case D:
				if (frog.checkMovable()) {
					if (keyHold) {
						keyReleased();
					} else {
						frog.moveRight(true);
						keyPressed = 'D';
						keyHold = true;
					}
				}
				break;
			case S:
				if (frog.checkMovable()) {
					if (keyHold) {
						keyReleased();
					} else {
						frog.moveDown(true);
						keyPressed = 'S';
						keyHold = true;
					}
				}
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
	 * Handles key-released events on the game view.
	 * This includes a part of movement controls of the frog.
	 */
	@FXML
	public void keyReleased() {
		if (frog.checkMovable() && keyHold) {
			if (keyPressed == 'W') {
				frog.moveUp(false);
				keyHold = false;
				if (frog.getY() < progressY) {
					// A further reach in the current life. 10 points awarded.
					progressY = frog.getY();
					score += 10;
					setScoreNumber(score);
					// Hide the message on score change.
					message.setVisible(false);
				}
			} else if (keyPressed == 'A') {
				frog.moveLeft(false);
				keyHold = false;
			} else if (keyPressed == 'S') {
				frog.moveDown(false);
				keyHold = false;
			} else if (keyPressed == 'D') {
				frog.moveRight(false);
				keyHold = false;
			}
		}
	}

	/**
	 * Creates the timer for checking events consecutively.
	 * The events include the action of all elements, the check of frog conditions, the addition of random elements, the completion of a level, and the ending of the game.
	 */
	private void createTimer() {
		timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				timeFrame++;

				// Act all actors in the game world.
				List<Actor> actors = world.getObjects(Actor.class);
				for (Actor anActor : actors) {
					anActor.act(now);
				}

				// Check for goal condition.
				if (frog.checkGoal()) {
					goalReset();
				}

				// Check for death condition.
				if (frog.checkDeath()) {
					deathReset();
				}

				// Set the score if applicable.
				if (frog.checkBonus()) {
					score += 25;
					setScoreNumber(score);
				}

				// Make a random special slot periodically.
				if (specialSlots && timeFrame % 500 == 0) {
					if (numSpecialSlots == 0) {
						// Choose a random slot.
						Slot randomSlot = world.getObjects(Slot.class).get((int) (Math.random() * 5));
						if (randomSlot.getStatus() == 0) {
							// Make a random special slot if the chosen slot is empty.
							if ((int) (Math.random() * 2) == 0) {
								randomSlot.setStatus(2);
							} else {
								randomSlot.setStatus(3);
							}
						}
						numSpecialSlots++;
					} else {
						// Remove the special slot if there is already one.
						for (Slot s : world.getObjects(Slot.class)) {
							if (s.getStatus() >= 2) {
								s.setStatus(0);
							}
						}
						numSpecialSlots--;
					}
				}

				// Put a log snake on a random log periodically.
				if (logSnakes && timeFrame % 750 == 0) {
					world.add(new LogSnake(world.getObjects(Log.class).get((int) (Math.random() * world.getObjects(Log.class).size())), 0.5 - (int) (Math.random() * 2)));
					frog.toFront();
				}

				// Check the completion of a level.
				if (slotsOccupied == 5) {
					// Level up.
					levelUp();
				}

				// Check the ending of the game.
				if (lives == 0) {
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
	}

	/**
	 * Pauses the game.
	 */
	private void pauseGame() {
		if (HomeController.musicOn) {
			bgm.pause();
		}
		timer.stop();
		frog.toggleMovable();
	}

	/**
	 * Resumes the game.
	 */
	private void resumeGame() {
		if (HomeController.musicOn) {
			bgm.play();
		}
		timer.start();
		frog.toggleMovable();
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
		frog.toggleMovable();

		// Check for high score.
		try {
			Record records = new Record();
			if (records.size() < 10 || score > records.getScore(9)) {
				// Go to high score.
				Main.switchScene(2);
			} else {
				// Go to leaderboard.
				Main.switchScene(3);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Resets death associated data.
	 */
	private void deathReset() {
		// Decrement number of lives.
		lives--;
		setLivesNumber(lives);
		// Update score.
		if (score >= 50) {
			score -= 50;
			setScoreNumber(score);
		}
		// Reset key-hold status.
		keyHold = false;
	}

	/**
	 * Resets goal associated data.
	 */
	private void goalReset() {
		slotsOccupied++;
		score += 50;
		setScoreNumber(score);
		progressY = 800;
		// Reset key-hold status.
		keyHold = false;
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
	 * Handles the level up.
	 * This includes the reset of slots, the speeding up of moving elements, the introduction of challenging elements and the display of message.
	 */
	private void levelUp() {
		// Clear slots.
		slotsOccupied = 0;
		for (Actor a : world.getObjects(Slot.class)) {
			Slot s = (Slot) a;
			s.setStatus(0);
		}

		// Level up.
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

		// Display message.
		levelText.setText("LEVEL-" + level);
		message.setVisible(true);

		// Award an extra life on occasion.
		if (level % 5 == 1 && lives < 3) {
			lives++;
			setLivesNumber(lives);
		}
	}

	/**
	 * Toggles the easter egg on or off.
	 */
	private void toggleEasterEgg() {
		easterEggOn = !easterEggOn;
		if (easterEggOn) {
			// Turn easter egg on.
			background.setImage(backgroundSnow);
			snow.setOpacity(100);
			snow.toFront();
		} else {
			// Turn easter egg off.
			background.setImage(backgroundNormal);
			snow.setOpacity(0);
		}
	}
}

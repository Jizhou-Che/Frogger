package scyjc1.frogger;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Frog extends Actor {
	private int imgSize = 40;
	private int points = 0;
	private double movementX = 10.666666 * 2;
	private double movementY = 13.333333 * 2;
	private int slotsOccupied = 0; // The number of occupied slots.
	private boolean keyHold = false; // Whether the jump key is held.
	private boolean noMove = false; // Whether the frog is forbidden to move.
	private boolean carDeath = false; // Whether the frog is dead because of a car.
	private boolean waterDeath = false; // Whether the frog is dead because of water.
	private int deathAnimationFlag = 0; // The stage of death animation.
	private boolean changeScore = false; // Whether the score should change in this frame.
	private double progressY = 800; // The furthest position reached in the current life. Used for points calculation.

	private Image imgUp = new Image("file:resources/froggerUp.png", imgSize, imgSize, true, true);
	private Image imgLeft = new Image("file:resources/froggerLeft.png", imgSize, imgSize, true, true);
	private Image imgDown = new Image("file:resources/froggerDown.png", imgSize, imgSize, true, true);
	private Image imgRight = new Image("file:resources/froggerRight.png", imgSize, imgSize, true, true);
	private Image imgUpJump = new Image("file:resources/froggerUpJump.png", imgSize, imgSize, true, true);
	private Image imgLeftJump = new Image("file:resources/froggerLeftJump.png", imgSize, imgSize, true, true);
	private Image imgDownJump = new Image("file:resources/froggerDownJump.png", imgSize, imgSize, true, true);
	private Image imgRightJump = new Image("file:resources/froggerRightJump.png", imgSize, imgSize, true, true);

	Frog(String imageLink) {
		// Initialise image and position.
		setImage(new Image(imageLink, imgSize, imgSize, true, true));
		setX(300);
		setY(705);

		// Set key handlers.
		setOnKeyPressed(this::handleKeyPressed);
		setOnKeyReleased(this::handleKeyReleased);
	}

	private void handleKeyPressed(KeyEvent event) {
		if (!noMove) {
			if (keyHold) {
				handleKeyReleased(event);
				keyHold = false; // Reset key hold status.
			} else if (event.getCode() == KeyCode.W) {
				move(0, -movementY);
				setImage(imgUpJump);
				keyHold = true;
			} else if (event.getCode() == KeyCode.A) {
				move(-movementX, 0);
				setImage(imgLeftJump);
				keyHold = true;
			} else if (event.getCode() == KeyCode.S) {
				move(0, movementY);
				setImage(imgDownJump);
				keyHold = true;
			} else if (event.getCode() == KeyCode.D) {
				move(movementX, 0);
				setImage(imgRightJump);
				keyHold = true;
			}
		}
	}

	private void handleKeyReleased(KeyEvent event) {
		if (!noMove) {
			if (event.getCode() == KeyCode.W) {
				if (getY() < progressY) {
					// A further reach in the current life. 10 points awarded.
					progressY = getY();
					points += 10;
					changeScore = true;
				}
				move(0, -movementY);
				setImage(imgUp);
				keyHold = false;
			} else if (event.getCode() == KeyCode.A) {
				move(-movementX, 0);
				setImage(imgLeft);
				keyHold = false;
			} else if (event.getCode() == KeyCode.S) {
				move(0, movementY);
				setImage(imgDown);
				keyHold = false;
			} else if (event.getCode() == KeyCode.D) {
				move(movementX, 0);
				setImage(imgRight);
				keyHold = false;
			}
		}
	}

	@Override
	public void act(long now) {
		if (getY() > 734) {
			// Frog exceeds lower boundary.
			setY(705);
		}

		if (getX() < 0) {
			// Frog exceeds left boundary.
			setX(0);
		}

		if (getX() > 600 - imgSize) {
			// Frog exceeds right boundary.
			setX(600 - imgSize);
		}

		if (carDeath) {
			// Frog crashed by car.
			if ((now) % 11 == 0) {
				deathAnimationFlag++;
			}
			if (deathAnimationFlag == 1) {
				setImage(new Image("file:resources/cardeath1.png", imgSize, imgSize, true, true));
			}
			if (deathAnimationFlag == 2) {
				setImage(new Image("file:resources/cardeath2.png", imgSize, imgSize, true, true));
			}
			if (deathAnimationFlag == 3) {
				setImage(new Image("file:resources/cardeath3.png", imgSize, imgSize, true, true));
			}
			if (deathAnimationFlag == 4) {
				deathReset();
			}
		}

		if (waterDeath) {
			// Frog falls in water.
			if ((now) % 11 == 0) {
				deathAnimationFlag++;
			}
			if (deathAnimationFlag == 1) {
				setImage(new Image("file:resources/waterdeath1.png", imgSize, imgSize, true, true));
			}
			if (deathAnimationFlag == 2) {
				setImage(new Image("file:resources/waterdeath2.png", imgSize, imgSize, true, true));
			}
			if (deathAnimationFlag == 3) {
				setImage(new Image("file:resources/waterdeath3.png", imgSize, imgSize, true, true));
			}
			if (deathAnimationFlag == 4) {
				setImage(new Image("file:resources/waterdeath4.png", imgSize, imgSize, true, true));
			}
			if (deathAnimationFlag == 5) {
				deathReset();
			}
		}

		if (!noMove) {
			if (getIntersectingObjects(Obstacle.class).size() >= 1) {
				// Frog crashed by a car.
				carDeath = true;
				noMove = true; // Disable moving.
			} else if (getIntersectingObjects(Log.class).size() >= 1) {
				// Frog lands on a log.
				Log currentLog = getIntersectingObjects(Log.class).get(0);
				// Frog follows the log.
				move(currentLog.speed, 0);
			} else if (getIntersectingObjects(Turtle.class).size() >= 1) {
				// Frog lands on a turtle.
				Turtle currentTurtle = getIntersectingObjects(Turtle.class).get(0);
				// Frog follows the turtle.
				move(currentTurtle.speed, 0);
			} else if (getIntersectingObjects(WetTurtle.class).size() >= 1) {
				// Frog lands on a wet turtle.
				WetTurtle currentWetTurtle = getIntersectingObjects(WetTurtle.class).get(0);
				if (currentWetTurtle.isSunk()) {
					// Frog dies when the wet turtle sinks.
					waterDeath = true;
					noMove = true; // Disable moving.
				} else {
					// Frog follows the wet turtle.
					move(currentWetTurtle.speed, 0);
				}
			} else if (getIntersectingObjects(Slot.class).size() >= 1) {
				// Frog reaches a slot.
				if (getIntersectingObjects(Slot.class).get(0).isOccupied()) {
					deathReset();
				} else {
					getIntersectingObjects(Slot.class).get(0).setOccupied();
					slotsOccupied++;
					points += 50;
					changeScore = true;
					progressY = 800;
					setX(300);
					setY(705);
				}
			} else if (getY() < 413) {
				// Frog enters the river but lands on nothing.
				waterDeath = true;
				noMove = true; // Disable moving.
			}
		}
	}

	private void deathReset() {
		// Clear death flags.
		carDeath = waterDeath = false;
		deathAnimationFlag = 0;
		noMove = false;
		// Reset frog image and position.
		setImage(new Image("file:resources/froggerUp.png", imgSize, imgSize, true, true));
		setX(300);
		setY(705);
		// Update score.
		if (points > 50) {
			points -= 50;
			changeScore = true;
		}
	}

	/**
	 * @return boolean indicating whether all slots are occupied.
	 */
	boolean gameWon() {
		return slotsOccupied == 5;
	}

	/**
	 * @return the current points as integer.
	 */
	int getPoints() {
		return points;
	}

	/**
	 * @return whether an update to the score digits is pending.
	 * <p>
	 * Resets the pending status as a side effect.
	 */
	boolean changeScore() {
		if (changeScore) {
			changeScore = false;
			return true;
		}
		return false;
	}
}

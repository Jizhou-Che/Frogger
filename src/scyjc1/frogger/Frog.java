package scyjc1.frogger;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Frog extends Actor {
	private int points = 0;
	private int slotsOccupied = 0;
	private boolean jumping = false;
	private boolean noMove = false;
	private double movementX = 10.666666 * 2;
	private double movementY = 13.3333333 * 2;
	private int imgSize = 40;
	private boolean carDeath = false;
	private boolean waterDeath = false;
	private boolean changeScore = false;
	private int deathAnimationFlag = 0;
	private double w = 800;

	Frog(String imageLink) {
		setImage(new Image(imageLink, imgSize, imgSize, true, true));
		setX(300);
		setY(705);
		Image imgW1 = new Image("file:resources/froggerUp.png", imgSize, imgSize, true, true);
		Image imgA1 = new Image("file:resources/froggerLeft.png", imgSize, imgSize, true, true);
		Image imgS1 = new Image("file:resources/froggerDown.png", imgSize, imgSize, true, true);
		Image imgD1 = new Image("file:resources/froggerRight.png", imgSize, imgSize, true, true);
		Image imgW2 = new Image("file:resources/froggerUpJump.png", imgSize, imgSize, true, true);
		Image imgA2 = new Image("file:resources/froggerLeftJump.png", imgSize, imgSize, true, true);
		Image imgS2 = new Image("file:resources/froggerDownJump.png", imgSize, imgSize, true, true);
		Image imgD2 = new Image("file:resources/froggerRightJump.png", imgSize, imgSize, true, true);
		setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (!noMove) {
					if (jumping) {
						if (event.getCode() == KeyCode.W) {
							move(0, -movementY);
							changeScore = false;
							setImage(imgW1);
							jumping = false;
						} else if (event.getCode() == KeyCode.A) {
							move(-movementX, 0);
							setImage(imgA1);
							jumping = false;
						} else if (event.getCode() == KeyCode.S) {
							move(0, movementY);
							setImage(imgS1);
							jumping = false;
						} else if (event.getCode() == KeyCode.D) {
							move(movementX, 0);
							setImage(imgD1);
							jumping = false;
						}
					} else if (event.getCode() == KeyCode.W) {
						move(0, -movementY);
						setImage(imgW2);
						jumping = true;
					} else if (event.getCode() == KeyCode.A) {
						move(-movementX, 0);
						setImage(imgA2);
						jumping = true;
					} else if (event.getCode() == KeyCode.S) {
						move(0, movementY);
						setImage(imgS2);
						jumping = true;
					} else if (event.getCode() == KeyCode.D) {
						move(movementX, 0);
						setImage(imgD2);
						jumping = true;
					}
				}
			}
		});

		setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (!noMove) {
					if (event.getCode() == KeyCode.W) {
						if (getY() < w) {
							changeScore = true;
							w = getY();
							points += 10;
						}
						move(0, -movementY);
						setImage(imgW1);
						jumping = false;
					} else if (event.getCode() == KeyCode.A) {
						move(-movementX, 0);
						setImage(imgA1);
						jumping = false;
					} else if (event.getCode() == KeyCode.S) {
						move(0, movementY);
						setImage(imgS1);
						jumping = false;
					} else if (event.getCode() == KeyCode.D) {
						move(movementX, 0);
						setImage(imgD1);
						jumping = false;
					}
				}
			}
		});
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

		if (getX() > 600) {
			// Frog exceeds right boundary.
			setX(600 - imgSize);
		}

		if (carDeath) {
			noMove = true;
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
				// Animation finished, set position back to origin.
				deathReset();
			}
		}

		if (waterDeath) {
			noMove = true;
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

		if (getIntersectingObjects(Obstacle.class).size() >= 1) {
			// Frog crashed by a car.
			carDeath = true;
		}

		if (getIntersectingObjects(Log.class).size() >= 1 && !noMove) {
			// Frog lands on a log.
			Log currentLog = getIntersectingObjects(Log.class).get(0);
			// Frog follows the log.
			move(currentLog.speed, 0);
		} else if (getIntersectingObjects(Turtle.class).size() >= 1 && !noMove) {
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
			} else {
				// Frog follows the wet turtle.
				move(currentWetTurtle.speed, 0);
			}
		} else if (getIntersectingObjects(Slot.class).size() >= 1) {
			// Frog reaches a slot.
			if (getIntersectingObjects(Slot.class).get(0).isActivated()) {
				slotsOccupied--;
				points -= 50;
			}
			points += 50;
			changeScore = true;
			w = 800;
			getIntersectingObjects(Slot.class).get(0).setEnd();
			slotsOccupied++;
			setX(300);
			setY(705);
		} else if (getY() < 413) {
			// Frog enters the river but lands on nothing.
			waterDeath = true;
		}
	}

	private void deathReset() {
		// Set frog to original position.
		setX(300);
		setY(705);
		// Clear death flags.
		carDeath = waterDeath = false;
		deathAnimationFlag = 0;
		// Reset frog image.
		setImage(new Image("file:resources/froggerUp.png", imgSize, imgSize, true, true));
		noMove = false;
		// Modify score.
		if (points > 50) {
			points -= 50;
			changeScore = true;
		}
	}

	boolean gameWon() {
		return slotsOccupied == 5;
	}

	int getPoints() {
		return points;
	}

	boolean changeScore() {
		if (changeScore) {
			changeScore = false;
			return true;
		}
		return false;
	}
}

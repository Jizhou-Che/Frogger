package scyjc1.frogger.model;

import javafx.scene.image.Image;

public class Frog extends Actor {
	private int imgSize = 40;
	private double movementX = 10.666666 * 2;
	private double movementY = 13.333333 * 2;
	private boolean noMove = false; // Whether the frog is forbidden to move.
	private boolean carDeath = false; // Whether the frog is dead because of a car.
	private boolean waterDeath = false; // Whether the frog is dead because of water.
	private int deathAnimationFlag = 0; // The stage of death animation.
	private boolean win = false;
	private boolean death = false;
	private boolean bonus = false;

	private Image imgUp = new Image("file:src/main/resources/images/frogger_up.png", imgSize, imgSize, true, true);
	private Image imgLeft = new Image("file:src/main/resources/images/frogger_left.png", imgSize, imgSize, true, true);
	private Image imgDown = new Image("file:src/main/resources/images/frogger_down.png", imgSize, imgSize, true, true);
	private Image imgRight = new Image("file:src/main/resources/images/frogger_right.png", imgSize, imgSize, true, true);
	private Image imgUpJump = new Image("file:src/main/resources/images/frogger_up_jump.png", imgSize, imgSize, true, true);
	private Image imgLeftJump = new Image("file:src/main/resources/images/frogger_left_jump.png", imgSize, imgSize, true, true);
	private Image imgDownJump = new Image("file:src/main/resources/images/frogger_down_jump.png", imgSize, imgSize, true, true);
	private Image imgRightJump = new Image("file:src/main/resources/images/frogger_right_jump.png", imgSize, imgSize, true, true);
	private Image imgCarDeath1 = new Image("file:src/main/resources/images/car_death_animation_1.png", imgSize, imgSize, true, true);
	private Image imgCarDeath2 = new Image("file:src/main/resources/images/car_death_animation_2.png", imgSize, imgSize, true, true);
	private Image imgCarDeath3 = new Image("file:src/main/resources/images/car_death_animation_3.png", imgSize, imgSize, true, true);
	private Image imgWaterDeath1 = new Image("file:src/main/resources/images/water_death_animation_1.png", imgSize, imgSize, true, true);
	private Image imgWaterDeath2 = new Image("file:src/main/resources/images/water_death_animation_2.png", imgSize, imgSize, true, true);
	private Image imgWaterDeath3 = new Image("file:src/main/resources/images/water_death_animation_3.png", imgSize, imgSize, true, true);
	private Image imgWaterDeath4 = new Image("file:src/main/resources/images/water_death_animation_4.png", imgSize, imgSize, true, true);

	public Frog() {
		// Initialise image and position.
		setImage(imgUp);
		setX(300);
		setY(705);
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
				setImage(imgCarDeath1);
			}
			if (deathAnimationFlag == 2) {
				setImage(imgCarDeath2);
			}
			if (deathAnimationFlag == 3) {
				setImage(imgCarDeath3);
			}
			if (deathAnimationFlag == 4) {
				death = true;
				deathReset();
			}
		}

		if (waterDeath) {
			// Frog falls in water.
			if ((now) % 11 == 0) {
				deathAnimationFlag++;
			}
			if (deathAnimationFlag == 1) {
				setImage(imgWaterDeath1);
			}
			if (deathAnimationFlag == 2) {
				setImage(imgWaterDeath2);
			}
			if (deathAnimationFlag == 3) {
				setImage(imgWaterDeath3);
			}
			if (deathAnimationFlag == 4) {
				setImage(imgWaterDeath4);
			}
			if (deathAnimationFlag == 5) {
				death = true;
				deathReset();
			}
		}

		if (!noMove) {
			if (getIntersectingObjects(Obstacle.class).size() >= 1 || getIntersectingObjects(Snake.class).size() >= 1 || getIntersectingObjects(LogSnake.class).size() >= 1) {
				// Frog crashed by a car or eaten by a snake.
				carDeath = true;
				noMove = true;
			} else if (getIntersectingObjects(Log.class).size() >= 1) {
				// Frog lands on a log.
				Log currentLog = getIntersectingObjects(Log.class).get(0);
				// Frog follows the log.
				move(currentLog.getSpeed(), 0);
			} else if (getIntersectingObjects(Crocodile.class).size() >= 1) {
				// Frog lands on a crocodile.
				Crocodile currentCrocodile = getIntersectingObjects(Crocodile.class).get(0);
				if (currentCrocodile.killsFrog(getBoundsInLocal())) {
					// Frog killed by crocodile.
					carDeath = true;
					noMove = true;
				} else {
					// Frog follows the crocodile.
					move(currentCrocodile.getSpeed(), 0);
				}
			} else if (getIntersectingObjects(Turtle.class).size() >= 1) {
				// Frog lands on a turtle.
				Turtle currentTurtle = getIntersectingObjects(Turtle.class).get(0);
				// Frog follows the turtle.
				move(currentTurtle.getSpeed(), 0);
			} else if (getIntersectingObjects(WetTurtle.class).size() >= 1) {
				// Frog lands on a wet turtle.
				WetTurtle currentWetTurtle = getIntersectingObjects(WetTurtle.class).get(0);
				if (currentWetTurtle.isSunk()) {
					// Frog dies when the wet turtle sinks.
					waterDeath = true;
					noMove = true;
				} else {
					// Frog follows the wet turtle.
					move(currentWetTurtle.getSpeed(), 0);
				}
			} else if (getIntersectingObjects(Slot.class).size() >= 1) {
				// Frog reaches a slot.
				if (getIntersectingObjects(Slot.class).get(0).getStatus() == 1) {
					// Occupied slot.
					waterDeath = true;
					noMove = true;
				} else if (getIntersectingObjects(Slot.class).get(0).getStatus() == 2) {
					// Crocodile slot.
					carDeath = true;
					noMove = true;
				} else {
					if (getIntersectingObjects(Slot.class).get(0).getStatus() == 3) {
						// Fly slot.
						bonus = true;
					}
					win = true;
					winReset();
				}
			} else if (getY() < 413) {
				// Frog enters the river but lands on nothing.
				waterDeath = true;
				noMove = true;
			}
		}
	}

	public void winReset() {
		getIntersectingObjects(Slot.class).get(0).setOccupied();
		setX(300);
		setY(705);
	}

	/**
	 * Resets death associated data.
	 */
	public void deathReset() {
		// Reset frog image and position.
		setImage(imgUp);
		setX(300);
		setY(705);
		// Clear death flags.
		carDeath = waterDeath = false;
		deathAnimationFlag = 0;
		noMove = false;
	}

	public boolean getBonus() {
		if (bonus) {
			bonus = false;
			return true;
		} else {
			return false;
		}
	}

	public void moveUp(boolean jumping) {
		move(0, -movementY);
		if (jumping) {
			setImage(imgUpJump);
		} else {
			setImage(imgUp);
		}
	}

	public void moveLeft(boolean jumping) {
		move(-movementX, 0);
		if (jumping) {
			setImage(imgLeftJump);
		} else {
			setImage(imgLeft);
		}
	}

	public void moveDown(boolean jumping) {
		move(0, movementY);
		if (jumping) {
			setImage(imgDownJump);
		} else {
			setImage(imgDown);
		}
	}

	public void moveRight(boolean jumping) {
		move(movementX, 0);
		if (jumping) {
			setImage(imgRightJump);
		} else {
			setImage(imgRight);
		}
	}

	public boolean checkWin() {
		if (win) {
			win = false;
			return true;
		} else {
			return false;
		}
	}

	public boolean checkDeath() {
		if (death) {
			death = false;
			return true;
		} else {
			return false;
		}
	}

	public boolean checkNoMove() {
		return noMove;
	}

	public void toggleNoMove() {
		noMove = !noMove;
	}
}

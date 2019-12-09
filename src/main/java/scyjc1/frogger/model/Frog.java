package scyjc1.frogger.model;

import javafx.beans.NamedArg;
import javafx.scene.image.Image;

/**
 * <h1>Frog</h1>
 * <h2>Extends {@link Actor}.</h2>
 * <p>
 *     The most important element in the game.
 *     The frog can jump around and interact with other game elements, causing a possible change to the game score.
 * </p>
 */
public class Frog extends Actor {
	private Image imgUp;
	private Image imgLeft;
	private Image imgDown;
	private Image imgRight;
	private Image imgUpJump;
	private Image imgLeftJump;
	private Image imgDownJump;
	private Image imgRightJump;
	private Image imgCarDeath1;
	private Image imgCarDeath2;
	private Image imgCarDeath3;
	private Image imgWaterDeath1;
	private Image imgWaterDeath2;
	private Image imgWaterDeath3;
	private Image imgWaterDeath4;
	private int imgSize;
	private double movementX = 10.666666 * 2;
	private double movementY = 13.333333 * 2;
	private boolean movable = true; // Whether the frog is able to move.
	private boolean carDeath = false; // Whether the frog is dead because of a car.
	private boolean waterDeath = false; // Whether the frog is dead because of water.
	private int deathAnimationFlag = 0; // The stage of death animation.
	private boolean win = false;
	private boolean death = false;
	private boolean bonus = false;

	/**
	 * Initialises the Frog with image, size and position.
	 *
	 * @param size the desired width or height of the frog image, whichever is smaller, as an integer.
	 * @param x the initial x position of the frog in the game world as an integer.
	 * @param y the initial y position of the frog in the game world as an integer.
	 */
	public Frog(@NamedArg("size") int size, @NamedArg("x") int x, @NamedArg("y") int y) {
		imgUp = new Image("file:src/main/resources/images/frogger_up.png", size, size, true, true);
		imgLeft = new Image("file:src/main/resources/images/frogger_left.png", size, size, true, true);
		imgDown = new Image("file:src/main/resources/images/frogger_down.png", size, size, true, true);
		imgRight = new Image("file:src/main/resources/images/frogger_right.png", size, size, true, true);
		imgUpJump = new Image("file:src/main/resources/images/frogger_up_jump.png", size, size, true, true);
		imgLeftJump = new Image("file:src/main/resources/images/frogger_left_jump.png", size, size, true, true);
		imgDownJump = new Image("file:src/main/resources/images/frogger_down_jump.png", size, size, true, true);
		imgRightJump = new Image("file:src/main/resources/images/frogger_right_jump.png", size, size, true, true);
		imgCarDeath1 = new Image("file:src/main/resources/images/car_death_animation_1.png", size, size, true, true);
		imgCarDeath2 = new Image("file:src/main/resources/images/car_death_animation_2.png", size, size, true, true);
		imgCarDeath3 = new Image("file:src/main/resources/images/car_death_animation_3.png", size, size, true, true);
		imgWaterDeath1 = new Image("file:src/main/resources/images/water_death_animation_1.png", size, size, true, true);
		imgWaterDeath2 = new Image("file:src/main/resources/images/water_death_animation_2.png", size, size, true, true);
		imgWaterDeath3 = new Image("file:src/main/resources/images/water_death_animation_3.png", size, size, true, true);
		imgWaterDeath4 = new Image("file:src/main/resources/images/water_death_animation_4.png", size, size, true, true);
		// Initialise image and position.
		setImage(imgUp);
		setX(x);
		setY(y);
		imgSize = size;
	}

	/**
	 * Defines the behaviour of frogs.
	 * This includes the interaction with other elements, image animation, moving and position resetting on boundaries.
	 *
	 * @param now the current time.
	 */
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
				deathReset();
			}
		}

		if (movable) {
			if (getIntersectingObjects(Obstacle.class).size() >= 1 || getIntersectingObjects(Snake.class).size() >= 1 || getIntersectingObjects(LogSnake.class).size() >= 1) {
				// Frog crashed by a car or eaten by a snake.
				carDeath = true;
				movable = false;
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
					movable = false;
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
					movable = false;
				} else {
					// Frog follows the wet turtle.
					move(currentWetTurtle.getSpeed(), 0);
				}
			} else if (getIntersectingObjects(Slot.class).size() >= 1) {
				// Frog reaches a slot.
				if (getIntersectingObjects(Slot.class).get(0).getStatus() == 1) {
					// Occupied slot.
					waterDeath = true;
					movable = false;
				} else if (getIntersectingObjects(Slot.class).get(0).getStatus() == 2) {
					// Crocodile slot.
					carDeath = true;
					movable = false;
				} else {
					if (getIntersectingObjects(Slot.class).get(0).getStatus() == 3) {
						// Fly slot.
						bonus = true;
					}
					getIntersectingObjects(Slot.class).get(0).setStatus(1);
					winReset();
				}
			} else if (getY() < 413) {
				// Frog enters the river but lands on nothing.
				waterDeath = true;
				movable = false;
			}
		}
	}

	/**
	 * Resets the frog on reaching a slot.
	 */
	public void winReset() {
		win = true;
		setX(300);
		setY(705);
	}

	/**
	 * Resets the frog on death.
	 */
	public void deathReset() {
		death = true;
		// Reset frog image and position.
		setImage(imgUp);
		setX(300);
		setY(705);
		// Clear death flags.
		carDeath = waterDeath = false;
		deathAnimationFlag = 0;
		movable = true;
	}

	/**
	 * Moves the frog up.
	 *
	 * @param jumping whether the frog is in the middle of a jump as a boolean.
	 */
	public void moveUp(boolean jumping) {
		move(0, -movementY);
		if (jumping) {
			setImage(imgUpJump);
		} else {
			setImage(imgUp);
		}
	}

	/**
	 * Moves the frog left.
	 *
	 * @param jumping whether the frog is in the middle of a jump as a boolean.
	 */
	public void moveLeft(boolean jumping) {
		move(-movementX, 0);
		if (jumping) {
			setImage(imgLeftJump);
		} else {
			setImage(imgLeft);
		}
	}

	/**
	 * Moves the frog down.
	 *
	 * @param jumping whether the frog is in the middle of a jump as a boolean.
	 */
	public void moveDown(boolean jumping) {
		move(0, movementY);
		if (jumping) {
			setImage(imgDownJump);
		} else {
			setImage(imgDown);
		}
	}

	/**
	 * Moves the frog right.
	 *
	 * @param jumping whether the frog is in the middle of a jump as a boolean.
	 */
	public void moveRight(boolean jumping) {
		move(movementX, 0);
		if (jumping) {
			setImage(imgRightJump);
		} else {
			setImage(imgRight);
		}
	}

	/**
	 * Checks whether a winning event is pending.
	 *
	 * @return whether a winning event is pending as a boolean.
	 */
	public boolean checkWin() {
		if (win) {
			win = false;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks whether a death event is pending.
	 *
	 * @return whether a death event is pending as a boolean.
	 */
	public boolean checkDeath() {
		if (death) {
			death = false;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks whether a bonus event is pending.
	 *
	 * @return whether a bonus event is pending as a boolean.
	 */
	public boolean checkBonus() {
		if (bonus) {
			bonus = false;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks whether the frog is movable.
	 *
	 * @return whether the frog is movable as a boolean.
	 */
	public boolean checkMovable() {
		return movable;
	}

	/**
	 * Toggles the movable status.
	 */
	public void toggleMovable() {
		movable = !movable;
	}
}

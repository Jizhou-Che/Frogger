package frogger.model;

import javafx.beans.NamedArg;
import javafx.scene.image.Image;

/**
 * The most important element in the game.
 * The {@link Frog} can jump around and interact with other game elements, leading a possible change to the game score.
 */
public class Frog extends Actor {
	private int size = 40;
	private double movementX = 21.333333;
	private double movementY = 26.666666;
	private boolean goal = false;
	private boolean death = false;
	private boolean bonus = false;
	private boolean movable = true; // Whether the frog is able to move.
	private boolean normalDeath = false; // Whether the frog is dead because of normal reasons. Used for animation.
	private boolean waterDeath = false; // Whether the frog is dead because of water. Used for animation.
	private int deathAnimationFlag = 0; // The stage of death animation.
	private Image imgUp = new Image("file:src/main/resources/images/frogger_up.png", size, size, true, true);
	private Image imgLeft = new Image("file:src/main/resources/images/frogger_left.png", size, size, true, true);
	private Image imgDown = new Image("file:src/main/resources/images/frogger_down.png", size, size, true, true);
	private Image imgRight = new Image("file:src/main/resources/images/frogger_right.png", size, size, true, true);
	private Image imgUpJump = new Image("file:src/main/resources/images/frogger_up_jump.png", size, size, true, true);
	private Image imgLeftJump = new Image("file:src/main/resources/images/frogger_left_jump.png", size, size, true, true);
	private Image imgDownJump = new Image("file:src/main/resources/images/frogger_down_jump.png", size, size, true, true);
	private Image imgRightJump = new Image("file:src/main/resources/images/frogger_right_jump.png", size, size, true, true);
	private Image imgNormalDeath1 = new Image("file:src/main/resources/images/normal_death_animation_1.png", size, size, true, true);
	private Image imgNormalDeath2 = new Image("file:src/main/resources/images/normal_death_animation_2.png", size, size, true, true);
	private Image imgNormalDeath3 = new Image("file:src/main/resources/images/normal_death_animation_3.png", size, size, true, true);
	private Image imgWaterDeath1 = new Image("file:src/main/resources/images/water_death_animation_1.png", size, size, true, true);
	private Image imgWaterDeath2 = new Image("file:src/main/resources/images/water_death_animation_2.png", size, size, true, true);
	private Image imgWaterDeath3 = new Image("file:src/main/resources/images/water_death_animation_3.png", size, size, true, true);
	private Image imgWaterDeath4 = new Image("file:src/main/resources/images/water_death_animation_4.png", size, size, true, true);

	/**
	 * Initialises the {@link Frog} with image and position.
	 *
	 * @param x the initial x position of the {@link Frog} in the game world as an integer.
	 * @param y the initial y position of the {@link Frog} in the game world as an integer.
	 */
	public Frog(@NamedArg("x") int x, @NamedArg("y") int y) {
		setImage(imgUp);
		setX(x);
		setY(y);
	}

	/**
	 * Defines the behaviour of {@link Frog}.
	 * This includes the interaction with other elements, image animation, moving and position resetting on boundaries.
	 *
	 * @param now the timestamp of the current frame given in nanoseconds.
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

		if (getX() > 600 - size) {
			// Frog exceeds right boundary.
			setX(600 - size);
		}

		if (normalDeath) {
			// Frog died normally.
			if (now % 15 == 0) {
				deathAnimationFlag++;
			}
			if (deathAnimationFlag == 1) {
				setImage(imgNormalDeath1);
			}
			if (deathAnimationFlag == 2) {
				setImage(imgNormalDeath2);
			}
			if (deathAnimationFlag == 3) {
				setImage(imgNormalDeath3);
			}
			if (deathAnimationFlag == 4) {
				deathReset();
			}
		}

		if (waterDeath) {
			// Frog died in water.
			if (now % 12 == 0) {
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
				normalDeath = true;
				movable = false;
			} else if (getIntersectingObjects(Log.class).size() >= 1) {
				// Frog lands on a log.
				Log currentLog = getIntersectingObjects(Log.class).get(0);
				// Frog follows the log.
				move(currentLog.getSpeed(), 0);
			} else if (getIntersectingObjects(Crocodile.class).size() >= 1) {
				// Frog lands on a crocodile.
				Crocodile currentCrocodile = getIntersectingObjects(Crocodile.class).get(0);
				if (currentCrocodile.killsFrog(this)) {
					// Frog killed by crocodile.
					normalDeath = true;
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
					normalDeath = true;
					movable = false;
				} else {
					if (getIntersectingObjects(Slot.class).get(0).getStatus() == 3) {
						// Fly slot.
						bonus = true;
					}
					getIntersectingObjects(Slot.class).get(0).setStatus(1);
					goalReset();
				}
			} else if (getY() < 413) {
				// Frog enters the river but lands on nothing.
				waterDeath = true;
				movable = false;
			}
		}
	}

	/**
	 * Resets the {@link Frog} on reaching a slot.
	 */
	public void goalReset() {
		goal = true;
		setX(300);
		setY(705);
	}

	/**
	 * Resets the {@link Frog} on death.
	 */
	public void deathReset() {
		death = true;
		// Reset frog image and position.
		setImage(imgUp);
		setX(300);
		setY(705);
		// Clear death flags.
		normalDeath = waterDeath = false;
		deathAnimationFlag = 0;
		movable = true;
	}

	/**
	 * Moves the {@link Frog} up.
	 *
	 * @param jumping whether the {@link Frog} is in the middle of a jump as a boolean.
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
	 * Moves the {@link Frog} left.
	 *
	 * @param jumping whether the {@link Frog} is in the middle of a jump as a boolean.
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
	 * Moves the {@link Frog} down.
	 *
	 * @param jumping whether the {@link Frog} is in the middle of a jump as a boolean.
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
	 * Moves the {@link Frog} right.
	 *
	 * @param jumping whether the {@link Frog} is in the middle of a jump as a boolean.
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
	 * Checks whether a goal event is pending.
	 *
	 * @return whether a goal event is pending as a boolean.
	 */
	public boolean checkGoal() {
		if (goal) {
			goal = false;
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
	 * Checks whether the {@link Frog} is movable.
	 *
	 * @return whether the {@link Frog} is movable as a boolean.
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

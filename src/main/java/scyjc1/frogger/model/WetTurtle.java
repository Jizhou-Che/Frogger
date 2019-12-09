package scyjc1.frogger.model;

import javafx.beans.NamedArg;
import javafx.scene.image.Image;

/**
 * <h1>WetTurtle</h1>
 * <h2>Extends {@link MovingActor}.</h2>
 * <p>
 *     A moving element in the game.
 *     The frog can land on it and follow its movement only when it is not sunk.
 * </p>
 */
public class WetTurtle extends MovingActor {
	private Image wetTurtle1;
	private Image wetTurtle2;
	private Image wetTurtle3;
	private Image wetTurtle4;
	private double speed;
	private boolean isSunk = false;

	/**
	 * Initialises the WetTurtle with image, position and speed.
	 *
	 * @param size the desired width or height of the wet turtle image, whichever is smaller, as an integer.
	 * @param x the initial x position of the WetTurtle as an integer.
	 * @param y the initial y position of the WetTurtle as an integer.
	 * @param speed the speed of the WetTurtle as a double.
	 */
	public WetTurtle(@NamedArg("size") int size, @NamedArg("x") int x, @NamedArg("y") int y, @NamedArg("speed") double speed) {
		wetTurtle1 = new Image("file:src/main/resources/images/turtle_animation_1.png", size, size, true, true);
		wetTurtle2 = new Image("file:src/main/resources/images/wet_turtle_animation_1.png", size, size, true, true);
		wetTurtle3 = new Image("file:src/main/resources/images/wet_turtle_animation_2.png", size, size, true, true);
		wetTurtle4 = new Image("file:src/main/resources/images/wet_turtle_animation_3.png", size, size, true, true);
		setImage(wetTurtle2);
		setX(x);
		setY(y);
		setSpeed(speed);
	}

	/**
	 * Defines the behaviour of WetTurtle.
	 * This includes moving and position resetting on boundaries.
	 *
	 * @param now the current time.
	 */
	@Override
	public void act(long now) {
		if (now / 900000000 % 4 == 0) {
			setImage(wetTurtle2);
			isSunk = false;
		} else if (now / 900000000 % 4 == 1) {
			setImage(wetTurtle1);
			isSunk = false;
		} else if (now / 900000000 % 4 == 2) {
			setImage(wetTurtle3);
			isSunk = false;
		} else if (now / 900000000 % 4 == 3) {
			setImage(wetTurtle4);
			isSunk = true;
		}

		move(speed, 0);

		if (getX() > 600 && speed > 0) {
			setX(-200);
		}
		if (getX() < -150 && speed < 0) {
			setX(600);
		}
	}

	/**
	 * Gets the speed of the WetTurtle.
	 *
	 * @return the speed of the WetTurtle as a double.
	 */
	@Override
	public double getSpeed() {
		return speed;
	}

	/**
	 * Sets the speed of the WetTurtle.
	 *
	 * @param speed the desired speed of the WetTurtle as a double.
	 */
	@Override
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	/**
	 * Checks whether the WetTurtle is currently sunk.
	 *
	 * @return whether the WetTurtle is sunk as a boolean.
	 */
	public boolean isSunk() {
		return isSunk;
	}
}

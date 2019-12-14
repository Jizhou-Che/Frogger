package frogger.model;

import javafx.beans.NamedArg;
import javafx.scene.image.Image;

/**
 * A moving element in the game.
 * The {@link Frog} can land on it and then follow its movement.
 */
public class Log extends MovingActor {
	private double speed;

	/**
	 * Initialises the {@link Log} with image, size, position and speed.
	 *
	 * @param type  the type of the {@link Log} as an integer.
	 * @param size  the desired width or height of the {@link Log}, whichever is smaller, as an integer.
	 * @param x     the initial x position of the {@link Log} in the game world as an integer.
	 * @param y     the initial y position of the {@link Log} in the game world as an integer.
	 * @param speed the speed of the {@link Log} as a double.
	 */
	public Log(@NamedArg("type") int type, @NamedArg("size") int size, @NamedArg("x") int x, @NamedArg("y") int y, @NamedArg("speed") double speed) {
		switch (type) {
			case 1:
				setImage(new Image("file:src/main/resources/images/log_1.png", size, size, true, true));
				break;
			case 2:
				setImage(new Image("file:src/main/resources/images/log_2.png", size, size, true, true));
				break;
			case 3:
				setImage(new Image("file:src/main/resources/images/log_3.png", size, size, true, true));
				break;
		}
		setX(x);
		setY(y);
		setSpeed(speed);
	}

	/**
	 * Defines the behaviour of {@link Log}.
	 * This includes moving and position resetting on boundaries.
	 *
	 * @param now the timestamp of the current frame given in nanoseconds.
	 */
	@Override
	public void act(long now) {
		move(speed, 0);
		if (getX() > 600 && speed > 0) {
			// Going right, exceeds boundary.
			setX(-180);
		}
		if (getX() < -300 && speed < 0) {
			// Going left, exceeds boundary.
			setX(700);
		}
	}

	/**
	 * Gets the speed of the {@link Log}.
	 *
	 * @return the speed of the {@link Log} as a double.
	 */
	@Override
	public double getSpeed() {
		return speed;
	}

	/**
	 * Sets the speed of the {@link Log}.
	 *
	 * @param speed the desired speed of the {@link Log} as a double.
	 */
	@Override
	public void setSpeed(double speed) {
		this.speed = speed;
	}
}

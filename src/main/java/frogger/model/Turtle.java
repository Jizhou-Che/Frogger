package frogger.model;

import javafx.beans.NamedArg;
import javafx.scene.image.Image;

/**
 * A moving element in the game.
 * The {@link Frog} can land on it and then follow its movement.
 */
public class Turtle extends MovingActor {
	private double speed;
	private Image turtle1;
	private Image turtle2;
	private Image turtle3;

	/**
	 * Initialises the {@link Turtle} with image, position and speed.
	 *
	 * @param size  the desired width or height of the {@link Turtle}, whichever is smaller, as an integer.
	 * @param x     the initial x position of the {@link Turtle} as an integer.
	 * @param y     the initial y position of the {@link Turtle} as an integer.
	 * @param speed the speed of the {@link Turtle} as a double.
	 */
	public Turtle(@NamedArg("size") int size, @NamedArg("x") int x, @NamedArg("y") int y, @NamedArg("speed") double speed) {
		turtle1 = new Image("file:src/main/resources/images/turtle_animation_1.png", size, size, true, true);
		turtle2 = new Image("file:src/main/resources/images/turtle_animation_2.png", size, size, true, true);
		turtle3 = new Image("file:src/main/resources/images/turtle_animation_3.png", size, size, true, true);
		setImage(turtle1);
		setX(x);
		setY(y);
		setSpeed(speed);
	}

	/**
	 * Defines the behaviour of {@link Turtle}.
	 * This includes moving and position resetting on boundaries.
	 *
	 * @param now the timestamp of the current frame given in nanoseconds.
	 */
	@Override
	public void act(long now) {
		if (now / 900000000 % 3 == 0) {
			setImage(turtle2);
		} else if (now / 900000000 % 3 == 1) {
			setImage(turtle1);
		} else if (now / 900000000 % 3 == 2) {
			setImage(turtle3);
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
	 * Gets the speed of the {@link Turtle}.
	 *
	 * @return the speed of the {@link Turtle} as a double.
	 */
	@Override
	public double getSpeed() {
		return speed;
	}

	/**
	 * Sets the speed of the {@link Turtle}.
	 *
	 * @param speed the desired speed of the {@link Turtle} as a double.
	 */
	@Override
	public void setSpeed(double speed) {
		this.speed = speed;
	}
}

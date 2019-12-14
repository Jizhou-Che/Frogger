package frogger.model;

import javafx.beans.NamedArg;
import javafx.scene.image.Image;

/**
 * A moving element in the game.
 * The {@link Frog} gets killed when they meet.
 */
public class Obstacle extends MovingActor {
	private double speed;

	/**
	 * Initialises the {@link Obstacle} with image, size, position and speed.
	 *
	 * @param type  the type of the {@link Obstacle} as an integer.
	 * @param size  the desired width or height of the {@link Obstacle}, whichever is smaller, as an integer.
	 * @param x     the initial x position of the {@link Obstacle} in the game world as an integer.
	 * @param y     the initial y position of the {@link Obstacle} in the game world as an integer.
	 * @param speed the speed of the {@link Obstacle} as a double.
	 */
	public Obstacle(@NamedArg("type") int type, @NamedArg("size") int size, @NamedArg("x") int x, @NamedArg("y") int y, @NamedArg("speed") double speed) {
		switch (type) {
			case 1:
				setImage(new Image("file:src/main/resources/images/car_1_left.png", size, size, true, true));
				break;
			case 2:
				setImage(new Image("file:src/main/resources/images/car_1_right.png", size, size, true, true));
				break;
			case 3:
				setImage(new Image("file:src/main/resources/images/car_2_left.png", size, size, true, true));
				break;
			case 4:
				setImage(new Image("file:src/main/resources/images/car_2_right.png", size, size, true, true));
				break;
			case 5:
				setImage(new Image("file:src/main/resources/images/van_1_left.png", size, size, true, true));
				break;
			case 6:
				setImage(new Image("file:src/main/resources/images/van_1_right.png", size, size, true, true));
				break;
			case 7:
				setImage(new Image("file:src/main/resources/images/truck_1_left.png", size, size, true, true));
				break;
			case 8:
				setImage(new Image("file:src/main/resources/images/truck_1_right.png", size, size, true, true));
				break;
			case 9:
				setImage(new Image("file:src/main/resources/images/truck_2_left.png", size, size, true, true));
				break;
			case 10:
				setImage(new Image("file:src/main/resources/images/truck_2_right.png", size, size, true, true));
				break;
		}
		setX(x);
		setY(y);
		setSpeed(speed);
	}

	/**
	 * Defines the behaviour of {@link Obstacle}.
	 * This includes moving and position resetting on boundaries.
	 *
	 * @param now the timestamp of the current frame given in nanoseconds.
	 */
	@Override
	public void act(long now) {
		move(speed, 0);
		if (getX() > 600 && speed > 0) {
			setX(-200);
		}
		if (getX() < -50 && speed < 0) {
			setX(600);
		}
	}

	/**
	 * Gets the speed of the {@link Obstacle}.
	 *
	 * @return the speed of the {@link Obstacle} as a double.
	 */
	@Override
	public double getSpeed() {
		return speed;
	}

	/**
	 * Sets the speed of the {@link Obstacle}.
	 *
	 * @param speed the desired speed of the {@link Obstacle} as a double.
	 */
	@Override
	public void setSpeed(double speed) {
		this.speed = speed;
	}
}

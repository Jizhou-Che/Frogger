package scyjc1.frogger.model;

import javafx.scene.image.Image;

/**
 * <h1>Snake</h1>
 * <h2>Extends {@link MovingActor}.</h2>
 * <p>
 *     A moving element in the game.
 *     Appears in the middle lane of the game periodically.
 * </p>
 */
public class Snake extends MovingActor {
	private Image snakeLeft1;
	private Image snakeLeft2;
	private Image snakeLeft3;
	private Image snakeRight1;
	private Image snakeRight2;
	private Image snakeRight3;
	private double speed;

	/**
	 * Initialises the Snake with image, position and speed.
	 *
	 * @param size the desired width or height of the snake image, whichever is smaller, as an integer.
	 * @param x the initial x position of the Snake in the game world as an integer.
	 * @param y the initial y position of the Snake in the game world as an integer.
	 * @param speed the speed of the Snake as a double.
	 */
	public Snake(int size, int x, int y, double speed) {
		snakeLeft1 = new Image("file:src/main/resources/images/snake_left_animation_1.png", size, size, true, true);
		snakeLeft2 = new Image("file:src/main/resources/images/snake_left_animation_2.png", size, size, true, true);
		snakeLeft3 = new Image("file:src/main/resources/images/snake_left_animation_3.png", size, size, true, true);
		snakeRight1 = new Image("file:src/main/resources/images/snake_right_animation_1.png", size, size, true, true);
		snakeRight2 = new Image("file:src/main/resources/images/snake_right_animation_2.png", size, size, true, true);
		snakeRight3 = new Image("file:src/main/resources/images/snake_right_animation_3.png", size, size, true, true);
		if (speed >= 0) {
			setImage(snakeRight1);
		} else {
			setImage(snakeLeft1);
		}
		setX(x);
		setY(y);
		setSpeed(speed);
	}

	/**
	 * Defines the behaviour of Snake.
	 * This includes image animation, moving and turning around on boundaries.
	 *
	 * @param now the current time.
	 */
	@Override
	public void act(long now) {
		if (speed >= 0) {
			if (now / 150000000 % 3 == 0) {
				setImage(snakeRight1);
			} else if (now / 150000000 % 3 == 1) {
				setImage(snakeRight2);
			} else if (now / 150000000 % 3 == 2) {
				setImage(snakeRight3);
			}
		} else {
			if (now / 150000000 % 3 == 0) {
				setImage(snakeLeft1);
			} else if (now / 150000000 % 3 == 1) {
				setImage(snakeLeft2);
			} else if (now / 150000000 % 3 == 2) {
				setImage(snakeLeft3);
			}
		}

		move(speed, 0);

		if ((getX() > 900 && speed > 0) || (getX() < -300 && speed < 0)) {
			speed = -speed;
		}
	}

	/**
	 * Gets the speed of the Snake.
	 *
	 * @return the speed of the Snake as a double.
	 */
	@Override
	public double getSpeed() {
		return speed;
	}

	/**
	 * Sets the speed of the Snake.
	 *
	 * @param speed the desired speed of the Snake as a double.
	 */
	@Override
	public void setSpeed(double speed) {
		this.speed = speed;
	}
}

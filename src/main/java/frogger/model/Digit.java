package frogger.model;

import javafx.scene.image.Image;

/**
 * The images for number display in the game.
 */
public class Digit extends Actor {
	/**
	 * Defines the behaviour of {@link Digit}.
	 *
	 * @param now the timestamp of the current frame given in nanoseconds.
	 */
	@Override
	public void act(long now) {}

	/**
	 * Initialises the {@link Digit} with respective image, size and position.
	 *
	 * @param n    the digit to be displayed, 0 to 9, as an integer.
	 * @param size the desired width or height of the digit image, whichever is smaller, as an integer.
	 * @param x    the x position of the digit in the game world as an integer.
	 * @param y    the y position of the digit in the game world as an integer.
	 */
	public Digit(int n, int size, int x, int y) {
		if (n >= 0 && n <= 9) {
			setImage(new Image("file:src/main/resources/images/digit_" + n + ".png", size, size, true, true));
		}
		setX(x);
		setY(y);
	}
}

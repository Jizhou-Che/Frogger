package frogger.test;

import frogger.model.Actor;

/**
 * A concrete implementation of the {@link Actor} abstract class.
 * Used for testing purposes.
 */
public class ActorImpl extends Actor {
	/**
	 * Defines the behaviour of the implementation of {@link Actor}.
	 *
	 * @param now the timestamp of the current frame given in nanoseconds.
	 */
	@Override
	public void act(long now) {}

	/**
	 * Sets the size of the implementation of {@link Actor}.
	 *
	 * @param width  the desired width of the implementation of {@link Actor}.
	 * @param height the desired height of the implementation of {@link Actor}.
	 */
	void setSize(double width, double height) {
		setFitWidth(width);
		setFitHeight(height);
	}

	/**
	 * Sets the position of the implementation of {@link Actor}.
	 *
	 * @param x the desired x position of the implementation of {@link Actor}.
	 * @param y the desired y position of the implementation of {@link Actor}.
	 */
	void setPosition(double x, double y) {
		setX(x);
		setY(y);
	}
}

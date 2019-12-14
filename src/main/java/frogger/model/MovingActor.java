package frogger.model;

/**
 * Moving elements in the game world with customisable and adjustable speed.
 */
public abstract class MovingActor extends Actor {
	/**
	 * Gets the speed of the {@link MovingActor}.
	 *
	 * @return the speed of the {@link MovingActor} as a double.
	 */
	public abstract double getSpeed();

	/**
	 * Sets the speed of the {@link MovingActor}.
	 *
	 * @param speed the desired speed of the {@link MovingActor} as a double.
	 */
	public abstract void setSpeed(double speed);
}

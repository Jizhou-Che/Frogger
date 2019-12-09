package scyjc1.frogger.model;

/**
 * <h1>MovingActor</h1>
 * <h2>Extends {@link Actor}.</h2>
 * <p>
 *     Moving elements in the game world with customisable and adjustable speed.
 * </p>
 */
public abstract class MovingActor extends Actor {
	/**
	 * Gets the speed of the MovingActor.
	 *
	 * @return the speed of the MovingActor as a double.
	 */
	public abstract double getSpeed();

	/**
	 * Sets the speed of the MovingActor.
	 *
	 * @param speed the desired speed of the MovingActor as a double.
	 */
	public abstract void setSpeed(double speed);
}

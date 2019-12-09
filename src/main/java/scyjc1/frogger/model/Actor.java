package scyjc1.frogger.model;

import javafx.scene.image.ImageView;
import java.util.ArrayList;

/**
 * <h1>Actor</h1>
 * <h2>Extends {@link ImageView}.</h2>
 * <p>
 *     All active ImageView objects that can be added to the game view.
 * </p>
 */
public abstract class Actor extends ImageView {
	/**
	 * Changes position of the Actor.
	 *
	 * @param dx movement to the x direction, possibly negative.
	 * @param dy movement to the y direction, possibly negative.
	 */
	public void move(double dx, double dy) {
		setX(getX() + dx);
		setY(getY() + dy);
	}

	/**
	 * Gets the game world the Actor is in.
	 *
	 * @return the pane the actor is in as a World.
	 */
	public World getWorld() {
		return (World) getParent();
	}

	/**
	 * Gets all intersecting objects of a given type with the actor in the game world.
	 *
	 * @param cls the class of objects to check for intersection.
	 * @param <A> the name of the class to be specified.
	 * @return all intersecting objects of the specified class as an ArrayList.
	 */
	public <A extends Actor> ArrayList<A> getIntersectingObjects(Class<A> cls) {
		ArrayList<A> intersectingObjects = new ArrayList<A>();
		for (A actor : getWorld().getObjects(cls)) {
			if (actor != this && actor.intersects(this.getBoundsInLocal())) {
				intersectingObjects.add(actor);
			}
		}
		return intersectingObjects;
	}

	/**
	 * Defines the behaviour of the Actor with respect to the current time.
	 *
	 * @param now the current time.
	 */
	public abstract void act(long now);
}

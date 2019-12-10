package scyjc1.frogger.model;

import javafx.scene.image.ImageView;

import java.util.ArrayList;

/**
 * All active {@link ImageView} objects that can be added to the game world.
 */
public abstract class Actor extends ImageView {
	/**
	 * Changes position of the {@link Actor}.
	 *
	 * @param dx movement to the x direction, possibly negative, as a double.
	 * @param dy movement to the y direction, possibly negative, as a double.
	 */
	public void move(double dx, double dy) {
		setX(getX() + dx);
		setY(getY() + dy);
	}

	/**
	 * Gets the game world the {@link Actor} is in.
	 *
	 * @return the game world the actor is in as a {@link World}.
	 */
	public World getWorld() {
		return (World) getParent();
	}

	/**
	 * Gets all intersecting objects with the {@link Actor} of a given type in the game world.
	 *
	 * @param cls the class of the specified type.
	 * @param <A> the type of objects to check for intersection.
	 * @return all intersecting objects of the specified type in the game world as an {@link ArrayList}.
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
	 * Defines the behaviour of the {@link Actor} with respect to the timestamp of the current frame.
	 *
	 * @param now the timestamp of the current frame given in nanoseconds.
	 */
	public abstract void act(long now);
}

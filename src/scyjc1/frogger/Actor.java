package scyjc1.frogger;

import javafx.scene.image.ImageView;

import java.util.ArrayList;

/**
 * All movable objects.
 */
public abstract class Actor extends ImageView {
	/**
	 * Changes position of the actor.
	 *
	 * @param dx movement to the x direction, can be negative.
	 * @param dy movement to the y direction, can be negative.
	 */
	void move(double dx, double dy) {
		setX(getX() + dx);
		setY(getY() + dy);
	}

	/**
	 * @return the world the actor is in.
	 */
	World getWorld() {
		return (World) getParent();
	}

	<A extends Actor> java.util.List<A> getIntersectingObjects(java.lang.Class<A> cls) {
		ArrayList<A> intersectingObjects = new ArrayList<A>();
		for (A actor : getWorld().getObjects(cls)) {
			if (actor != this && actor.intersects(this.getBoundsInLocal())) {
				intersectingObjects.add(actor);
			}
		}
		return intersectingObjects;
	}

	public abstract void act(long now);
}

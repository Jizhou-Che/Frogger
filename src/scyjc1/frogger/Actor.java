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
	 * @param dx X position of the actor.
	 * @param dy Y position of the actor.
	 */
	void move(double dx, double dy) {
		setX(getX() + dx);
		setY(getY() + dy);
	}

	/**
	 * @return The world the actor is in.
	 */
	World getWorld() {
		return (World) getParent();
	}

	<A extends Actor> java.util.List<A> getIntersectingActors(java.lang.Class<A> cls) {
		ArrayList<A> intersectingActors = new ArrayList<A>();
		for (A actor : getWorld().getObjects(cls)) {
			if (actor != this && actor.intersects(this.getBoundsInLocal())) {
				intersectingActors.add(actor);
			}
		}
		return intersectingActors;
	}

	public abstract void act(long now);
}

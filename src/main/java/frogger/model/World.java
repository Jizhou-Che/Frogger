package frogger.model;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

/**
 * The game world every {@link Actor} is in.
 */
public class World extends Pane {
	/**
	 * Adds an {@link Actor} to the game world.
	 *
	 * @param actor the {@link Actor} to be added to the game world.
	 */
	public void add(Actor actor) {
		getChildren().add(actor);
	}

	/**
	 * Removes an {@link Actor} from the game world.
	 *
	 * @param actor the {@link Actor} to be removed from the game world.
	 */
	public void remove(Actor actor) {
		getChildren().remove(actor);
	}

	/**
	 * Gets all objects of a give type in the game world.
	 *
	 * @param cls the class of the specified type.
	 * @param <A> the type of objects to check for intersection.
	 * @return all objects of the specified type in the game world as an {@link ArrayList}.
	 */
	public <A extends Actor> ArrayList<A> getObjects(Class<A> cls) {
		ArrayList<A> objects = new ArrayList<A>();
		for (Node n : getChildren()) {
			if (cls.isInstance(n)) {
				objects.add((A) n);
			}
		}
		return objects;
	}
}

package scyjc1.frogger.model;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import java.util.ArrayList;

/**
 * <h1>World</h1>
 * <h2>Extends {@link Pane}.</h2>
 * <p>
 *     The game world every Actor is in.
 * </p>
 */
public class World extends Pane {
	/**
	 * Adds an Actor to the game world.
	 *
	 * @param actor the Actor to add to the game world.
	 */
	public void add(Actor actor) {
		getChildren().add(actor);
	}

	/**
	 * Removes an Actor from the game world.
	 *
	 * @param actor the Actor to remove from the game world.
	 */
	public void remove(Actor actor) {
		getChildren().remove(actor);
	}

	/**
	 * Gets all objects of a give type in the game world.
	 *
	 * @param cls the class of object to get.
	 * @param <A> the name of the class to be specified.
	 * @return all objects of the specified class in the game world as an ArrayList.
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

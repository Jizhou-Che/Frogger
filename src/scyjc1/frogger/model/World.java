package scyjc1.frogger.model;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.List;

public class World extends Pane {
	private AnimationTimer timer;

	public World() {
		sceneProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				newValue.setOnKeyPressed(event -> {
					if (getOnKeyPressed() != null) {
						getOnKeyPressed().handle(event);
					}
					List<Actor> myActors = getObjects(Actor.class);
					for (Actor anActor : myActors) {
						if (anActor.getOnKeyPressed() != null) {
							anActor.getOnKeyPressed().handle(event);
						}
					}
				});
				newValue.setOnKeyReleased(event -> {
					if (getOnKeyReleased() != null) {
						getOnKeyReleased().handle(event);
					}
					List<Actor> myActors = getObjects(Actor.class);
					for (Actor anActor : myActors) {
						if (anActor.getOnKeyReleased() != null) {
							anActor.getOnKeyReleased().handle(event);
						}
					}
				});
			}
		});
	}

	private void createTimer() {
		timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				List<Actor> actors = getObjects(Actor.class);
				for (Actor anActor : actors) {
					anActor.act(now);
				}
			}
		};
	}

	public void start() {
		createTimer();
		timer.start();
	}

	public void stop() {
		timer.stop();
	}

	public void resume() {
		timer.start();
	}

	public void add(Actor actor) {
		getChildren().add(actor);
	}

	public void remove(Actor actor) {
		getChildren().remove(actor);
	}

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

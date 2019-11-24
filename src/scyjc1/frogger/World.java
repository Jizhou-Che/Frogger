package scyjc1.frogger;

import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

class World extends Pane {
	private AnimationTimer timer;

	World() {
		sceneProperty().addListener(new ChangeListener<Scene>() {
			@Override
			public void changed(ObservableValue<? extends Scene> observable, Scene oldValue, Scene newValue) {
				if (newValue != null) {
					newValue.setOnKeyReleased(new EventHandler<KeyEvent>() {
						@Override
						public void handle(KeyEvent event) {
							if (getOnKeyReleased() != null)
								getOnKeyReleased().handle(event);
							List<Actor> myActors = getObjects(Actor.class);
							for (Actor anActor : myActors) {
								if (anActor.getOnKeyReleased() != null) {
									anActor.getOnKeyReleased().handle(event);
								}
							}
						}
					});

					newValue.setOnKeyPressed(new EventHandler<KeyEvent>() {
						@Override
						public void handle(KeyEvent event) {
							if (getOnKeyPressed() != null)
								getOnKeyPressed().handle(event);
							List<Actor> myActors = getObjects(Actor.class);
							for (Actor anActor : myActors) {
								if (anActor.getOnKeyPressed() != null) {
									anActor.getOnKeyPressed().handle(event);
								}
							}
						}
					});
				}
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

	void start() {
		createTimer();
		timer.start();
	}

	void stop() {
		timer.stop();
	}

	void add(Actor actor) {
		getChildren().add(actor);
	}

	void remove(Actor actor) {
		getChildren().remove(actor);
	}

	<A extends Actor> ArrayList<A> getObjects(Class<A> cls) {
		ArrayList<A> objects = new ArrayList<A>();
		for (Node n : getChildren()) {
			if (cls.isInstance(n)) {
				objects.add((A) n);
			}
		}
		return objects;
	}
}

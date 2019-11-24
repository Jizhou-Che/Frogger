package scyjc1.frogger;

import javafx.scene.image.Image;

public class Slot extends Actor {
	private boolean activated = false;

	@Override
	public void act(long now) {
	}

	Slot(int x, int y) {
		setX(x);
		setY(y);
		setImage(new Image("file:resources/images/End.png", 60, 60, true, true));
	}

	void setEnd() {
		setImage(new Image("file:resources/images/FrogEnd.png", 70, 70, true, true));
		activated = true;
	}

	boolean isActivated() {
		return activated;
	}
}

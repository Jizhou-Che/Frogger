package scyjc1.frogger;

import javafx.scene.image.Image;

public class End extends Actor {
	private boolean activated = false;

	@Override
	public void act(long now) {
	}

	End(int x, int y) {
		setX(x);
		setY(y);
		setImage(new Image("file:resources/End.png", 60, 60, true, true));
	}

	void setEnd() {
		setImage(new Image("file:resources/FrogEnd.png", 70, 70, true, true));
		activated = true;
	}

	boolean isActivated() {
		return activated;
	}
}

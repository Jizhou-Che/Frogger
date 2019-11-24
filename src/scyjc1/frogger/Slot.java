package scyjc1.frogger;

import javafx.scene.image.Image;

public class Slot extends Actor {
	private boolean occupied = false;

	@Override
	public void act(long now) {
	}

	Slot(int x, int y) {
		setX(x);
		setY(y);
		setImage(new Image("file:resources/End.png", 60, 60, true, true));
	}

	void setOccupied() {
		setImage(new Image("file:resources/FrogEnd.png", 70, 70, true, true));
		occupied = true;
	}

	boolean isOccupied() {
		return occupied;
	}
}

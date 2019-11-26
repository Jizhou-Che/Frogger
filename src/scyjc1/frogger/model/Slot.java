package scyjc1.frogger.model;

import javafx.scene.image.Image;

public class Slot extends Actor {
	private boolean occupied = false;

	@Override
	public void act(long now) {
	}

	public Slot(int x, int y) {
		setX(x);
		setY(y);
		setImage(new Image("file:resources/images/slot.png", 60, 60, true, true));
	}

	void setOccupied() {
		setImage(new Image("file:resources/images/slot_occupied.png", 70, 70, true, true));
		occupied = true;
	}

	boolean isOccupied() {
		return occupied;
	}
}

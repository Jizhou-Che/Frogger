package scyjc1.frogger.model;

import javafx.beans.NamedArg;
import javafx.scene.image.Image;

public class Slot extends Actor {
	private boolean occupied = false;

	@Override
	public void act(long now) {
	}

	public Slot(@NamedArg("x") int x, @NamedArg("y") int y) {
		setX(x);
		setY(y);
		setImage(new Image("file:resources/images/slot.png", 60, 60, true, true));
	}

	void setOccupied() {
		setImage(new Image("file:resources/images/slot_occupied.png", 65, 65, true, true));
		occupied = true;
	}

	void setEmpty() {
		setImage(new Image("file:resources/images/slot.png", 60, 60, true, true));
		occupied = false;
	}

	boolean isOccupied() {
		return occupied;
	}
}
